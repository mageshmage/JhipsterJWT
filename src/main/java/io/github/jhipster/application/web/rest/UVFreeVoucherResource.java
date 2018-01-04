package io.github.jhipster.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.application.domain.UVFreeVoucher;

import io.github.jhipster.application.repository.UVFreeVoucherRepository;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.web.rest.util.HeaderUtil;
import io.github.jhipster.application.web.rest.util.PaginationUtil;
import io.github.jhipster.application.service.dto.UVFreeVoucherDTO;
import io.github.jhipster.application.service.mapper.UVFreeVoucherMapper;
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
 * REST controller for managing UVFreeVoucher.
 */
@RestController
@RequestMapping("/api")
public class UVFreeVoucherResource {

    private final Logger log = LoggerFactory.getLogger(UVFreeVoucherResource.class);

    private static final String ENTITY_NAME = "uVFreeVoucher";

    private final UVFreeVoucherRepository uVFreeVoucherRepository;

    private final UVFreeVoucherMapper uVFreeVoucherMapper;

    public UVFreeVoucherResource(UVFreeVoucherRepository uVFreeVoucherRepository, UVFreeVoucherMapper uVFreeVoucherMapper) {
        this.uVFreeVoucherRepository = uVFreeVoucherRepository;
        this.uVFreeVoucherMapper = uVFreeVoucherMapper;
    }

    /**
     * POST  /uv-free-vouchers : Create a new uVFreeVoucher.
     *
     * @param uVFreeVoucherDTO the uVFreeVoucherDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new uVFreeVoucherDTO, or with status 400 (Bad Request) if the uVFreeVoucher has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/uv-free-vouchers")
    @Timed
    public ResponseEntity<UVFreeVoucherDTO> createUVFreeVoucher(@RequestBody UVFreeVoucherDTO uVFreeVoucherDTO) throws URISyntaxException {
        log.debug("REST request to save UVFreeVoucher : {}", uVFreeVoucherDTO);
        if (uVFreeVoucherDTO.getId() != null) {
            throw new BadRequestAlertException("A new uVFreeVoucher cannot already have an ID", ENTITY_NAME, "idexists");
        }
        UVFreeVoucher uVFreeVoucher = uVFreeVoucherMapper.toEntity(uVFreeVoucherDTO);
        uVFreeVoucher = uVFreeVoucherRepository.save(uVFreeVoucher);
        UVFreeVoucherDTO result = uVFreeVoucherMapper.toDto(uVFreeVoucher);
        return ResponseEntity.created(new URI("/api/uv-free-vouchers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /uv-free-vouchers : Updates an existing uVFreeVoucher.
     *
     * @param uVFreeVoucherDTO the uVFreeVoucherDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated uVFreeVoucherDTO,
     * or with status 400 (Bad Request) if the uVFreeVoucherDTO is not valid,
     * or with status 500 (Internal Server Error) if the uVFreeVoucherDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/uv-free-vouchers")
    @Timed
    public ResponseEntity<UVFreeVoucherDTO> updateUVFreeVoucher(@RequestBody UVFreeVoucherDTO uVFreeVoucherDTO) throws URISyntaxException {
        log.debug("REST request to update UVFreeVoucher : {}", uVFreeVoucherDTO);
        if (uVFreeVoucherDTO.getId() == null) {
            return createUVFreeVoucher(uVFreeVoucherDTO);
        }
        UVFreeVoucher uVFreeVoucher = uVFreeVoucherMapper.toEntity(uVFreeVoucherDTO);
        uVFreeVoucher = uVFreeVoucherRepository.save(uVFreeVoucher);
        UVFreeVoucherDTO result = uVFreeVoucherMapper.toDto(uVFreeVoucher);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, uVFreeVoucherDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /uv-free-vouchers : get all the uVFreeVouchers.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of uVFreeVouchers in body
     */
    @GetMapping("/uv-free-vouchers")
    @Timed
    public ResponseEntity<List<UVFreeVoucherDTO>> getAllUVFreeVouchers(Pageable pageable) {
        log.debug("REST request to get a page of UVFreeVouchers");
        Page<UVFreeVoucher> page = uVFreeVoucherRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/uv-free-vouchers");
        return new ResponseEntity<>(uVFreeVoucherMapper.toDto(page.getContent()), headers, HttpStatus.OK);
    }

    /**
     * GET  /uv-free-vouchers/:id : get the "id" uVFreeVoucher.
     *
     * @param id the id of the uVFreeVoucherDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the uVFreeVoucherDTO, or with status 404 (Not Found)
     */
    @GetMapping("/uv-free-vouchers/{id}")
    @Timed
    public ResponseEntity<UVFreeVoucherDTO> getUVFreeVoucher(@PathVariable Long id) {
        log.debug("REST request to get UVFreeVoucher : {}", id);
        UVFreeVoucher uVFreeVoucher = uVFreeVoucherRepository.findOne(id);
        UVFreeVoucherDTO uVFreeVoucherDTO = uVFreeVoucherMapper.toDto(uVFreeVoucher);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(uVFreeVoucherDTO));
    }

    /**
     * DELETE  /uv-free-vouchers/:id : delete the "id" uVFreeVoucher.
     *
     * @param id the id of the uVFreeVoucherDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/uv-free-vouchers/{id}")
    @Timed
    public ResponseEntity<Void> deleteUVFreeVoucher(@PathVariable Long id) {
        log.debug("REST request to delete UVFreeVoucher : {}", id);
        uVFreeVoucherRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
