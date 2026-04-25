package com.fabricaescuela.micuenta.interfaces.rest;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fabricaescuela.micuenta.application.dto.request.CreateMovementRequest;
import com.fabricaescuela.micuenta.application.dto.request.UpdateMovementRequest;
import com.fabricaescuela.micuenta.application.dto.response.MovementResponse;
import com.fabricaescuela.micuenta.application.usecase.DeleteMovementUseCase;
import com.fabricaescuela.micuenta.application.usecase.ListExpensesUseCase;
import com.fabricaescuela.micuenta.application.usecase.ListIncomesUseCase;
import com.fabricaescuela.micuenta.application.usecase.RegisterExpenseUseCase;
import com.fabricaescuela.micuenta.application.usecase.RegisterIncomeUseCase;
import com.fabricaescuela.micuenta.application.usecase.UpdateMovementUseCase;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/movements")
public class MovementController {

    private final RegisterIncomeUseCase registerIncomeUseCase;
    private final RegisterExpenseUseCase registerExpenseUseCase;
    private final ListIncomesUseCase listIncomesUseCase;
    private final ListExpensesUseCase listExpensesUseCase;
    private final UpdateMovementUseCase updateMovementUseCase;
    private final DeleteMovementUseCase deleteMovementUseCase;

    public MovementController(
            RegisterIncomeUseCase registerIncomeUseCase,
            RegisterExpenseUseCase registerExpenseUseCase,
            ListIncomesUseCase listIncomesUseCase,
            ListExpensesUseCase listExpensesUseCase,
            UpdateMovementUseCase updateMovementUseCase,
            DeleteMovementUseCase deleteMovementUseCase
    ) {
        this.registerIncomeUseCase = registerIncomeUseCase;
        this.registerExpenseUseCase = registerExpenseUseCase;
        this.listIncomesUseCase = listIncomesUseCase;
        this.listExpensesUseCase = listExpensesUseCase;
        this.updateMovementUseCase = updateMovementUseCase;
        this.deleteMovementUseCase = deleteMovementUseCase;
    }

    @PostMapping("/incomes")
    public ResponseEntity<MovementResponse> registerIncome(
            @Valid @RequestBody CreateMovementRequest request,
            Authentication authentication
    ) {
        String email = (String) authentication.getPrincipal();
        MovementResponse response = registerIncomeUseCase.execute(email, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/expenses")
    public ResponseEntity<MovementResponse> registerExpense(
            @Valid @RequestBody CreateMovementRequest request,
            Authentication authentication
    ) {
        String email = (String) authentication.getPrincipal();
        MovementResponse response = registerExpenseUseCase.execute(email, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/incomes")
    public ResponseEntity<List<MovementResponse>> listIncomes(Authentication authentication) {
        String email = (String) authentication.getPrincipal();
        return ResponseEntity.ok(listIncomesUseCase.execute(email));
    }

    @GetMapping("/expenses")
    public ResponseEntity<List<MovementResponse>> listExpenses(Authentication authentication) {
        String email = (String) authentication.getPrincipal();
        return ResponseEntity.ok(listExpensesUseCase.execute(email));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovementResponse> updateMovement(
            @PathVariable Long id,
            @Valid @RequestBody UpdateMovementRequest request,
            Authentication authentication) {
        String email = (String) authentication.getPrincipal();
        MovementResponse response = updateMovementUseCase.execute(id, email, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovement(
            @PathVariable Long id,
            Authentication authentication) {
        String email = (String) authentication.getPrincipal();
        deleteMovementUseCase.execute(id, email);
        return ResponseEntity.noContent().build();
    }
}
