package io.github.jhipster.application.web.rest;

import com.codahale.metrics.annotation.Timed;
import io.github.jhipster.application.domain.UVUser;

import io.github.jhipster.application.repository.UVUserRepository;
import io.github.jhipster.application.web.rest.errors.BadRequestAlertException;
import io.github.jhipster.application.web.rest.util.HeaderUtil;
import io.github.jhipster.application.service.dto.UVUserDTO;
import io.github.jhipster.application.service.mapper.UVUserMapper;
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
 * REST controller for managing UVUser.
 */
@RestController
@RequestMapping("/api")
public class UVUserResource {

    private final Logger log = LoggerFactory.getLogger(UVUserResource.class);

    private static final String ENTITY_NAME = "uVUser";

    private final UVUserRepository uVUserRepository;

    private final UVUserMapper uVUserMapper;

    public UVUserResource(UVUserRepository uVUserRepository, UVUserMapper uVUserMapper) {
        this.uVUserRepository = uVUserRepository;
        this.uVUserMapper = uVUserMapper;
    }

    /**
     * POST  /uv-users : Create a new uVUser.
     *
     * @param uVUserDTO the uVUserDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new uVUserDTO, or with status 400 (Bad Request) if the uVUser has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/uv-users")
    @Timed
    public ResponseEntity<UVUserDTO> createUVUser(@RequestBody UVUserDTO uVUserDTO) throws URISyntaxException {
        log.debug("REST request to save UVUser : {}", uVUserDTO);
        if (uVUserDTO.getId() != null) {
            throw new BadRequestAlertException("A new uVUser cannot already have an ID", ENTITY_NAME, "idexists");
        }
        UVUser uVUser = uVUserMapper.toEntity(uVUserDTO);
        uVUser = uVUserRepository.save(uVUser);
        UVUserDTO result = uVUserMapper.toDto(uVUser);
        return ResponseEntity.created(new URI("/api/uv-users/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /uv-users : Updates an existing uVUser.
     *
     * @param uVUserDTO the uVUserDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated uVUserDTO,
     * or with status 400 (Bad Request) if the uVUserDTO is not valid,
     * or with status 500 (Internal Server Error) if the uVUserDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/uv-users")
    @Timed
    public ResponseEntity<UVUserDTO> updateUVUser(@RequestBody UVUserDTO uVUserDTO) throws URISyntaxException {
        log.debug("REST request to update UVUser : {}", uVUserDTO);
        if (uVUserDTO.getId() == null) {
            return createUVUser(uVUserDTO);
        }
        UVUser uVUser = uVUserMapper.toEntity(uVUserDTO);
        uVUser = uVUserRepository.save(uVUser);
        UVUserDTO result = uVUserMapper.toDto(uVUser);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, uVUserDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /uv-users : get all the uVUsers.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of uVUsers in body
     */
    @GetMapping("/uv-users")
    @Timed
    public List<UVUserDTO> getAllUVUsers() {
        log.debug("REST request to get all UVUsers");
        List<UVUser> uVUsers = uVUserRepository.findAll();
        return uVUserMapper.toDto(uVUsers);
        }

    /**
     * GET  /uv-users/:id : get the "id" uVUser.
     *
     * @param id the id of the uVUserDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the uVUserDTO, or with status 404 (Not Found)
     */
    @GetMapping("/uv-users/{id}")
    @Timed
    public ResponseEntity<UVUserDTO> getUVUser(@PathVariable Long id) {
        log.debug("REST request to get UVUser : {}", id);
        UVUser uVUser = uVUserRepository.findOne(id);
        UVUserDTO uVUserDTO = uVUserMapper.toDto(uVUser);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(uVUserDTO));
    }

    /**
     * DELETE  /uv-users/:id : delete the "id" uVUser.
     *
     * @param id the id of the uVUserDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/uv-users/{id}")
    @Timed
    public ResponseEntity<Void> deleteUVUser(@PathVariable Long id) {
        log.debug("REST request to delete UVUser : {}", id);
        uVUserRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
