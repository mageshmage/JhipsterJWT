package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.UVUserDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity UVUser and its DTO UVUserDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface UVUserMapper extends EntityMapper<UVUserDTO, UVUser> {

    

    

    default UVUser fromId(Long id) {
        if (id == null) {
            return null;
        }
        UVUser uVUser = new UVUser();
        uVUser.setId(id);
        return uVUser;
    }
}
