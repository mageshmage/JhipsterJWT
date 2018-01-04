package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.UVFreeVoucher;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the UVFreeVoucher entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UVFreeVoucherRepository extends JpaRepository<UVFreeVoucher, Long> {

}
