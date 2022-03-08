package ru.honorzor.manifestservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.honorzor.manifestservice.entity.CellEntity;

public interface CellRepository extends JpaRepository<CellEntity, Long> {
    @Query(nativeQuery = true, value = "SELECT id FROM t_cell WHERE code = :code")
    Long getCellIdByCode(@Param("code") Long code);
}
