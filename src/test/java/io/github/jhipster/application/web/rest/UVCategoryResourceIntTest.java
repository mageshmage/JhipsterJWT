package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterJwtApp;

import io.github.jhipster.application.domain.UVCategory;
import io.github.jhipster.application.repository.UVCategoryRepository;
import io.github.jhipster.application.service.dto.UVCategoryDTO;
import io.github.jhipster.application.service.mapper.UVCategoryMapper;
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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static io.github.jhipster.application.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the UVCategoryResource REST controller.
 *
 * @see UVCategoryResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhipsterJwtApp.class)
public class UVCategoryResourceIntTest {

    private static final String DEFAULT_CATEGORY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_CATEGORY_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_CATEGORY_CODE = "AAAAAAAAAA";
    private static final String UPDATED_CATEGORY_CODE = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_ENABLED = false;
    private static final Boolean UPDATED_IS_ENABLED = true;

    private static final LocalDate DEFAULT_CREATED_ON = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATED_ON = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_LAST_UPDATED_ON = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_LAST_UPDATED_ON = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private UVCategoryRepository uVCategoryRepository;

    @Autowired
    private UVCategoryMapper uVCategoryMapper;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restUVCategoryMockMvc;

    private UVCategory uVCategory;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final UVCategoryResource uVCategoryResource = new UVCategoryResource(uVCategoryRepository, uVCategoryMapper);
        this.restUVCategoryMockMvc = MockMvcBuilders.standaloneSetup(uVCategoryResource)
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
    public static UVCategory createEntity(EntityManager em) {
        UVCategory uVCategory = new UVCategory()
            .categoryName(DEFAULT_CATEGORY_NAME)
            .categoryCode(DEFAULT_CATEGORY_CODE)
            .isEnabled(DEFAULT_IS_ENABLED)
            .createdOn(DEFAULT_CREATED_ON)
            .lastUpdatedOn(DEFAULT_LAST_UPDATED_ON);
        return uVCategory;
    }

    @Before
    public void initTest() {
        uVCategory = createEntity(em);
    }

    @Test
    @Transactional
    public void createUVCategory() throws Exception {
        int databaseSizeBeforeCreate = uVCategoryRepository.findAll().size();

        // Create the UVCategory
        UVCategoryDTO uVCategoryDTO = uVCategoryMapper.toDto(uVCategory);
        restUVCategoryMockMvc.perform(post("/api/uv-categories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(uVCategoryDTO)))
            .andExpect(status().isCreated());

        // Validate the UVCategory in the database
        List<UVCategory> uVCategoryList = uVCategoryRepository.findAll();
        assertThat(uVCategoryList).hasSize(databaseSizeBeforeCreate + 1);
        UVCategory testUVCategory = uVCategoryList.get(uVCategoryList.size() - 1);
        assertThat(testUVCategory.getCategoryName()).isEqualTo(DEFAULT_CATEGORY_NAME);
        assertThat(testUVCategory.getCategoryCode()).isEqualTo(DEFAULT_CATEGORY_CODE);
        assertThat(testUVCategory.isIsEnabled()).isEqualTo(DEFAULT_IS_ENABLED);
        assertThat(testUVCategory.getCreatedOn()).isEqualTo(DEFAULT_CREATED_ON);
        assertThat(testUVCategory.getLastUpdatedOn()).isEqualTo(DEFAULT_LAST_UPDATED_ON);
    }

    @Test
    @Transactional
    public void createUVCategoryWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = uVCategoryRepository.findAll().size();

        // Create the UVCategory with an existing ID
        uVCategory.setId(1L);
        UVCategoryDTO uVCategoryDTO = uVCategoryMapper.toDto(uVCategory);

        // An entity with an existing ID cannot be created, so this API call must fail
        restUVCategoryMockMvc.perform(post("/api/uv-categories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(uVCategoryDTO)))
            .andExpect(status().isBadRequest());

        // Validate the UVCategory in the database
        List<UVCategory> uVCategoryList = uVCategoryRepository.findAll();
        assertThat(uVCategoryList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllUVCategories() throws Exception {
        // Initialize the database
        uVCategoryRepository.saveAndFlush(uVCategory);

        // Get all the uVCategoryList
        restUVCategoryMockMvc.perform(get("/api/uv-categories?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(uVCategory.getId().intValue())))
            .andExpect(jsonPath("$.[*].categoryName").value(hasItem(DEFAULT_CATEGORY_NAME.toString())))
            .andExpect(jsonPath("$.[*].categoryCode").value(hasItem(DEFAULT_CATEGORY_CODE.toString())))
            .andExpect(jsonPath("$.[*].isEnabled").value(hasItem(DEFAULT_IS_ENABLED.booleanValue())))
            .andExpect(jsonPath("$.[*].createdOn").value(hasItem(DEFAULT_CREATED_ON.toString())))
            .andExpect(jsonPath("$.[*].lastUpdatedOn").value(hasItem(DEFAULT_LAST_UPDATED_ON.toString())));
    }

    @Test
    @Transactional
    public void getUVCategory() throws Exception {
        // Initialize the database
        uVCategoryRepository.saveAndFlush(uVCategory);

        // Get the uVCategory
        restUVCategoryMockMvc.perform(get("/api/uv-categories/{id}", uVCategory.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(uVCategory.getId().intValue()))
            .andExpect(jsonPath("$.categoryName").value(DEFAULT_CATEGORY_NAME.toString()))
            .andExpect(jsonPath("$.categoryCode").value(DEFAULT_CATEGORY_CODE.toString()))
            .andExpect(jsonPath("$.isEnabled").value(DEFAULT_IS_ENABLED.booleanValue()))
            .andExpect(jsonPath("$.createdOn").value(DEFAULT_CREATED_ON.toString()))
            .andExpect(jsonPath("$.lastUpdatedOn").value(DEFAULT_LAST_UPDATED_ON.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingUVCategory() throws Exception {
        // Get the uVCategory
        restUVCategoryMockMvc.perform(get("/api/uv-categories/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateUVCategory() throws Exception {
        // Initialize the database
        uVCategoryRepository.saveAndFlush(uVCategory);
        int databaseSizeBeforeUpdate = uVCategoryRepository.findAll().size();

        // Update the uVCategory
        UVCategory updatedUVCategory = uVCategoryRepository.findOne(uVCategory.getId());
        // Disconnect from session so that the updates on updatedUVCategory are not directly saved in db
        em.detach(updatedUVCategory);
        updatedUVCategory
            .categoryName(UPDATED_CATEGORY_NAME)
            .categoryCode(UPDATED_CATEGORY_CODE)
            .isEnabled(UPDATED_IS_ENABLED)
            .createdOn(UPDATED_CREATED_ON)
            .lastUpdatedOn(UPDATED_LAST_UPDATED_ON);
        UVCategoryDTO uVCategoryDTO = uVCategoryMapper.toDto(updatedUVCategory);

        restUVCategoryMockMvc.perform(put("/api/uv-categories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(uVCategoryDTO)))
            .andExpect(status().isOk());

        // Validate the UVCategory in the database
        List<UVCategory> uVCategoryList = uVCategoryRepository.findAll();
        assertThat(uVCategoryList).hasSize(databaseSizeBeforeUpdate);
        UVCategory testUVCategory = uVCategoryList.get(uVCategoryList.size() - 1);
        assertThat(testUVCategory.getCategoryName()).isEqualTo(UPDATED_CATEGORY_NAME);
        assertThat(testUVCategory.getCategoryCode()).isEqualTo(UPDATED_CATEGORY_CODE);
        assertThat(testUVCategory.isIsEnabled()).isEqualTo(UPDATED_IS_ENABLED);
        assertThat(testUVCategory.getCreatedOn()).isEqualTo(UPDATED_CREATED_ON);
        assertThat(testUVCategory.getLastUpdatedOn()).isEqualTo(UPDATED_LAST_UPDATED_ON);
    }

    @Test
    @Transactional
    public void updateNonExistingUVCategory() throws Exception {
        int databaseSizeBeforeUpdate = uVCategoryRepository.findAll().size();

        // Create the UVCategory
        UVCategoryDTO uVCategoryDTO = uVCategoryMapper.toDto(uVCategory);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restUVCategoryMockMvc.perform(put("/api/uv-categories")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(uVCategoryDTO)))
            .andExpect(status().isCreated());

        // Validate the UVCategory in the database
        List<UVCategory> uVCategoryList = uVCategoryRepository.findAll();
        assertThat(uVCategoryList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteUVCategory() throws Exception {
        // Initialize the database
        uVCategoryRepository.saveAndFlush(uVCategory);
        int databaseSizeBeforeDelete = uVCategoryRepository.findAll().size();

        // Get the uVCategory
        restUVCategoryMockMvc.perform(delete("/api/uv-categories/{id}", uVCategory.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<UVCategory> uVCategoryList = uVCategoryRepository.findAll();
        assertThat(uVCategoryList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(UVCategory.class);
        UVCategory uVCategory1 = new UVCategory();
        uVCategory1.setId(1L);
        UVCategory uVCategory2 = new UVCategory();
        uVCategory2.setId(uVCategory1.getId());
        assertThat(uVCategory1).isEqualTo(uVCategory2);
        uVCategory2.setId(2L);
        assertThat(uVCategory1).isNotEqualTo(uVCategory2);
        uVCategory1.setId(null);
        assertThat(uVCategory1).isNotEqualTo(uVCategory2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(UVCategoryDTO.class);
        UVCategoryDTO uVCategoryDTO1 = new UVCategoryDTO();
        uVCategoryDTO1.setId(1L);
        UVCategoryDTO uVCategoryDTO2 = new UVCategoryDTO();
        assertThat(uVCategoryDTO1).isNotEqualTo(uVCategoryDTO2);
        uVCategoryDTO2.setId(uVCategoryDTO1.getId());
        assertThat(uVCategoryDTO1).isEqualTo(uVCategoryDTO2);
        uVCategoryDTO2.setId(2L);
        assertThat(uVCategoryDTO1).isNotEqualTo(uVCategoryDTO2);
        uVCategoryDTO1.setId(null);
        assertThat(uVCategoryDTO1).isNotEqualTo(uVCategoryDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(uVCategoryMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(uVCategoryMapper.fromId(null)).isNull();
    }
}
