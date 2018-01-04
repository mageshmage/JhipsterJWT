package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.UVCategoryDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity UVCategory and its DTO UVCategoryDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface UVCategoryMapper extends EntityMapper<UVCategoryDTO, UVCategory> {

    

    

    default UVCategory fromId(Long id) {
        if (id == null) {
            return null;
        }
        UVCategory uVCategory = new UVCategory();
        uVCategory.setId(id);
        return uVCategory;
    }
}
