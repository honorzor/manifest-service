package ru.honorzor.manifestservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.honorzor.manifestservice.dto.OrderDTO;
import ru.honorzor.manifestservice.dto.ProductDTO;
import ru.honorzor.manifestservice.entity.OrderEntity;
import ru.honorzor.manifestservice.entity.ProductEntity;
import ru.honorzor.manifestservice.entity.ProductInfoEntity;
import ru.honorzor.manifestservice.enums.OrderState;
import ru.honorzor.manifestservice.exception.order.CannotFinishOrderException;
import ru.honorzor.manifestservice.mapper.ProductMapper;
import ru.honorzor.manifestservice.repository.OrderRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductMapper productMapper;
    private final CellService cellService;
    private final ProductInfoService productInfoService;

    @Transactional
    public void save(OrderDTO orderDTO) {
        final List<ProductEntity> productEntities = productMapper.toEntities(orderDTO.getProducts());

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

    @Transactional
    public Optional<OrderDTO> getOrder() {
        final Optional<OrderEntity> orderEntity = orderRepository.findFirstByOrderState(OrderState.FREE);
        if (orderEntity.isPresent()) {
            final OrderEntity order = orderEntity.get();

            final List<ProductDTO> product = productMapper.toDTO(order.getProducts())
                    .stream().peek(setInfo -> {
                        Long cellId = cellService.getCellIdByCode(setInfo.getCode());
                        Optional<ProductInfoEntity> productInfoEntity =
                                productInfoService.getProductInfoByCode(setInfo.getCode());

                        setInfo.setCellId(cellId);
                        setInfo.setName(productInfoEntity.get().getName());
                        setInfo.setDescription(productInfoEntity.get().getDescription());
                    }).collect(Collectors.toList());

            final OrderDTO orderDTO = OrderDTO.builder().products(product).build();

            order.setOrderState(OrderState.ACTIVE);

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
                throw new CannotFinishOrderException(String.format("You cannot finish this order, because order have state = %s", order.getOrderState()));
            }
            order.setOrderState(OrderState.FINISHED);
            orderRepository.save(order);
        }
    }
}