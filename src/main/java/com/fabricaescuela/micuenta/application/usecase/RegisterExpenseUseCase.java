package com.fabricaescuela.micuenta.application.usecase;

import org.springframework.stereotype.Service;

import com.fabricaescuela.micuenta.application.dto.request.CreateMovementRequest;
import com.fabricaescuela.micuenta.application.dto.response.MovementResponse;
import com.fabricaescuela.micuenta.application.exception.ResourceNotFoundException;
import com.fabricaescuela.micuenta.domain.model.Movement;
import com.fabricaescuela.micuenta.domain.model.MovementType;
import com.fabricaescuela.micuenta.domain.model.User;
import com.fabricaescuela.micuenta.domain.repository.MovementRepository;
import com.fabricaescuela.micuenta.domain.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class RegisterExpenseUseCase {

    private final MovementRepository movementRepository;
    private final UserRepository userRepository;

    public RegisterExpenseUseCase(
            MovementRepository movementRepository,
            UserRepository userRepository
    ) {
        this.movementRepository = movementRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public MovementResponse execute(String authenticatedEmail, CreateMovementRequest request) {
        User user = userRepository.findByEmail(authenticatedEmail.trim().toLowerCase())
                .orElseThrow(() -> new ResourceNotFoundException("Authenticated user not found"));

        Movement savedMovement = movementRepository.save(
                new Movement(
                        null,
                        user.id(),
                        request.amount(),
                        request.date(),
                        MovementType.EGRESO,
                        request.category(),
                        normalizeDescription(request.description())
                )
        );

        return toResponse(savedMovement);
    }

    private String normalizeDescription(String description) {
        if (description == null || description.trim().isEmpty()) {
            return null;
        }
        return description.trim();
    }

    private MovementResponse toResponse(Movement movement) {
        return new MovementResponse(
                movement.id(),
                movement.amount(),
                movement.date(),
                movement.type(),
                movement.category(),
                movement.description()
        );
    }
}