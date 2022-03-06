package ru.honorzor.manifestservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.honorzor.manifestservice.dto.ProductDTO;
import ru.honorzor.manifestservice.entity.ProductEntity;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    ProductEntity toEntities(ProductDTO productDTO);

    ProductDTO toDTO(ProductEntity productEntity);

    default List<ProductEntity> toEntities(List<ProductDTO> products) {
        return products.stream()
                .map(this::toEntities)
                .collect(Collectors.toList());
    }

    default List<ProductDTO> toDTO(List<ProductEntity> products) {
        return products.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}
