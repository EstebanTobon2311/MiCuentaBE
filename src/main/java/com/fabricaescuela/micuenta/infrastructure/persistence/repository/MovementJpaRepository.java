package com.fabricaescuela.micuenta.infrastructure.persistence.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fabricaescuela.micuenta.domain.model.MovementType;
import com.fabricaescuela.micuenta.infrastructure.persistence.entity.MovementEntity;

public interface MovementJpaRepository extends JpaRepository<MovementEntity, Long> {

    List<MovementEntity> findByUserIdAndTypeOrderByDateDescIdDesc(Long userId, MovementType type);

    List<MovementEntity> findByUserIdAndDateBetweenOrderByDateDescIdDesc(
            Long userId,
            LocalDate startDate,
            LocalDate endDate
    );

    @Query("""
            SELECT COALESCE(SUM(m.amount), 0)
            FROM MovementEntity m
            WHERE m.userId = :userId
              AND m.type = :type
            """)
    BigDecimal sumAmountByUserIdAndType(
            @Param("userId") Long userId,
            @Param("type") MovementType type
    );
}