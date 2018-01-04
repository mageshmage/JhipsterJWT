package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.UVBrand;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the UVBrand entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UVBrandRepository extends JpaRepository<UVBrand, Long> {

}
