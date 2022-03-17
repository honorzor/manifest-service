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
import ru.honorzor.manifestservice.dto.OrderDTO;
import ru.honorzor.manifestservice.entity.ProductEntity;
import ru.honorzor.manifestservice.entity.ProductInfoEntity;
import ru.honorzor.manifestservice.mapper.ProductMapper;
import ru.honorzor.manifestservice.repository.ProductInfoRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(value = SpringExtension.class)
@Disabled
public class OrderServiceTest extends TestWithMysqlContainer {
    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductInfoRepository productInfoRepository;

    private final ProductInfoEntity productInfoEntityOne = ProductInfoEntity.builder()
            .code(4353534L)
            .name("Apple iPhone 13 green")
            .description("description 1")
            .build();

    private final ProductInfoEntity productInfoEntityTwo = ProductInfoEntity.builder()
            .code(4353535L)
            .name("Apple iPhone 12 purple")
            .description("description 2")
            .build();

    private final OrderDTO orderDTO = OrderDTO.builder()
            .products(ProductMapper.INSTANCE.toDTO(Arrays.asList(ProductEntity.builder()
                            .code(4353534L)
                            .count(53L)
                            .build(),
                    ProductEntity.builder()
                            .code(4353535L)
                            .count(52L)
                            .build())))
            .build();

    @BeforeEach
    void setUp() {
        orderService.save(orderDTO);
        productInfoRepository.saveAll(List.of(productInfoEntityOne, productInfoEntityTwo));
    }

    @Test
    public void shouldGetOrderByStateFree() {
        final Optional<OrderDTO> order = orderService.getOrder();

        final String nameOne = order.get().getProducts().get(0).getName();
        final String descriptionOne = order.get().getProducts().get(0).getDescription();

        final String nameTwo = order.get().getProducts().get(1).getName();
        final String descriptionTwo = order.get().getProducts().get(1).getDescription();

        assertEquals(productInfoEntityOne.getName(), nameOne);
        assertEquals(productInfoEntityOne.getDescription(), descriptionOne);

        assertEquals(productInfoEntityTwo.getName(), nameTwo);
        assertEquals(productInfoEntityTwo.getDescription(), descriptionTwo);
    }
}
