package com.fabricaescuela.micuenta.application.usecase;

import org.springframework.stereotype.Service;

import com.fabricaescuela.micuenta.application.exception.ResourceNotFoundException;
import com.fabricaescuela.micuenta.domain.model.Movement;
import com.fabricaescuela.micuenta.domain.model.User;
import com.fabricaescuela.micuenta.domain.repository.MovementRepository;
import com.fabricaescuela.micuenta.domain.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class DeleteMovementUseCase {

    private final MovementRepository movementRepository;
    private final UserRepository userRepository;

    public DeleteMovementUseCase(MovementRepository movementRepository,
            UserRepository userRepository) {
        this.movementRepository = movementRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public void execute(Long movementId, String authenticatedEmail) {

        User user = userRepository.findByEmail(authenticatedEmail.trim().toLowerCase())
                .orElseThrow(() -> new ResourceNotFoundException("Authenticated user not found"));

        Movement existing = movementRepository.findById(movementId)
                .orElseThrow(() -> new ResourceNotFoundException(
                "Movement not found with id: " + movementId));

        if (!existing.userId().equals(user.id())) {
            throw new ResourceNotFoundException(
                    "Movement not found with id: " + movementId);
        }

        movementRepository.deleteById(movementId);
    }
}
