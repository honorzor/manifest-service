package ru.honorzor.manifestservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.honorzor.manifestservice.entity.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
}
