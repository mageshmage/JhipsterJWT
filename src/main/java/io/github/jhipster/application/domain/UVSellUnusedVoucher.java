package io.github.jhipster.application.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * A UVSellUnusedVoucher.
 */
@Entity
@Table(name = "uv_sell_unused_voucher")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class UVSellUnusedVoucher implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "voucher_code")
    private String voucherCode;

    @Column(name = "is_valid")
    private Boolean isValid;

    @Column(name = "is_expired")
    private Boolean isExpired;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "created_on")
    private LocalDate createdOn;

    @Column(name = "last_updated_on")
    private LocalDate lastUpdatedOn;

    @OneToOne
    @JoinColumn(unique = true)
    private UVBrand brand;

    @OneToOne
    @JoinColumn(unique = true)
    private UVCategory category;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVoucherCode() {
        return voucherCode;
    }

    public UVSellUnusedVoucher voucherCode(String voucherCode) {
        this.voucherCode = voucherCode;
        return this;
    }

    public void setVoucherCode(String voucherCode) {
        this.voucherCode = voucherCode;
    }

    public Boolean isIsValid() {
        return isValid;
    }

    public UVSellUnusedVoucher isValid(Boolean isValid) {
        this.isValid = isValid;
        return this;
    }

    public void setIsValid(Boolean isValid) {
        this.isValid = isValid;
    }

    public Boolean isIsExpired() {
        return isExpired;
    }

    public UVSellUnusedVoucher isExpired(Boolean isExpired) {
        this.isExpired = isExpired;
        return this;
    }

    public void setIsExpired(Boolean isExpired) {
        this.isExpired = isExpired;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public UVSellUnusedVoucher createdBy(String createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDate getCreatedOn() {
        return createdOn;
    }

    public UVSellUnusedVoucher createdOn(LocalDate createdOn) {
        this.createdOn = createdOn;
        return this;
    }

    public void setCreatedOn(LocalDate createdOn) {
        this.createdOn = createdOn;
    }

    public LocalDate getLastUpdatedOn() {
        return lastUpdatedOn;
    }

    public UVSellUnusedVoucher lastUpdatedOn(LocalDate lastUpdatedOn) {
        this.lastUpdatedOn = lastUpdatedOn;
        return this;
    }

    public void setLastUpdatedOn(LocalDate lastUpdatedOn) {
        this.lastUpdatedOn = lastUpdatedOn;
    }

    public UVBrand getBrand() {
        return brand;
    }

    public UVSellUnusedVoucher brand(UVBrand uVBrand) {
        this.brand = uVBrand;
        return this;
    }

    public void setBrand(UVBrand uVBrand) {
        this.brand = uVBrand;
    }

    public UVCategory getCategory() {
        return category;
    }

    public UVSellUnusedVoucher category(UVCategory uVCategory) {
        this.category = uVCategory;
        return this;
    }

    public void setCategory(UVCategory uVCategory) {
        this.category = uVCategory;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UVSellUnusedVoucher uVSellUnusedVoucher = (UVSellUnusedVoucher) o;
        if (uVSellUnusedVoucher.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), uVSellUnusedVoucher.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "UVSellUnusedVoucher{" +
            "id=" + getId() +
            ", voucherCode='" + getVoucherCode() + "'" +
            ", isValid='" + isIsValid() + "'" +
            ", isExpired='" + isIsExpired() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", createdOn='" + getCreatedOn() + "'" +
            ", lastUpdatedOn='" + getLastUpdatedOn() + "'" +
            "}";
    }
}
