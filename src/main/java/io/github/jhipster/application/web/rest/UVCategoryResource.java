package io.github.jhipster.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.application.domain.UVCategory;

import io.github.jhipster.application.repository.UVCategoryRepository;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.web.rest.util.HeaderUtil;
import io.github.jhipster.application.service.dto.UVCategoryDTO;
import io.github.jhipster.application.service.mapper.UVCategoryMapper;
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
 * REST controller for managing UVCategory.
 */
@RestController
@RequestMapping("/api")
public class UVCategoryResource {

    private final Logger log = LoggerFactory.getLogger(UVCategoryResource.class);

    private static final String ENTITY_NAME = "uVCategory";

    private final UVCategoryRepository uVCategoryRepository;

    private final UVCategoryMapper uVCategoryMapper;

    public UVCategoryResource(UVCategoryRepository uVCategoryRepository, UVCategoryMapper uVCategoryMapper) {
        this.uVCategoryRepository = uVCategoryRepository;
        this.uVCategoryMapper = uVCategoryMapper;
    }

    /**
     * POST  /uv-categories : Create a new uVCategory.
     *
     * @param uVCategoryDTO the uVCategoryDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new uVCategoryDTO, or with status 400 (Bad Request) if the uVCategory has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/uv-categories")
    @Timed
    public ResponseEntity<UVCategoryDTO> createUVCategory(@RequestBody UVCategoryDTO uVCategoryDTO) throws URISyntaxException {
        log.debug("REST request to save UVCategory : {}", uVCategoryDTO);
        if (uVCategoryDTO.getId() != null) {
            throw new BadRequestAlertException("A new uVCategory cannot already have an ID", ENTITY_NAME, "idexists");
        }
        UVCategory uVCategory = uVCategoryMapper.toEntity(uVCategoryDTO);
        uVCategory = uVCategoryRepository.save(uVCategory);
        UVCategoryDTO result = uVCategoryMapper.toDto(uVCategory);
        return ResponseEntity.created(new URI("/api/uv-categories/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /uv-categories : Updates an existing uVCategory.
     *
     * @param uVCategoryDTO the uVCategoryDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated uVCategoryDTO,
     * or with status 400 (Bad Request) if the uVCategoryDTO is not valid,
     * or with status 500 (Internal Server Error) if the uVCategoryDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/uv-categories")
    @Timed
    public ResponseEntity<UVCategoryDTO> updateUVCategory(@RequestBody UVCategoryDTO uVCategoryDTO) throws URISyntaxException {
        log.debug("REST request to update UVCategory : {}", uVCategoryDTO);
        if (uVCategoryDTO.getId() == null) {
            return createUVCategory(uVCategoryDTO);
        }
        UVCategory uVCategory = uVCategoryMapper.toEntity(uVCategoryDTO);
        uVCategory = uVCategoryRepository.save(uVCategory);
        UVCategoryDTO result = uVCategoryMapper.toDto(uVCategory);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, uVCategoryDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /uv-categories : get all the uVCategories.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of uVCategories in body
     */
    @GetMapping("/uv-categories")
    @Timed
    public List<UVCategoryDTO> getAllUVCategories() {
        log.debug("REST request to get all UVCategories");
        List<UVCategory> uVCategories = uVCategoryRepository.findAll();
        return uVCategoryMapper.toDto(uVCategories);
        }

    /**
     * GET  /uv-categories/:id : get the "id" uVCategory.
     *
     * @param id the id of the uVCategoryDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the uVCategoryDTO, or with status 404 (Not Found)
     */
    @GetMapping("/uv-categories/{id}")
    @Timed
    public ResponseEntity<UVCategoryDTO> getUVCategory(@PathVariable Long id) {
        log.debug("REST request to get UVCategory : {}", id);
        UVCategory uVCategory = uVCategoryRepository.findOne(id);
        UVCategoryDTO uVCategoryDTO = uVCategoryMapper.toDto(uVCategory);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(uVCategoryDTO));
    }

    /**
     * DELETE  /uv-categories/:id : delete the "id" uVCategory.
     *
     * @param id the id of the uVCategoryDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/uv-categories/{id}")
    @Timed
    public ResponseEntity<Void> deleteUVCategory(@PathVariable Long id) {
        log.debug("REST request to delete UVCategory : {}", id);
        uVCategoryRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
