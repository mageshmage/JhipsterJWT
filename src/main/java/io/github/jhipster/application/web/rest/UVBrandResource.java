package io.github.jhipster.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.application.domain.UVBrand;

import io.github.jhipster.application.repository.UVBrandRepository;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.web.rest.util.HeaderUtil;
import io.github.jhipster.application.service.dto.UVBrandDTO;
import io.github.jhipster.application.service.mapper.UVBrandMapper;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing UVBrand.
 */
@RestController
@RequestMapping("/api")
public class UVBrandResource {

    private final Logger log = LoggerFactory.getLogger(UVBrandResource.class);

    private static final String ENTITY_NAME = "uVBrand";

    private final UVBrandRepository uVBrandRepository;

    private final UVBrandMapper uVBrandMapper;

    public UVBrandResource(UVBrandRepository uVBrandRepository, UVBrandMapper uVBrandMapper) {
        this.uVBrandRepository = uVBrandRepository;
        this.uVBrandMapper = uVBrandMapper;
    }

    /**
     * POST  /uv-brands : Create a new uVBrand.
     *
     * @param uVBrandDTO the uVBrandDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new uVBrandDTO, or with status 400 (Bad Request) if the uVBrand has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/uv-brands")
    @Timed
    public ResponseEntity<UVBrandDTO> createUVBrand(@RequestBody UVBrandDTO uVBrandDTO) throws URISyntaxException {
        log.debug("REST request to save UVBrand : {}", uVBrandDTO);
        if (uVBrandDTO.getId() != null) {
            throw new BadRequestAlertException("A new uVBrand cannot already have an ID", ENTITY_NAME, "idexists");
        }
        UVBrand uVBrand = uVBrandMapper.toEntity(uVBrandDTO);
        uVBrand = uVBrandRepository.save(uVBrand);
        UVBrandDTO result = uVBrandMapper.toDto(uVBrand);
        return ResponseEntity.created(new URI("/api/uv-brands/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /uv-brands : Updates an existing uVBrand.
     *
     * @param uVBrandDTO the uVBrandDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated uVBrandDTO,
     * or with status 400 (Bad Request) if the uVBrandDTO is not valid,
     * or with status 500 (Internal Server Error) if the uVBrandDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/uv-brands")
    @Timed
    public ResponseEntity<UVBrandDTO> updateUVBrand(@RequestBody UVBrandDTO uVBrandDTO) throws URISyntaxException {
        log.debug("REST request to update UVBrand : {}", uVBrandDTO);
        if (uVBrandDTO.getId() == null) {
            return createUVBrand(uVBrandDTO);
        }
        UVBrand uVBrand = uVBrandMapper.toEntity(uVBrandDTO);
        uVBrand = uVBrandRepository.save(uVBrand);
        UVBrandDTO result = uVBrandMapper.toDto(uVBrand);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, uVBrandDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /uv-brands : get all the uVBrands.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of uVBrands in body
     */
    @GetMapping("/uv-brands")
    @Timed
    public List<UVBrandDTO> getAllUVBrands() {
        log.debug("REST request to get all UVBrands");
        List<UVBrand> uVBrands = uVBrandRepository.findAll();
        return uVBrandMapper.toDto(uVBrands);
        }

    /**
     * GET  /uv-brands/:id : get the "id" uVBrand.
     *
     * @param id the id of the uVBrandDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the uVBrandDTO, or with status 404 (Not Found)
     */
    @GetMapping("/uv-brands/{id}")
    @Timed
    public ResponseEntity<UVBrandDTO> getUVBrand(@PathVariable Long id) {
        log.debug("REST request to get UVBrand : {}", id);
        UVBrand uVBrand = uVBrandRepository.findOne(id);
        UVBrandDTO uVBrandDTO = uVBrandMapper.toDto(uVBrand);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(uVBrandDTO));
    }

    /**
     * DELETE  /uv-brands/:id : delete the "id" uVBrand.
     *
     * @param id the id of the uVBrandDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/uv-brands/{id}")
    @Timed
    public ResponseEntity<Void> deleteUVBrand(@PathVariable Long id) {
        log.debug("REST request to delete UVBrand : {}", id);
        uVBrandRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
