package com.fabricaescuela.micuenta.application.usecase;

import org.springframework.stereotype.Service;

import com.fabricaescuela.micuenta.application.dto.request.UpdateMovementRequest;
import com.fabricaescuela.micuenta.application.dto.response.MovementResponse;
import com.fabricaescuela.micuenta.application.exception.ResourceNotFoundException;
import com.fabricaescuela.micuenta.domain.model.Movement;
import com.fabricaescuela.micuenta.domain.model.User;
import com.fabricaescuela.micuenta.domain.repository.MovementRepository;
import com.fabricaescuela.micuenta.domain.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UpdateMovementUseCase {

    private final MovementRepository movementRepository;
    private final UserRepository userRepository;

    public UpdateMovementUseCase(MovementRepository movementRepository,
            UserRepository userRepository) {
        this.movementRepository = movementRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public MovementResponse execute(Long movementId, String authenticatedEmail,
            UpdateMovementRequest request) {

        User user = userRepository.findByEmail(authenticatedEmail.trim().toLowerCase())
                .orElseThrow(() -> new ResourceNotFoundException("Authenticated user not found"));

        Movement existing = movementRepository.findById(movementId)
                .orElseThrow(() -> new ResourceNotFoundException(
                "Movement not found with id: " + movementId));

        if (!existing.userId().equals(user.id())) {
            throw new ResourceNotFoundException(
                    "Movement not found with id: " + movementId);
        }

        Movement updated = new Movement(
                existing.id(),
                existing.userId(),
                request.amount(),
                request.date(),
                existing.type(),
                request.category(),
                normalizeDescription(request.description())
        );

        Movement saved = movementRepository.update(updated);

        return toResponse(saved);
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
