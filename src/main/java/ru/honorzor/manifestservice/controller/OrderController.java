package ru.honorzor.manifestservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.honorzor.manifestservice.dto.OrderDTO;
import ru.honorzor.manifestservice.service.OrderService;

import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1/order")
@RequiredArgsConstructor
@Slf4j
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public void save(@RequestBody OrderDTO orderDTO) {
        log.info("request to save order: {}", orderDTO);
        orderService.save(orderDTO);
    }

    @GetMapping(value = "/get/{id}")
    public Optional<OrderDTO> getOrderById(@PathVariable Long id) {
        return orderService.getEntityById(id);
    }

    @GetMapping(value = "/get")
    public Optional<OrderDTO> getOrder() {
        return orderService.getOrder();
    }

    @PostMapping(value = "/finish/{id}")
    public void finishOrder(@PathVariable Long id) {
        orderService.finishOrder(id);
        log.info("Order with id ended: {}", id);
    }
}
