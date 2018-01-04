package io.github.jhipster.application.service.mapper;

import io.github.jhipster.application.domain.*;
import io.github.jhipster.application.service.dto.UVSellUnusedVoucherDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity UVSellUnusedVoucher and its DTO UVSellUnusedVoucherDTO.
 */
@Mapper(componentModel = "spring", uses = {UVBrandMapper.class, UVCategoryMapper.class})
public interface UVSellUnusedVoucherMapper extends EntityMapper<UVSellUnusedVoucherDTO, UVSellUnusedVoucher> {

    @Mapping(source = "brand.id", target = "brandId")
    @Mapping(source = "category.id", target = "categoryId")
    UVSellUnusedVoucherDTO toDto(UVSellUnusedVoucher uVSellUnusedVoucher); 

    @Mapping(source = "brandId", target = "brand")
    @Mapping(source = "categoryId", target = "category")
    UVSellUnusedVoucher toEntity(UVSellUnusedVoucherDTO uVSellUnusedVoucherDTO);

    default UVSellUnusedVoucher fromId(Long id) {
        if (id == null) {
            return null;
        }
        UVSellUnusedVoucher uVSellUnusedVoucher = new UVSellUnusedVoucher();
        uVSellUnusedVoucher.setId(id);
        return uVSellUnusedVoucher;
    }
}
