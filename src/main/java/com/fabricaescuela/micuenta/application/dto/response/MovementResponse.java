package com.fabricaescuela.micuenta.application.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fabricaescuela.micuenta.domain.model.MovementCategory;
import com.fabricaescuela.micuenta.domain.model.MovementType;

public record MovementResponse(
        Long id,
        BigDecimal amount,
        LocalDate date,
        MovementType type,
        MovementCategory category,
        String description
) {
}