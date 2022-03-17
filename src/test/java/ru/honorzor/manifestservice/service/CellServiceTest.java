package ru.honorzor.manifestservice.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.honorzor.manifestservice.container.TestWithMysqlContainer;
import ru.honorzor.manifestservice.dto.CellDTO;
import ru.honorzor.manifestservice.entity.CellEntity;
import ru.honorzor.manifestservice.repository.CellRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(value = SpringExtension.class)
@Disabled
public class CellServiceTest extends TestWithMysqlContainer {

    @Autowired
    private CellService cellService;

    @Autowired
    private CellRepository cellRepository;

    private final CellEntity cellEntity = CellEntity.builder()
            .id(1L)
            .code(3312312L)
            .count(433L)
            .build();

    private final CellDTO cellDTO = CellDTO.builder()
            .id(1L)
            .code(3312312L)
            .count(3L)
            .build();

    @BeforeEach
    private void setUp() {
        cellService.save(cellEntity);
    }

    @Test
    void shouldAddCountInCell() {
        cellService.addCountInCell(cellDTO);

        final Optional<CellEntity> cellEntity = cellRepository.findById(cellDTO.getId());
        assertEquals(436L, cellEntity.get().getCount());
    }

    @Test
    void shouldMinusCountInCell() {
        cellService.minusCountFromCell(cellDTO);

        final Optional<CellEntity> cellEntity = cellRepository.findById(cellDTO.getId());
        assertEquals(430L, cellEntity.get().getCount());
    }

    @Test
    public void shouldGetCellIdByCode() {
        final Long cellIdByCode = cellService.getCellIdByCode(3312312L);
        assertEquals(cellDTO.getId(), cellIdByCode);
    }
}
