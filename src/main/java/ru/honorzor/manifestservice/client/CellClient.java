package ru.honorzor.manifestservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.honorzor.manifestservice.dto.RequestDTO;

import java.util.Map;

@FeignClient(name = "CELL-SERVICE/api/v1/cell")
public interface CellClient {
    @PostMapping("/getAllCellIdByCode")
    Map<Long, Long> getAllCellIdByCode(@RequestBody RequestDTO requestDTO);
}
