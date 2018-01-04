package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.UVBrandDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity UVBrand and its DTO UVBrandDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface UVBrandMapper extends EntityMapper<UVBrandDTO, UVBrand> {

    

    

    default UVBrand fromId(Long id) {
        if (id == null) {
            return null;
        }
        UVBrand uVBrand = new UVBrand();
        uVBrand.setId(id);
        return uVBrand;
    }
}
