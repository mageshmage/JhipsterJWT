package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterJwtApp;

import io.github.jhipster.application.domain.UVFreeVoucher;
import io.github.jhipster.application.repository.UVFreeVoucherRepository;
import io.github.jhipster.application.service.dto.UVFreeVoucherDTO;
import io.github.jhipster.application.service.mapper.UVFreeVoucherMapper;
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
 * Test class for the UVFreeVoucherResource REST controller.
 *
 * @see UVFreeVoucherResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhipsterJwtApp.class)
public class UVFreeVoucherResourceIntTest {

    private static final String DEFAULT_VOUCHER_CODE = "AAAAAAAAAA";
    private static final String UPDATED_VOUCHER_CODE = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_VALID = false;
    private static final Boolean UPDATED_IS_VALID = true;

    private static final Boolean DEFAULT_IS_EXPIRED = false;
    private static final Boolean UPDATED_IS_EXPIRED = true;

    private static final Boolean DEFAULT_CREATED_BY = false;
    private static final Boolean UPDATED_CREATED_BY = true;

    private static final LocalDate DEFAULT_CREATED_ON = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATED_ON = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_LAST_UPDATED_ON = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_LAST_UPDATED_ON = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private UVFreeVoucherRepository uVFreeVoucherRepository;

    @Autowired
    private UVFreeVoucherMapper uVFreeVoucherMapper;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restUVFreeVoucherMockMvc;

    private UVFreeVoucher uVFreeVoucher;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final UVFreeVoucherResource uVFreeVoucherResource = new UVFreeVoucherResource(uVFreeVoucherRepository, uVFreeVoucherMapper);
        this.restUVFreeVoucherMockMvc = MockMvcBuilders.standaloneSetup(uVFreeVoucherResource)
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
    public static UVFreeVoucher createEntity(EntityManager em) {
        UVFreeVoucher uVFreeVoucher = new UVFreeVoucher()
            .voucherCode(DEFAULT_VOUCHER_CODE)
            .isValid(DEFAULT_IS_VALID)
            .isExpired(DEFAULT_IS_EXPIRED)
            .createdBy(DEFAULT_CREATED_BY)
            .createdOn(DEFAULT_CREATED_ON)
            .lastUpdatedOn(DEFAULT_LAST_UPDATED_ON);
        return uVFreeVoucher;
    }

    @Before
    public void initTest() {
        uVFreeVoucher = createEntity(em);
    }

    @Test
    @Transactional
    public void createUVFreeVoucher() throws Exception {
        int databaseSizeBeforeCreate = uVFreeVoucherRepository.findAll().size();

        // Create the UVFreeVoucher
        UVFreeVoucherDTO uVFreeVoucherDTO = uVFreeVoucherMapper.toDto(uVFreeVoucher);
        restUVFreeVoucherMockMvc.perform(post("/api/uv-free-vouchers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(uVFreeVoucherDTO)))
            .andExpect(status().isCreated());

        // Validate the UVFreeVoucher in the database
        List<UVFreeVoucher> uVFreeVoucherList = uVFreeVoucherRepository.findAll();
        assertThat(uVFreeVoucherList).hasSize(databaseSizeBeforeCreate + 1);
        UVFreeVoucher testUVFreeVoucher = uVFreeVoucherList.get(uVFreeVoucherList.size() - 1);
        assertThat(testUVFreeVoucher.getVoucherCode()).isEqualTo(DEFAULT_VOUCHER_CODE);
        assertThat(testUVFreeVoucher.isIsValid()).isEqualTo(DEFAULT_IS_VALID);
        assertThat(testUVFreeVoucher.isIsExpired()).isEqualTo(DEFAULT_IS_EXPIRED);
        assertThat(testUVFreeVoucher.isCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testUVFreeVoucher.getCreatedOn()).isEqualTo(DEFAULT_CREATED_ON);
        assertThat(testUVFreeVoucher.getLastUpdatedOn()).isEqualTo(DEFAULT_LAST_UPDATED_ON);
    }

    @Test
    @Transactional
    public void createUVFreeVoucherWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = uVFreeVoucherRepository.findAll().size();

        // Create the UVFreeVoucher with an existing ID
        uVFreeVoucher.setId(1L);
        UVFreeVoucherDTO uVFreeVoucherDTO = uVFreeVoucherMapper.toDto(uVFreeVoucher);

        // An entity with an existing ID cannot be created, so this API call must fail
        restUVFreeVoucherMockMvc.perform(post("/api/uv-free-vouchers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(uVFreeVoucherDTO)))
            .andExpect(status().isBadRequest());

        // Validate the UVFreeVoucher in the database
        List<UVFreeVoucher> uVFreeVoucherList = uVFreeVoucherRepository.findAll();
        assertThat(uVFreeVoucherList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllUVFreeVouchers() throws Exception {
        // Initialize the database
        uVFreeVoucherRepository.saveAndFlush(uVFreeVoucher);

        // Get all the uVFreeVoucherList
        restUVFreeVoucherMockMvc.perform(get("/api/uv-free-vouchers?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(uVFreeVoucher.getId().intValue())))
            .andExpect(jsonPath("$.[*].voucherCode").value(hasItem(DEFAULT_VOUCHER_CODE.toString())))
            .andExpect(jsonPath("$.[*].isValid").value(hasItem(DEFAULT_IS_VALID.booleanValue())))
            .andExpect(jsonPath("$.[*].isExpired").value(hasItem(DEFAULT_IS_EXPIRED.booleanValue())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY.booleanValue())))
            .andExpect(jsonPath("$.[*].createdOn").value(hasItem(DEFAULT_CREATED_ON.toString())))
            .andExpect(jsonPath("$.[*].lastUpdatedOn").value(hasItem(DEFAULT_LAST_UPDATED_ON.toString())));
    }

    @Test
    @Transactional
    public void getUVFreeVoucher() throws Exception {
        // Initialize the database
        uVFreeVoucherRepository.saveAndFlush(uVFreeVoucher);

        // Get the uVFreeVoucher
        restUVFreeVoucherMockMvc.perform(get("/api/uv-free-vouchers/{id}", uVFreeVoucher.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(uVFreeVoucher.getId().intValue()))
            .andExpect(jsonPath("$.voucherCode").value(DEFAULT_VOUCHER_CODE.toString()))
            .andExpect(jsonPath("$.isValid").value(DEFAULT_IS_VALID.booleanValue()))
            .andExpect(jsonPath("$.isExpired").value(DEFAULT_IS_EXPIRED.booleanValue()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY.booleanValue()))
            .andExpect(jsonPath("$.createdOn").value(DEFAULT_CREATED_ON.toString()))
            .andExpect(jsonPath("$.lastUpdatedOn").value(DEFAULT_LAST_UPDATED_ON.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingUVFreeVoucher() throws Exception {
        // Get the uVFreeVoucher
        restUVFreeVoucherMockMvc.perform(get("/api/uv-free-vouchers/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateUVFreeVoucher() throws Exception {
        // Initialize the database
        uVFreeVoucherRepository.saveAndFlush(uVFreeVoucher);
        int databaseSizeBeforeUpdate = uVFreeVoucherRepository.findAll().size();

        // Update the uVFreeVoucher
        UVFreeVoucher updatedUVFreeVoucher = uVFreeVoucherRepository.findOne(uVFreeVoucher.getId());
        // Disconnect from session so that the updates on updatedUVFreeVoucher are not directly saved in db
        em.detach(updatedUVFreeVoucher);
        updatedUVFreeVoucher
            .voucherCode(UPDATED_VOUCHER_CODE)
            .isValid(UPDATED_IS_VALID)
            .isExpired(UPDATED_IS_EXPIRED)
            .createdBy(UPDATED_CREATED_BY)
            .createdOn(UPDATED_CREATED_ON)
            .lastUpdatedOn(UPDATED_LAST_UPDATED_ON);
        UVFreeVoucherDTO uVFreeVoucherDTO = uVFreeVoucherMapper.toDto(updatedUVFreeVoucher);

        restUVFreeVoucherMockMvc.perform(put("/api/uv-free-vouchers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(uVFreeVoucherDTO)))
            .andExpect(status().isOk());

        // Validate the UVFreeVoucher in the database
        List<UVFreeVoucher> uVFreeVoucherList = uVFreeVoucherRepository.findAll();
        assertThat(uVFreeVoucherList).hasSize(databaseSizeBeforeUpdate);
        UVFreeVoucher testUVFreeVoucher = uVFreeVoucherList.get(uVFreeVoucherList.size() - 1);
        assertThat(testUVFreeVoucher.getVoucherCode()).isEqualTo(UPDATED_VOUCHER_CODE);
        assertThat(testUVFreeVoucher.isIsValid()).isEqualTo(UPDATED_IS_VALID);
        assertThat(testUVFreeVoucher.isIsExpired()).isEqualTo(UPDATED_IS_EXPIRED);
        assertThat(testUVFreeVoucher.isCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testUVFreeVoucher.getCreatedOn()).isEqualTo(UPDATED_CREATED_ON);
        assertThat(testUVFreeVoucher.getLastUpdatedOn()).isEqualTo(UPDATED_LAST_UPDATED_ON);
    }

    @Test
    @Transactional
    public void updateNonExistingUVFreeVoucher() throws Exception {
        int databaseSizeBeforeUpdate = uVFreeVoucherRepository.findAll().size();

        // Create the UVFreeVoucher
        UVFreeVoucherDTO uVFreeVoucherDTO = uVFreeVoucherMapper.toDto(uVFreeVoucher);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restUVFreeVoucherMockMvc.perform(put("/api/uv-free-vouchers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(uVFreeVoucherDTO)))
            .andExpect(status().isCreated());

        // Validate the UVFreeVoucher in the database
        List<UVFreeVoucher> uVFreeVoucherList = uVFreeVoucherRepository.findAll();
        assertThat(uVFreeVoucherList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteUVFreeVoucher() throws Exception {
        // Initialize the database
        uVFreeVoucherRepository.saveAndFlush(uVFreeVoucher);
        int databaseSizeBeforeDelete = uVFreeVoucherRepository.findAll().size();

        // Get the uVFreeVoucher
        restUVFreeVoucherMockMvc.perform(delete("/api/uv-free-vouchers/{id}", uVFreeVoucher.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<UVFreeVoucher> uVFreeVoucherList = uVFreeVoucherRepository.findAll();
        assertThat(uVFreeVoucherList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(UVFreeVoucher.class);
        UVFreeVoucher uVFreeVoucher1 = new UVFreeVoucher();
        uVFreeVoucher1.setId(1L);
        UVFreeVoucher uVFreeVoucher2 = new UVFreeVoucher();
        uVFreeVoucher2.setId(uVFreeVoucher1.getId());
        assertThat(uVFreeVoucher1).isEqualTo(uVFreeVoucher2);
        uVFreeVoucher2.setId(2L);
        assertThat(uVFreeVoucher1).isNotEqualTo(uVFreeVoucher2);
        uVFreeVoucher1.setId(null);
        assertThat(uVFreeVoucher1).isNotEqualTo(uVFreeVoucher2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(UVFreeVoucherDTO.class);
        UVFreeVoucherDTO uVFreeVoucherDTO1 = new UVFreeVoucherDTO();
        uVFreeVoucherDTO1.setId(1L);
        UVFreeVoucherDTO uVFreeVoucherDTO2 = new UVFreeVoucherDTO();
        assertThat(uVFreeVoucherDTO1).isNotEqualTo(uVFreeVoucherDTO2);
        uVFreeVoucherDTO2.setId(uVFreeVoucherDTO1.getId());
        assertThat(uVFreeVoucherDTO1).isEqualTo(uVFreeVoucherDTO2);
        uVFreeVoucherDTO2.setId(2L);
        assertThat(uVFreeVoucherDTO1).isNotEqualTo(uVFreeVoucherDTO2);
        uVFreeVoucherDTO1.setId(null);
        assertThat(uVFreeVoucherDTO1).isNotEqualTo(uVFreeVoucherDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(uVFreeVoucherMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(uVFreeVoucherMapper.fromId(null)).isNull();
    }
}
