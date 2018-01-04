package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterJwtApp;

import io.github.jhipster.application.domain.UVUser;
import io.github.jhipster.application.repository.UVUserRepository;
import io.github.jhipster.application.service.dto.UVUserDTO;
import io.github.jhipster.application.service.mapper.UVUserMapper;
import io.github.jhipster.application.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static io.github.jhipster.application.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the UVUserResource REST controller.
 *
 * @see UVUserResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhipsterJwtApp.class)
public class UVUserResourceIntTest {

    private static final String DEFAULT_USER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_USER_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_PASSWORD = "AAAAAAAAAA";
    private static final String UPDATED_PASSWORD = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_VERIFIED_USER = false;
    private static final Boolean UPDATED_IS_VERIFIED_USER = true;

    @Autowired
    private UVUserRepository uVUserRepository;

    @Autowired
    private UVUserMapper uVUserMapper;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restUVUserMockMvc;

    private UVUser uVUser;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final UVUserResource uVUserResource = new UVUserResource(uVUserRepository, uVUserMapper);
        this.restUVUserMockMvc = MockMvcBuilders.standaloneSetup(uVUserResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static UVUser createEntity(EntityManager em) {
        UVUser uVUser = new UVUser()
            .userName(DEFAULT_USER_NAME)
            .password(DEFAULT_PASSWORD)
            .email(DEFAULT_EMAIL)
            .isVerifiedUser(DEFAULT_IS_VERIFIED_USER);
        return uVUser;
    }

    @Before
    public void initTest() {
        uVUser = createEntity(em);
    }

    @Test
    @Transactional
    public void createUVUser() throws Exception {
        int databaseSizeBeforeCreate = uVUserRepository.findAll().size();

        // Create the UVUser
        UVUserDTO uVUserDTO = uVUserMapper.toDto(uVUser);
        restUVUserMockMvc.perform(post("/api/uv-users")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(uVUserDTO)))
            .andExpect(status().isCreated());

        // Validate the UVUser in the database
        List<UVUser> uVUserList = uVUserRepository.findAll();
        assertThat(uVUserList).hasSize(databaseSizeBeforeCreate + 1);
        UVUser testUVUser = uVUserList.get(uVUserList.size() - 1);
        assertThat(testUVUser.getUserName()).isEqualTo(DEFAULT_USER_NAME);
        assertThat(testUVUser.getPassword()).isEqualTo(DEFAULT_PASSWORD);
        assertThat(testUVUser.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testUVUser.isIsVerifiedUser()).isEqualTo(DEFAULT_IS_VERIFIED_USER);
    }

    @Test
    @Transactional
    public void createUVUserWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = uVUserRepository.findAll().size();

        // Create the UVUser with an existing ID
        uVUser.setId(1L);
        UVUserDTO uVUserDTO = uVUserMapper.toDto(uVUser);

        // An entity with an existing ID cannot be created, so this API call must fail
        restUVUserMockMvc.perform(post("/api/uv-users")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(uVUserDTO)))
            .andExpect(status().isBadRequest());

        // Validate the UVUser in the database
        List<UVUser> uVUserList = uVUserRepository.findAll();
        assertThat(uVUserList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllUVUsers() throws Exception {
        // Initialize the database
        uVUserRepository.saveAndFlush(uVUser);

        // Get all the uVUserList
        restUVUserMockMvc.perform(get("/api/uv-users?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(uVUser.getId().intValue())))
            .andExpect(jsonPath("$.[*].userName").value(hasItem(DEFAULT_USER_NAME.toString())))
            .andExpect(jsonPath("$.[*].password").value(hasItem(DEFAULT_PASSWORD.toString())))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL.toString())))
            .andExpect(jsonPath("$.[*].isVerifiedUser").value(hasItem(DEFAULT_IS_VERIFIED_USER.booleanValue())));
    }

    @Test
    @Transactional
    public void getUVUser() throws Exception {
        // Initialize the database
        uVUserRepository.saveAndFlush(uVUser);

        // Get the uVUser
        restUVUserMockMvc.perform(get("/api/uv-users/{id}", uVUser.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(uVUser.getId().intValue()))
            .andExpect(jsonPath("$.userName").value(DEFAULT_USER_NAME.toString()))
            .andExpect(jsonPath("$.password").value(DEFAULT_PASSWORD.toString()))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL.toString()))
            .andExpect(jsonPath("$.isVerifiedUser").value(DEFAULT_IS_VERIFIED_USER.booleanValue()));
    }

    @Test
    @Transactional
    public void getNonExistingUVUser() throws Exception {
        // Get the uVUser
        restUVUserMockMvc.perform(get("/api/uv-users/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateUVUser() throws Exception {
        // Initialize the database
        uVUserRepository.saveAndFlush(uVUser);
        int databaseSizeBeforeUpdate = uVUserRepository.findAll().size();

        // Update the uVUser
        UVUser updatedUVUser = uVUserRepository.findOne(uVUser.getId());
        // Disconnect from session so that the updates on updatedUVUser are not directly saved in db
        em.detach(updatedUVUser);
        updatedUVUser
            .userName(UPDATED_USER_NAME)
            .password(UPDATED_PASSWORD)
            .email(UPDATED_EMAIL)
            .isVerifiedUser(UPDATED_IS_VERIFIED_USER);
        UVUserDTO uVUserDTO = uVUserMapper.toDto(updatedUVUser);

        restUVUserMockMvc.perform(put("/api/uv-users")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(uVUserDTO)))
            .andExpect(status().isOk());

        // Validate the UVUser in the database
        List<UVUser> uVUserList = uVUserRepository.findAll();
        assertThat(uVUserList).hasSize(databaseSizeBeforeUpdate);
        UVUser testUVUser = uVUserList.get(uVUserList.size() - 1);
        assertThat(testUVUser.getUserName()).isEqualTo(UPDATED_USER_NAME);
        assertThat(testUVUser.getPassword()).isEqualTo(UPDATED_PASSWORD);
        assertThat(testUVUser.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testUVUser.isIsVerifiedUser()).isEqualTo(UPDATED_IS_VERIFIED_USER);
    }

    @Test
    @Transactional
    public void updateNonExistingUVUser() throws Exception {
        int databaseSizeBeforeUpdate = uVUserRepository.findAll().size();

        // Create the UVUser
        UVUserDTO uVUserDTO = uVUserMapper.toDto(uVUser);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restUVUserMockMvc.perform(put("/api/uv-users")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(uVUserDTO)))
            .andExpect(status().isCreated());

        // Validate the UVUser in the database
        List<UVUser> uVUserList = uVUserRepository.findAll();
        assertThat(uVUserList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteUVUser() throws Exception {
        // Initialize the database
        uVUserRepository.saveAndFlush(uVUser);
        int databaseSizeBeforeDelete = uVUserRepository.findAll().size();

        // Get the uVUser
        restUVUserMockMvc.perform(delete("/api/uv-users/{id}", uVUser.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<UVUser> uVUserList = uVUserRepository.findAll();
        assertThat(uVUserList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(UVUser.class);
        UVUser uVUser1 = new UVUser();
        uVUser1.setId(1L);
        UVUser uVUser2 = new UVUser();
        uVUser2.setId(uVUser1.getId());
        assertThat(uVUser1).isEqualTo(uVUser2);
        uVUser2.setId(2L);
        assertThat(uVUser1).isNotEqualTo(uVUser2);
        uVUser1.setId(null);
        assertThat(uVUser1).isNotEqualTo(uVUser2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(UVUserDTO.class);
        UVUserDTO uVUserDTO1 = new UVUserDTO();
        uVUserDTO1.setId(1L);
        UVUserDTO uVUserDTO2 = new UVUserDTO();
        assertThat(uVUserDTO1).isNotEqualTo(uVUserDTO2);
        uVUserDTO2.setId(uVUserDTO1.getId());
        assertThat(uVUserDTO1).isEqualTo(uVUserDTO2);
        uVUserDTO2.setId(2L);
        assertThat(uVUserDTO1).isNotEqualTo(uVUserDTO2);
        uVUserDTO1.setId(null);
        assertThat(uVUserDTO1).isNotEqualTo(uVUserDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(uVUserMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(uVUserMapper.fromId(null)).isNull();
    }
}
