package ru.honorzor.manifestservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.honorzor.manifestservice.entity.ProductInfoEntity;

import java.util.Optional;

@Repository
public interface ProductInfoRepository extends JpaRepository<ProductInfoEntity, Long> {
    Optional<ProductInfoEntity> findFirstByCode(Long code);
}
