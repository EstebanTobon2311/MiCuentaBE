package com.fabricaescuela.micuenta.domain.model;

import java.math.BigDecimal;

public record CategoryExpenseReport(
        String category,
        BigDecimal totalAmount,
        double percentage
        ) {

}
