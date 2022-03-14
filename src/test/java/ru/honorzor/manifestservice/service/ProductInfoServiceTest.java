package ru.honorzor.manifestservice.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.honorzor.manifestservice.container.TestWithMysqlContainer;
import ru.honorzor.manifestservice.entity.ProductInfoEntity;
import ru.honorzor.manifestservice.repository.ProductInfoRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(value = SpringExtension.class)
public class ProductInfoServiceTest extends TestWithMysqlContainer {
    @Autowired
    private ProductInfoService productInfoService;

    @Autowired
    private ProductInfoRepository productInfoRepository;

    private final ProductInfoEntity productInfoEntity = ProductInfoEntity.builder()
            .code(4353535L)
            .name("Apple iPhone 12 purple")
            .description("description 2")
            .build();

    @BeforeEach
    void setUp() {
        productInfoRepository.save(productInfoEntity);
    }

    @Test
    public void shouldGetProductInfoByCode() {
        final Optional<ProductInfoEntity> productInfoByCode = productInfoService.getProductInfoByCode(4353535L);

        final String name = productInfoByCode.get().getName();
        final String description = productInfoByCode.get().getDescription();

        assertEquals(productInfoEntity.getName(), name);
        assertEquals(productInfoEntity.getDescription(), description);
    }
}
