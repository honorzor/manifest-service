package ru.honorzor.manifestservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.honorzor.manifestservice.dto.CellDTO;
import ru.honorzor.manifestservice.entity.CellEntity;
import ru.honorzor.manifestservice.mapper.CellMapper;
import ru.honorzor.manifestservice.repository.CellRepository;

import javax.transaction.Transactional;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CellService {
    private final CellRepository cellRepository;
    private final CellMapper cellMapper;

    @Transactional
    public void save(CellEntity cellEntity) {
        cellRepository.save(cellEntity);
    }

    @Transactional
    public Optional<CellDTO> minusCountFromCell(CellDTO cellDTO) {
        final Optional<CellEntity> cell = cellRepository.findById(cellDTO.getCell_id());
        if (cell.isPresent()) {
            final CellEntity cellEntity = cell.get();
            if (!Objects.equals(cellEntity.getCode(), cellDTO.getCode())) {
                throw new RuntimeException("You cannot minus count, please check your code");
            }
            if (cellEntity.getCount() < cellDTO.getCount()) {
                throw new RuntimeException("Need add count of product in cell");
            }
            log.info("Cell: {} before minus count: {}", cellEntity.getId(), cellEntity.getCount());
            cellEntity.setCount(cellEntity.getCount() - cellDTO.getCount());
            updateCount(cellEntity);
            log.info("Cell: {} after minus count: {}", cellEntity.getId(), cellEntity.getCount());
            return Optional.of(cellMapper.toDTO(cellEntity));
        }
        return Optional.empty();
    }

    @Transactional
    public void updateCount(CellEntity cellEntity) {
        cellRepository.save(cellEntity);
    }

    @Transactional
    public Optional<CellDTO> addCountInCell(CellDTO cellDTO) {
        final Optional<CellEntity> cellEntity = cellRepository.findById(cellDTO.getCell_id());
        if (cellEntity.isPresent()) {
            final CellEntity cell = cellEntity.get();
            if (!Objects.equals(cell.getCode(), cellDTO.getCode())) {
                throw new RuntimeException("You cannot add new item in current cell, please check your code");
            }
            log.info("Cell: {} before plus count: {}", cell.getId(), cell.getCount());
            cell.setCount(cell.getCount() + cellDTO.getCount());
            updateCount(cell);
            log.info("Cell: {} after plus count: {}", cell.getId(), cell.getCount());
            return Optional.of(cellMapper.toDTO(cell));
        }
        return Optional.empty();
    }

    @Transactional
    public Long getCellIdByCode(Long code) {
        return cellRepository.getCellIdByCode(code);
    }
}
