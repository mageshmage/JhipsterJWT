package io.github.jhipster.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.application.domain.UVSellUnusedVoucher;

import io.github.jhipster.application.repository.UVSellUnusedVoucherRepository;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.web.rest.util.HeaderUtil;
import io.github.jhipster.application.web.rest.util.PaginationUtil;
import io.github.jhipster.application.service.dto.UVSellUnusedVoucherDTO;
import io.github.jhipster.application.service.mapper.UVSellUnusedVoucherMapper;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing UVSellUnusedVoucher.
 */
@RestController
@RequestMapping("/api")
public class UVSellUnusedVoucherResource {

    private final Logger log = LoggerFactory.getLogger(UVSellUnusedVoucherResource.class);

    private static final String ENTITY_NAME = "uVSellUnusedVoucher";

    private final UVSellUnusedVoucherRepository uVSellUnusedVoucherRepository;

    private final UVSellUnusedVoucherMapper uVSellUnusedVoucherMapper;

    public UVSellUnusedVoucherResource(UVSellUnusedVoucherRepository uVSellUnusedVoucherRepository, UVSellUnusedVoucherMapper uVSellUnusedVoucherMapper) {
        this.uVSellUnusedVoucherRepository = uVSellUnusedVoucherRepository;
        this.uVSellUnusedVoucherMapper = uVSellUnusedVoucherMapper;
    }

    /**
     * POST  /uv-sell-unused-vouchers : Create a new uVSellUnusedVoucher.
     *
     * @param uVSellUnusedVoucherDTO the uVSellUnusedVoucherDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new uVSellUnusedVoucherDTO, or with status 400 (Bad Request) if the uVSellUnusedVoucher has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/uv-sell-unused-vouchers")
    @Timed
    public ResponseEntity<UVSellUnusedVoucherDTO> createUVSellUnusedVoucher(@RequestBody UVSellUnusedVoucherDTO uVSellUnusedVoucherDTO) throws URISyntaxException {
        log.debug("REST request to save UVSellUnusedVoucher : {}", uVSellUnusedVoucherDTO);
        if (uVSellUnusedVoucherDTO.getId() != null) {
            throw new BadRequestAlertException("A new uVSellUnusedVoucher cannot already have an ID", ENTITY_NAME, "idexists");
        }
        UVSellUnusedVoucher uVSellUnusedVoucher = uVSellUnusedVoucherMapper.toEntity(uVSellUnusedVoucherDTO);
        uVSellUnusedVoucher = uVSellUnusedVoucherRepository.save(uVSellUnusedVoucher);
        UVSellUnusedVoucherDTO result = uVSellUnusedVoucherMapper.toDto(uVSellUnusedVoucher);
        return ResponseEntity.created(new URI("/api/uv-sell-unused-vouchers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /uv-sell-unused-vouchers : Updates an existing uVSellUnusedVoucher.
     *
     * @param uVSellUnusedVoucherDTO the uVSellUnusedVoucherDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated uVSellUnusedVoucherDTO,
     * or with status 400 (Bad Request) if the uVSellUnusedVoucherDTO is not valid,
     * or with status 500 (Internal Server Error) if the uVSellUnusedVoucherDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/uv-sell-unused-vouchers")
    @Timed
    public ResponseEntity<UVSellUnusedVoucherDTO> updateUVSellUnusedVoucher(@RequestBody UVSellUnusedVoucherDTO uVSellUnusedVoucherDTO) throws URISyntaxException {
        log.debug("REST request to update UVSellUnusedVoucher : {}", uVSellUnusedVoucherDTO);
        if (uVSellUnusedVoucherDTO.getId() == null) {
            return createUVSellUnusedVoucher(uVSellUnusedVoucherDTO);
        }
        UVSellUnusedVoucher uVSellUnusedVoucher = uVSellUnusedVoucherMapper.toEntity(uVSellUnusedVoucherDTO);
        uVSellUnusedVoucher = uVSellUnusedVoucherRepository.save(uVSellUnusedVoucher);
        UVSellUnusedVoucherDTO result = uVSellUnusedVoucherMapper.toDto(uVSellUnusedVoucher);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, uVSellUnusedVoucherDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /uv-sell-unused-vouchers : get all the uVSellUnusedVouchers.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of uVSellUnusedVouchers in body
     */
    @GetMapping("/uv-sell-unused-vouchers")
    @Timed
    public ResponseEntity<List<UVSellUnusedVoucherDTO>> getAllUVSellUnusedVouchers(Pageable pageable) {
        log.debug("REST request to get a page of UVSellUnusedVouchers");
        Page<UVSellUnusedVoucher> page = uVSellUnusedVoucherRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/uv-sell-unused-vouchers");
        return new ResponseEntity<>(uVSellUnusedVoucherMapper.toDto(page.getContent()), headers, HttpStatus.OK);
    }

    /**
     * GET  /uv-sell-unused-vouchers/:id : get the "id" uVSellUnusedVoucher.
     *
     * @param id the id of the uVSellUnusedVoucherDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the uVSellUnusedVoucherDTO, or with status 404 (Not Found)
     */
    @GetMapping("/uv-sell-unused-vouchers/{id}")
    @Timed
    public ResponseEntity<UVSellUnusedVoucherDTO> getUVSellUnusedVoucher(@PathVariable Long id) {
        log.debug("REST request to get UVSellUnusedVoucher : {}", id);
        UVSellUnusedVoucher uVSellUnusedVoucher = uVSellUnusedVoucherRepository.findOne(id);
        UVSellUnusedVoucherDTO uVSellUnusedVoucherDTO = uVSellUnusedVoucherMapper.toDto(uVSellUnusedVoucher);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(uVSellUnusedVoucherDTO));
    }

    /**
     * DELETE  /uv-sell-unused-vouchers/:id : delete the "id" uVSellUnusedVoucher.
     *
     * @param id the id of the uVSellUnusedVoucherDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/uv-sell-unused-vouchers/{id}")
    @Timed
    public ResponseEntity<Void> deleteUVSellUnusedVoucher(@PathVariable Long id) {
        log.debug("REST request to delete UVSellUnusedVoucher : {}", id);
        uVSellUnusedVoucherRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
