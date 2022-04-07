package ru.honorzor.manifestservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.honorzor.manifestservice.dto.CellDTO;
import ru.honorzor.manifestservice.entity.CellEntity;
import ru.honorzor.manifestservice.enums.CellState;
import ru.honorzor.manifestservice.exception.cell.NotEnoughCountException;
import ru.honorzor.manifestservice.exception.cell.NotEqualCodeException;
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
    public Optional<CellDTO> minusCountFromCell(CellDTO cellDTO) {
        final Optional<CellEntity> cell = cellRepository.findById(cellDTO.getId());
        if (cell.isPresent()) {
            final CellEntity cellEntity = cell.get();
            if (!Objects.equals(cellEntity.getCode(), cellDTO.getCode())) {
                throw new NotEqualCodeException("your code not equal code in cell, try again");
            }
            if (cellEntity.getCount() < cellDTO.getCount()) {
                throw new NotEnoughCountException("You cannot get count greater than in cell, try again");
            }
            log.info("Cell: {} before minus count: {}", cellEntity.getId(), cellEntity.getCount());
            cellEntity.setCount(cellEntity.getCount() - cellDTO.getCount());

            if (cellEntity.getCount() == 0) {
                cellEntity.setCellState(CellState.EMPTY);
                cellEntity.setCode(0L);
            }

            save(cellEntity);
            log.info("Cell: {} after minus count: {}", cellEntity.getId(), cellEntity.getCount());
            return Optional.of(cellMapper.toDTO(cellEntity));
        }
        return Optional.empty();
    }

    @Transactional
    public Optional<CellDTO> addCountInCell(CellDTO cellDTO) {
        final Optional<CellEntity> cellEntity = cellRepository.findById(cellDTO.getId());
        if (cellEntity.isPresent()) {
            final CellEntity cell = cellEntity.get();
            if (!Objects.equals(cell.getCode(), cellDTO.getCode())) {
                throw new NotEqualCodeException("your code not equal code in cell, try again");
            }
            log.info("Cell: {} before plus count: {}", cell.getId(), cell.getCount());
            cell.setCount(cell.getCount() + cellDTO.getCount());
            save(cell);
            log.info("Cell: {} after plus count: {}", cell.getId(), cell.getCount());
            return Optional.of(cellMapper.toDTO(cell));
        }
        return Optional.empty();
    }

    @Transactional
    public Long getCellIdByCode(Long code) {
        return cellRepository.getCellIdByCode(code);
    }

    @Transactional
    public void save(CellEntity cellEntity) {
        cellRepository.save(cellEntity);
    }

    @Transactional
    public String createCell(CellDTO cellDTO) {
        final CellEntity cellEntity = CellEntity.builder()
                .code(cellDTO.getCode())
                .count(cellDTO.getCount())
                .build();
        cellRepository.save(cellEntity);
        log.info("cell save");
        return "cell is created";
    }
}
