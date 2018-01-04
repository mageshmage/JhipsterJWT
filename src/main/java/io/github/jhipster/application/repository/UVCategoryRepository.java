package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.UVCategory;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the UVCategory entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UVCategoryRepository extends JpaRepository<UVCategory, Long> {

}
