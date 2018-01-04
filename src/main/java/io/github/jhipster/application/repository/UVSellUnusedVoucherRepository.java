package io.github.jhipster.application.repository;

import io.github.jhipster.application.domain.UVSellUnusedVoucher;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the UVSellUnusedVoucher entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UVSellUnusedVoucherRepository extends JpaRepository<UVSellUnusedVoucher, Long> {

}
