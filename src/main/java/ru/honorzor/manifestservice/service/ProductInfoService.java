package ru.honorzor.manifestservice.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.honorzor.manifestservice.entity.ProductInfoEntity;
import ru.honorzor.manifestservice.repository.ProductInfoRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductInfoService {
    private final ProductInfoRepository productInfoRepository;

    @Transactional
    public Optional<ProductInfoEntity> getProductInfoByCode(Long code) {
        final Optional<ProductInfoEntity> productInfoEntity = productInfoRepository.findFirstByCode(code);
        if (productInfoEntity.isPresent()){
            log.info("ProductInfo entity by code {} , body {}",code,productInfoEntity);
            return productInfoEntity;
        }
        return Optional.empty();
    }
}
