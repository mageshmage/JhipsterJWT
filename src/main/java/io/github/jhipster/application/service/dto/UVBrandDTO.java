package io.github.jhipster.application.service.dto;


import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the UVBrand entity.
 */
public class UVBrandDTO implements Serializable {

    private Long id;

    private String brandName;

    private String brandCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getBrandCode() {
        return brandCode;
    }

    public void setBrandCode(String brandCode) {
        this.brandCode = brandCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        UVBrandDTO uVBrandDTO = (UVBrandDTO) o;
        if(uVBrandDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), uVBrandDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "UVBrandDTO{" +
            "id=" + getId() +
            ", brandName='" + getBrandName() + "'" +
            ", brandCode='" + getBrandCode() + "'" +
            "}";
    }
}
