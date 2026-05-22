package com.fabricaescuela.micuenta.interfaces.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fabricaescuela.micuenta.application.dto.response.CategoryExpenseReportResponse;
import com.fabricaescuela.micuenta.application.usecase.GetExpenseReportUseCase;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/reports")
public class ReportController {

    private final GetExpenseReportUseCase getExpenseReportUseCase;

    public ReportController(GetExpenseReportUseCase getExpenseReportUseCase) {
        this.getExpenseReportUseCase = getExpenseReportUseCase;
    }

    @GetMapping("/expenses")
    public ResponseEntity<CategoryExpenseReportResponse> getExpenseReport(
            @RequestParam int month,
            @RequestParam int year,
            Authentication authentication) {

        String email = (String) authentication.getPrincipal();
        CategoryExpenseReportResponse response
                = getExpenseReportUseCase.execute(email, month, year);
        return ResponseEntity.ok(response);
    }
}
