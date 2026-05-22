package com.fabricaescuela.micuenta.application.dto.response;

import java.math.BigDecimal;
import java.util.List;

import com.fabricaescuela.micuenta.domain.model.CategoryExpenseReport;

public record CategoryExpenseReportResponse(
        int month,
        int year,
        BigDecimal totalExpenses,
        List<CategoryExpenseReport> categories
        ) {

}
