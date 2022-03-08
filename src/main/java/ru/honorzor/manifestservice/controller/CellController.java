package ru.honorzor.manifestservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.honorzor.manifestservice.dto.CellDTO;
import ru.honorzor.manifestservice.entity.CellEntity;
import ru.honorzor.manifestservice.service.CellService;

import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1/cell")
@RequiredArgsConstructor
@Slf4j
public class CellController {
    private final CellService cellService;

    @PostMapping(value = "/minus")
    public Optional<CellDTO> minus(@RequestBody CellDTO cellDTO) {
        log.info("request for minus count: {}", cellDTO);
        return cellService.minusCountFromCell(cellDTO);
    }

    @PostMapping(value = "/add")
    public Optional<CellDTO> add(@RequestBody CellDTO cellDTO) {
        log.info("request for add count: {}", cellDTO);
        return cellService.addCountInCell(cellDTO);
    }

    @PostMapping(value = "/create")
    public void createCell(@RequestBody CellEntity cellEntity) {
        cellService.save(cellEntity);
    }
}
