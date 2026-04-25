package com.fabricaescuela.micuenta.domain.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.fabricaescuela.micuenta.domain.model.Movement;
import com.fabricaescuela.micuenta.domain.model.MovementType;

public interface MovementRepository {

    Movement save(Movement movement);

    List<Movement> findByUserIdAndType(Long userId, MovementType type);

    List<Movement> findByUserIdAndDateBetween(Long userId, LocalDate startDate, LocalDate endDate);

    BigDecimal sumAmountByUserIdAndType(Long userId, MovementType type);

    Optional<Movement> findById(Long id);

    Movement update(Movement movement);

    void deleteById(Long id);

}
