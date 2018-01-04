package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterJwtApp;

import io.github.jhipster.application.domain.UVBrand;
import io.github.jhipster.application.repository.UVBrandRepository;
import io.github.jhipster.application.service.dto.UVBrandDTO;
import io.github.jhipster.application.service.mapper.UVBrandMapper;
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
 * Test class for the UVBrandResource REST controller.
 *
 * @see UVBrandResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhipsterJwtApp.class)
public class UVBrandResourceIntTest {

    private static final String DEFAULT_BRAND_NAME = "AAAAAAAAAA";
    private static final String UPDATED_BRAND_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_BRAND_CODE = "AAAAAAAAAA";
    private static final String UPDATED_BRAND_CODE = "BBBBBBBBBB";

    @Autowired
    private UVBrandRepository uVBrandRepository;

    @Autowired
    private UVBrandMapper uVBrandMapper;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restUVBrandMockMvc;

    private UVBrand uVBrand;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final UVBrandResource uVBrandResource = new UVBrandResource(uVBrandRepository, uVBrandMapper);
        this.restUVBrandMockMvc = MockMvcBuilders.standaloneSetup(uVBrandResource)
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
    public static UVBrand createEntity(EntityManager em) {
        UVBrand uVBrand = new UVBrand()
            .brandName(DEFAULT_BRAND_NAME)
            .brandCode(DEFAULT_BRAND_CODE);
        return uVBrand;
    }

    @Before
    public void initTest() {
        uVBrand = createEntity(em);
    }

    @Test
    @Transactional
    public void createUVBrand() throws Exception {
        int databaseSizeBeforeCreate = uVBrandRepository.findAll().size();

        // Create the UVBrand
        UVBrandDTO uVBrandDTO = uVBrandMapper.toDto(uVBrand);
        restUVBrandMockMvc.perform(post("/api/uv-brands")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(uVBrandDTO)))
            .andExpect(status().isCreated());

        // Validate the UVBrand in the database
        List<UVBrand> uVBrandList = uVBrandRepository.findAll();
        assertThat(uVBrandList).hasSize(databaseSizeBeforeCreate + 1);
        UVBrand testUVBrand = uVBrandList.get(uVBrandList.size() - 1);
        assertThat(testUVBrand.getBrandName()).isEqualTo(DEFAULT_BRAND_NAME);
        assertThat(testUVBrand.getBrandCode()).isEqualTo(DEFAULT_BRAND_CODE);
    }

    @Test
    @Transactional
    public void createUVBrandWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = uVBrandRepository.findAll().size();

        // Create the UVBrand with an existing ID
        uVBrand.setId(1L);
        UVBrandDTO uVBrandDTO = uVBrandMapper.toDto(uVBrand);

        // An entity with an existing ID cannot be created, so this API call must fail
        restUVBrandMockMvc.perform(post("/api/uv-brands")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(uVBrandDTO)))
            .andExpect(status().isBadRequest());

        // Validate the UVBrand in the database
        List<UVBrand> uVBrandList = uVBrandRepository.findAll();
        assertThat(uVBrandList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllUVBrands() throws Exception {
        // Initialize the database
        uVBrandRepository.saveAndFlush(uVBrand);

        // Get all the uVBrandList
        restUVBrandMockMvc.perform(get("/api/uv-brands?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(uVBrand.getId().intValue())))
            .andExpect(jsonPath("$.[*].brandName").value(hasItem(DEFAULT_BRAND_NAME.toString())))
            .andExpect(jsonPath("$.[*].brandCode").value(hasItem(DEFAULT_BRAND_CODE.toString())));
    }

    @Test
    @Transactional
    public void getUVBrand() throws Exception {
        // Initialize the database
        uVBrandRepository.saveAndFlush(uVBrand);

        // Get the uVBrand
        restUVBrandMockMvc.perform(get("/api/uv-brands/{id}", uVBrand.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(uVBrand.getId().intValue()))
            .andExpect(jsonPath("$.brandName").value(DEFAULT_BRAND_NAME.toString()))
            .andExpect(jsonPath("$.brandCode").value(DEFAULT_BRAND_CODE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingUVBrand() throws Exception {
        // Get the uVBrand
        restUVBrandMockMvc.perform(get("/api/uv-brands/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateUVBrand() throws Exception {
        // Initialize the database
        uVBrandRepository.saveAndFlush(uVBrand);
        int databaseSizeBeforeUpdate = uVBrandRepository.findAll().size();

        // Update the uVBrand
        UVBrand updatedUVBrand = uVBrandRepository.findOne(uVBrand.getId());
        // Disconnect from session so that the updates on updatedUVBrand are not directly saved in db
        em.detach(updatedUVBrand);
        updatedUVBrand
            .brandName(UPDATED_BRAND_NAME)
            .brandCode(UPDATED_BRAND_CODE);
        UVBrandDTO uVBrandDTO = uVBrandMapper.toDto(updatedUVBrand);

        restUVBrandMockMvc.perform(put("/api/uv-brands")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(uVBrandDTO)))
            .andExpect(status().isOk());

        // Validate the UVBrand in the database
        List<UVBrand> uVBrandList = uVBrandRepository.findAll();
        assertThat(uVBrandList).hasSize(databaseSizeBeforeUpdate);
        UVBrand testUVBrand = uVBrandList.get(uVBrandList.size() - 1);
        assertThat(testUVBrand.getBrandName()).isEqualTo(UPDATED_BRAND_NAME);
        assertThat(testUVBrand.getBrandCode()).isEqualTo(UPDATED_BRAND_CODE);
    }

    @Test
    @Transactional
    public void updateNonExistingUVBrand() throws Exception {
        int databaseSizeBeforeUpdate = uVBrandRepository.findAll().size();

        // Create the UVBrand
        UVBrandDTO uVBrandDTO = uVBrandMapper.toDto(uVBrand);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restUVBrandMockMvc.perform(put("/api/uv-brands")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(uVBrandDTO)))
            .andExpect(status().isCreated());

        // Validate the UVBrand in the database
        List<UVBrand> uVBrandList = uVBrandRepository.findAll();
        assertThat(uVBrandList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteUVBrand() throws Exception {
        // Initialize the database
        uVBrandRepository.saveAndFlush(uVBrand);
        int databaseSizeBeforeDelete = uVBrandRepository.findAll().size();

        // Get the uVBrand
        restUVBrandMockMvc.perform(delete("/api/uv-brands/{id}", uVBrand.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<UVBrand> uVBrandList = uVBrandRepository.findAll();
        assertThat(uVBrandList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(UVBrand.class);
        UVBrand uVBrand1 = new UVBrand();
        uVBrand1.setId(1L);
        UVBrand uVBrand2 = new UVBrand();
        uVBrand2.setId(uVBrand1.getId());
        assertThat(uVBrand1).isEqualTo(uVBrand2);
        uVBrand2.setId(2L);
        assertThat(uVBrand1).isNotEqualTo(uVBrand2);
        uVBrand1.setId(null);
        assertThat(uVBrand1).isNotEqualTo(uVBrand2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(UVBrandDTO.class);
        UVBrandDTO uVBrandDTO1 = new UVBrandDTO();
        uVBrandDTO1.setId(1L);
        UVBrandDTO uVBrandDTO2 = new UVBrandDTO();
        assertThat(uVBrandDTO1).isNotEqualTo(uVBrandDTO2);
        uVBrandDTO2.setId(uVBrandDTO1.getId());
        assertThat(uVBrandDTO1).isEqualTo(uVBrandDTO2);
        uVBrandDTO2.setId(2L);
        assertThat(uVBrandDTO1).isNotEqualTo(uVBrandDTO2);
        uVBrandDTO1.setId(null);
        assertThat(uVBrandDTO1).isNotEqualTo(uVBrandDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(uVBrandMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(uVBrandMapper.fromId(null)).isNull();
    }
}
