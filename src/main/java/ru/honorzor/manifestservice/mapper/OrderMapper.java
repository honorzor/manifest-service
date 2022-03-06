package ru.honorzor.manifestservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.honorzor.manifestservice.dto.ProductDTO;
import ru.honorzor.manifestservice.entity.ProductEntity;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    ProductEntity toEntity(ProductDTO productDTO);

    default List<ProductEntity> toEntities(List<ProductDTO> products) {
        return products.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

}
