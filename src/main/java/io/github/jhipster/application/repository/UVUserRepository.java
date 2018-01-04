package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.UVUser;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the UVUser entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UVUserRepository extends JpaRepository<UVUser, Long> {

}
