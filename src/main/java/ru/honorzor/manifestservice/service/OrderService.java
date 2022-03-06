package ru.honorzor.manifestservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import ru.honorzor.manifestservice.dto.OrderDTO;
import ru.honorzor.manifestservice.dto.ProductDTO;
import ru.honorzor.manifestservice.entity.OrderEntity;
import ru.honorzor.manifestservice.entity.ProductEntity;
import ru.honorzor.manifestservice.enums.OrderState;
import ru.honorzor.manifestservice.mapper.OrderMapper;
import ru.honorzor.manifestservice.mapper.ProductMapper;
import ru.honorzor.manifestservice.repository.OrderRepository;

import javax.transaction.Transactional;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductMapper productMapper;
    private final OrderMapper orderMapper;
    private final CellService cellService;

    @Transactional
    public void save(OrderDTO orderDTO) {
        final List<ProductEntity> productEntities = orderMapper.toEntities(orderDTO.getProducts());
        if (true){
            throw new RuntimeException();
        }

        final OrderEntity orderEntity = OrderEntity.builder()
                .products(productEntities).build();
        productEntities.forEach(productEntity -> productEntity.setOrderEntity(orderEntity));
        orderRepository.save(orderEntity);
    }

    @Transactional
    public Optional<OrderDTO> getEntityById(Long id) {
        final Optional<OrderEntity> orderEntity = orderRepository.findById(id);
        if (orderEntity.isPresent()) {
            final OrderEntity order = orderEntity.get();
            final List<ProductDTO> productDTOS = productMapper.toDTO(order.getProducts());

            final OrderDTO orderDTO = OrderDTO.builder().products(productDTOS).build();
            log.info("get order with id: {} , body: {}", order.getId(), orderDTO.getProducts());

            return Optional.of(orderDTO);
        }
        return Optional.empty();
    }
//    @Transactional
//    public Optional<OrderDTO> getOrder() {
//        return orderRepository.findFirstByOrderState(OrderState.FREE)
//                .map(orderEntity -> {
//                    orderEntity.setOrderState(OrderState.ACTIVE);
//                    final List<ProductEntity> products = orderEntity.getProducts();
//                    final List<ProductDTO> productDTOS = productMapper.toDTO(products);
//                    productDTOS.forEach(x -> x.setCellId(cellService.getCellIdByCode(x.getCode())));
//                    return Pair.of(orderEntity.getId(), productDTOS);
//                })
//                .map(pair -> {
//                    final OrderDTO orderDTO = OrderDTO.builder()
//                            .products(pair.getSecond())
//                            .build();
//                    log.info("order picking started id: {} ,body: {}", pair.getFirst(), orderDTO);
//                    return orderDTO;
//                });
//    }

    @Transactional
    public Optional<OrderDTO> getOrder() {
        Optional<OrderEntity> orderEntity = orderRepository.findFirstByOrderState(OrderState.FREE);
        if (orderEntity.isPresent()) {
            final OrderEntity order = orderEntity.get();
            final List<ProductDTO> productDTOS = productMapper.toDTO(order.getProducts());

            order.setOrderState(OrderState.ACTIVE);
            productDTOS.forEach(x -> x.setCellId(cellService.getCellIdByCode(x.getCode())));

            final OrderDTO orderDTO = OrderDTO.builder().products(productDTOS).build();
            log.info("order picking started id: {} ,body: {}", order.getId(), orderDTO);

            return Optional.of(orderDTO);
        }
        return Optional.empty();
    }

    @Transactional
    public void finishOrder(Long id) {
        final Optional<OrderEntity> orderEntity = orderRepository.findById(id);
        if (orderEntity.isPresent()) {
            final OrderEntity order = orderEntity.get();
            if (order.getOrderState() == OrderState.FREE) {
                throw new RuntimeException(String.format("You cannot finished this order, because order have state = %s", order.getOrderState()));
            }
            order.setOrderState(OrderState.FINISHED);
            orderRepository.save(orderEntity.get());
        }
    }
}