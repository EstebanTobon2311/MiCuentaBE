package com.fabricaescuela.micuenta.application.usecase;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.fabricaescuela.micuenta.application.dto.response.CategoryExpenseReportResponse;
import com.fabricaescuela.micuenta.application.exception.ResourceNotFoundException;
import com.fabricaescuela.micuenta.domain.model.CategoryExpenseReport;
import com.fabricaescuela.micuenta.domain.model.Movement;
import com.fabricaescuela.micuenta.domain.model.User;
import com.fabricaescuela.micuenta.domain.repository.MovementRepository;
import com.fabricaescuela.micuenta.domain.repository.UserRepository;

@Service
public class GetExpenseReportUseCase {

    private final MovementRepository movementRepository;
    private final UserRepository userRepository;

    public GetExpenseReportUseCase(MovementRepository movementRepository,
            UserRepository userRepository) {
        this.movementRepository = movementRepository;
        this.userRepository = userRepository;
    }

    public CategoryExpenseReportResponse execute(
            String authenticatedEmail, int month, int year) {

        User user = userRepository.findByEmail(
                authenticatedEmail.trim().toLowerCase())
                .orElseThrow(() -> new ResourceNotFoundException(
                "Authenticated user not found"));

        List<Movement> expenses = movementRepository
                .findExpensesByUserIdAndMonthAndYear(user.id(), month, year);

        if (expenses.isEmpty()) {
            return new CategoryExpenseReportResponse(
                    month, year, BigDecimal.ZERO, List.of());
        }

        BigDecimal totalExpenses = expenses.stream()
                .map(Movement::amount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        Map<String, BigDecimal> byCategory = expenses.stream()
                .collect(Collectors.groupingBy(
                        m -> m.category().name(),
                        Collectors.reducing(
                                BigDecimal.ZERO,
                                Movement::amount,
                                BigDecimal::add)
                ));

        List<CategoryExpenseReport> categories = byCategory.entrySet().stream()
                .map(entry -> {
                    BigDecimal catTotal = entry.getValue();
                    double percentage = catTotal
                            .divide(totalExpenses, 4, RoundingMode.HALF_UP)
                            .multiply(BigDecimal.valueOf(100))
                            .doubleValue();
                    return new CategoryExpenseReport(
                            entry.getKey(),
                            catTotal,
                            percentage
                    );
                })
                // Ordenar de mayor a menor gasto
                .sorted((a, b) -> b.totalAmount().compareTo(a.totalAmount()))
                .toList();

        return new CategoryExpenseReportResponse(
                month, year, totalExpenses, categories);
    }
}
