package ru.honorzor.manifestservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.honorzor.manifestservice.entity.OrderEntity;
import ru.honorzor.manifestservice.enums.OrderState;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    Optional<OrderEntity> findFirstByOrderState(OrderState state);
}
