package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.UVFreeVoucherDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity UVFreeVoucher and its DTO UVFreeVoucherDTO.
 */
@Mapper(componentModel = "spring", uses = {UVBrandMapper.class, UVCategoryMapper.class})
public interface UVFreeVoucherMapper extends EntityMapper<UVFreeVoucherDTO, UVFreeVoucher> {

    @Mapping(source = "brand.id", target = "brandId")
    @Mapping(source = "brand.id", target = "brandId")
    UVFreeVoucherDTO toDto(UVFreeVoucher uVFreeVoucher); 

    @Mapping(source = "brandId", target = "brand")
    @Mapping(source = "brandId", target = "brand")
    UVFreeVoucher toEntity(UVFreeVoucherDTO uVFreeVoucherDTO);

    default UVFreeVoucher fromId(Long id) {
        if (id == null) {
            return null;
        }
        UVFreeVoucher uVFreeVoucher = new UVFreeVoucher();
        uVFreeVoucher.setId(id);
        return uVFreeVoucher;
    }
}
