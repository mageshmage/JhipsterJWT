package io.github.jhipster.application.web.rest;

import io.github.jhipster.application.JhipsterJwtApp;

import io.github.jhipster.application.domain.UVSellUnusedVoucher;
import io.github.jhipster.application.repository.UVSellUnusedVoucherRepository;
import io.github.jhipster.application.service.dto.UVSellUnusedVoucherDTO;
import io.github.jhipster.application.service.mapper.UVSellUnusedVoucherMapper;
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
 * Test class for the UVSellUnusedVoucherResource REST controller.
 *
 * @see UVSellUnusedVoucherResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhipsterJwtApp.class)
public class UVSellUnusedVoucherResourceIntTest {

    private static final String DEFAULT_VOUCHER_CODE = "AAAAAAAAAA";
    private static final String UPDATED_VOUCHER_CODE = "BBBBBBBBBB";

    private static final Boolean DEFAULT_IS_VALID = false;
    private static final Boolean UPDATED_IS_VALID = true;

    private static final Boolean DEFAULT_IS_EXPIRED = false;
    private static final Boolean UPDATED_IS_EXPIRED = true;

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_CREATED_ON = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_CREATED_ON = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_LAST_UPDATED_ON = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_LAST_UPDATED_ON = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private UVSellUnusedVoucherRepository uVSellUnusedVoucherRepository;

    @Autowired
    private UVSellUnusedVoucherMapper uVSellUnusedVoucherMapper;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restUVSellUnusedVoucherMockMvc;

    private UVSellUnusedVoucher uVSellUnusedVoucher;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final UVSellUnusedVoucherResource uVSellUnusedVoucherResource = new UVSellUnusedVoucherResource(uVSellUnusedVoucherRepository, uVSellUnusedVoucherMapper);
        this.restUVSellUnusedVoucherMockMvc = MockMvcBuilders.standaloneSetup(uVSellUnusedVoucherResource)
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
    public static UVSellUnusedVoucher createEntity(EntityManager em) {
        UVSellUnusedVoucher uVSellUnusedVoucher = new UVSellUnusedVoucher()
            .voucherCode(DEFAULT_VOUCHER_CODE)
            .isValid(DEFAULT_IS_VALID)
            .isExpired(DEFAULT_IS_EXPIRED)
            .createdBy(DEFAULT_CREATED_BY)
            .createdOn(DEFAULT_CREATED_ON)
            .lastUpdatedOn(DEFAULT_LAST_UPDATED_ON);
        return uVSellUnusedVoucher;
    }

    @Before
    public void initTest() {
        uVSellUnusedVoucher = createEntity(em);
    }

    @Test
    @Transactional
    public void createUVSellUnusedVoucher() throws Exception {
        int databaseSizeBeforeCreate = uVSellUnusedVoucherRepository.findAll().size();

        // Create the UVSellUnusedVoucher
        UVSellUnusedVoucherDTO uVSellUnusedVoucherDTO = uVSellUnusedVoucherMapper.toDto(uVSellUnusedVoucher);
        restUVSellUnusedVoucherMockMvc.perform(post("/api/uv-sell-unused-vouchers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(uVSellUnusedVoucherDTO)))
            .andExpect(status().isCreated());

        // Validate the UVSellUnusedVoucher in the database
        List<UVSellUnusedVoucher> uVSellUnusedVoucherList = uVSellUnusedVoucherRepository.findAll();
        assertThat(uVSellUnusedVoucherList).hasSize(databaseSizeBeforeCreate + 1);
        UVSellUnusedVoucher testUVSellUnusedVoucher = uVSellUnusedVoucherList.get(uVSellUnusedVoucherList.size() - 1);
        assertThat(testUVSellUnusedVoucher.getVoucherCode()).isEqualTo(DEFAULT_VOUCHER_CODE);
        assertThat(testUVSellUnusedVoucher.isIsValid()).isEqualTo(DEFAULT_IS_VALID);
        assertThat(testUVSellUnusedVoucher.isIsExpired()).isEqualTo(DEFAULT_IS_EXPIRED);
        assertThat(testUVSellUnusedVoucher.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testUVSellUnusedVoucher.getCreatedOn()).isEqualTo(DEFAULT_CREATED_ON);
        assertThat(testUVSellUnusedVoucher.getLastUpdatedOn()).isEqualTo(DEFAULT_LAST_UPDATED_ON);
    }

    @Test
    @Transactional
    public void createUVSellUnusedVoucherWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = uVSellUnusedVoucherRepository.findAll().size();

        // Create the UVSellUnusedVoucher with an existing ID
        uVSellUnusedVoucher.setId(1L);
        UVSellUnusedVoucherDTO uVSellUnusedVoucherDTO = uVSellUnusedVoucherMapper.toDto(uVSellUnusedVoucher);

        // An entity with an existing ID cannot be created, so this API call must fail
        restUVSellUnusedVoucherMockMvc.perform(post("/api/uv-sell-unused-vouchers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(uVSellUnusedVoucherDTO)))
            .andExpect(status().isBadRequest());

        // Validate the UVSellUnusedVoucher in the database
        List<UVSellUnusedVoucher> uVSellUnusedVoucherList = uVSellUnusedVoucherRepository.findAll();
        assertThat(uVSellUnusedVoucherList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllUVSellUnusedVouchers() throws Exception {
        // Initialize the database
        uVSellUnusedVoucherRepository.saveAndFlush(uVSellUnusedVoucher);

        // Get all the uVSellUnusedVoucherList
        restUVSellUnusedVoucherMockMvc.perform(get("/api/uv-sell-unused-vouchers?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(uVSellUnusedVoucher.getId().intValue())))
            .andExpect(jsonPath("$.[*].voucherCode").value(hasItem(DEFAULT_VOUCHER_CODE.toString())))
            .andExpect(jsonPath("$.[*].isValid").value(hasItem(DEFAULT_IS_VALID.booleanValue())))
            .andExpect(jsonPath("$.[*].isExpired").value(hasItem(DEFAULT_IS_EXPIRED.booleanValue())))
            .andExpect(jsonPath("$.[*].createdBy").value(hasItem(DEFAULT_CREATED_BY.toString())))
            .andExpect(jsonPath("$.[*].createdOn").value(hasItem(DEFAULT_CREATED_ON.toString())))
            .andExpect(jsonPath("$.[*].lastUpdatedOn").value(hasItem(DEFAULT_LAST_UPDATED_ON.toString())));
    }

    @Test
    @Transactional
    public void getUVSellUnusedVoucher() throws Exception {
        // Initialize the database
        uVSellUnusedVoucherRepository.saveAndFlush(uVSellUnusedVoucher);

        // Get the uVSellUnusedVoucher
        restUVSellUnusedVoucherMockMvc.perform(get("/api/uv-sell-unused-vouchers/{id}", uVSellUnusedVoucher.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(uVSellUnusedVoucher.getId().intValue()))
            .andExpect(jsonPath("$.voucherCode").value(DEFAULT_VOUCHER_CODE.toString()))
            .andExpect(jsonPath("$.isValid").value(DEFAULT_IS_VALID.booleanValue()))
            .andExpect(jsonPath("$.isExpired").value(DEFAULT_IS_EXPIRED.booleanValue()))
            .andExpect(jsonPath("$.createdBy").value(DEFAULT_CREATED_BY.toString()))
            .andExpect(jsonPath("$.createdOn").value(DEFAULT_CREATED_ON.toString()))
            .andExpect(jsonPath("$.lastUpdatedOn").value(DEFAULT_LAST_UPDATED_ON.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingUVSellUnusedVoucher() throws Exception {
        // Get the uVSellUnusedVoucher
        restUVSellUnusedVoucherMockMvc.perform(get("/api/uv-sell-unused-vouchers/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateUVSellUnusedVoucher() throws Exception {
        // Initialize the database
        uVSellUnusedVoucherRepository.saveAndFlush(uVSellUnusedVoucher);
        int databaseSizeBeforeUpdate = uVSellUnusedVoucherRepository.findAll().size();

        // Update the uVSellUnusedVoucher
        UVSellUnusedVoucher updatedUVSellUnusedVoucher = uVSellUnusedVoucherRepository.findOne(uVSellUnusedVoucher.getId());
        // Disconnect from session so that the updates on updatedUVSellUnusedVoucher are not directly saved in db
        em.detach(updatedUVSellUnusedVoucher);
        updatedUVSellUnusedVoucher
            .voucherCode(UPDATED_VOUCHER_CODE)
            .isValid(UPDATED_IS_VALID)
            .isExpired(UPDATED_IS_EXPIRED)
            .createdBy(UPDATED_CREATED_BY)
            .createdOn(UPDATED_CREATED_ON)
            .lastUpdatedOn(UPDATED_LAST_UPDATED_ON);
        UVSellUnusedVoucherDTO uVSellUnusedVoucherDTO = uVSellUnusedVoucherMapper.toDto(updatedUVSellUnusedVoucher);

        restUVSellUnusedVoucherMockMvc.perform(put("/api/uv-sell-unused-vouchers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(uVSellUnusedVoucherDTO)))
            .andExpect(status().isOk());

        // Validate the UVSellUnusedVoucher in the database
        List<UVSellUnusedVoucher> uVSellUnusedVoucherList = uVSellUnusedVoucherRepository.findAll();
        assertThat(uVSellUnusedVoucherList).hasSize(databaseSizeBeforeUpdate);
        UVSellUnusedVoucher testUVSellUnusedVoucher = uVSellUnusedVoucherList.get(uVSellUnusedVoucherList.size() - 1);
        assertThat(testUVSellUnusedVoucher.getVoucherCode()).isEqualTo(UPDATED_VOUCHER_CODE);
        assertThat(testUVSellUnusedVoucher.isIsValid()).isEqualTo(UPDATED_IS_VALID);
        assertThat(testUVSellUnusedVoucher.isIsExpired()).isEqualTo(UPDATED_IS_EXPIRED);
        assertThat(testUVSellUnusedVoucher.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testUVSellUnusedVoucher.getCreatedOn()).isEqualTo(UPDATED_CREATED_ON);
        assertThat(testUVSellUnusedVoucher.getLastUpdatedOn()).isEqualTo(UPDATED_LAST_UPDATED_ON);
    }

    @Test
    @Transactional
    public void updateNonExistingUVSellUnusedVoucher() throws Exception {
        int databaseSizeBeforeUpdate = uVSellUnusedVoucherRepository.findAll().size();

        // Create the UVSellUnusedVoucher
        UVSellUnusedVoucherDTO uVSellUnusedVoucherDTO = uVSellUnusedVoucherMapper.toDto(uVSellUnusedVoucher);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restUVSellUnusedVoucherMockMvc.perform(put("/api/uv-sell-unused-vouchers")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(uVSellUnusedVoucherDTO)))
            .andExpect(status().isCreated());

        // Validate the UVSellUnusedVoucher in the database
        List<UVSellUnusedVoucher> uVSellUnusedVoucherList = uVSellUnusedVoucherRepository.findAll();
        assertThat(uVSellUnusedVoucherList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteUVSellUnusedVoucher() throws Exception {
        // Initialize the database
        uVSellUnusedVoucherRepository.saveAndFlush(uVSellUnusedVoucher);
        int databaseSizeBeforeDelete = uVSellUnusedVoucherRepository.findAll().size();

        // Get the uVSellUnusedVoucher
        restUVSellUnusedVoucherMockMvc.perform(delete("/api/uv-sell-unused-vouchers/{id}", uVSellUnusedVoucher.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<UVSellUnusedVoucher> uVSellUnusedVoucherList = uVSellUnusedVoucherRepository.findAll();
        assertThat(uVSellUnusedVoucherList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(UVSellUnusedVoucher.class);
        UVSellUnusedVoucher uVSellUnusedVoucher1 = new UVSellUnusedVoucher();
        uVSellUnusedVoucher1.setId(1L);
        UVSellUnusedVoucher uVSellUnusedVoucher2 = new UVSellUnusedVoucher();
        uVSellUnusedVoucher2.setId(uVSellUnusedVoucher1.getId());
        assertThat(uVSellUnusedVoucher1).isEqualTo(uVSellUnusedVoucher2);
        uVSellUnusedVoucher2.setId(2L);
        assertThat(uVSellUnusedVoucher1).isNotEqualTo(uVSellUnusedVoucher2);
        uVSellUnusedVoucher1.setId(null);
        assertThat(uVSellUnusedVoucher1).isNotEqualTo(uVSellUnusedVoucher2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(UVSellUnusedVoucherDTO.class);
        UVSellUnusedVoucherDTO uVSellUnusedVoucherDTO1 = new UVSellUnusedVoucherDTO();
        uVSellUnusedVoucherDTO1.setId(1L);
        UVSellUnusedVoucherDTO uVSellUnusedVoucherDTO2 = new UVSellUnusedVoucherDTO();
        assertThat(uVSellUnusedVoucherDTO1).isNotEqualTo(uVSellUnusedVoucherDTO2);
        uVSellUnusedVoucherDTO2.setId(uVSellUnusedVoucherDTO1.getId());
        assertThat(uVSellUnusedVoucherDTO1).isEqualTo(uVSellUnusedVoucherDTO2);
        uVSellUnusedVoucherDTO2.setId(2L);
        assertThat(uVSellUnusedVoucherDTO1).isNotEqualTo(uVSellUnusedVoucherDTO2);
        uVSellUnusedVoucherDTO1.setId(null);
        assertThat(uVSellUnusedVoucherDTO1).isNotEqualTo(uVSellUnusedVoucherDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(uVSellUnusedVoucherMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(uVSellUnusedVoucherMapper.fromId(null)).isNull();
    }
}
