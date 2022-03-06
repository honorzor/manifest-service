package ru.honorzor.manifestservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.honorzor.manifestservice.dto.CellDTO;
import ru.honorzor.manifestservice.entity.CellEntity;

@Mapper(componentModel = "spring")
public interface CellMapper {

    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    CellDTO toDTO(CellEntity cellEntity);
}
