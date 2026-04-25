package com.fabricaescuela.micuenta.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public record Movement(
        Long id,
        Long userId,
        BigDecimal amount,
        LocalDate date,
        MovementType type,
        MovementCategory category,
        String description
) {
}