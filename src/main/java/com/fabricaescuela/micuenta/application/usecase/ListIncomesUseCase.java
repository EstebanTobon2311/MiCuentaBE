package com.fabricaescuela.micuenta.application.usecase;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fabricaescuela.micuenta.application.dto.response.MovementResponse;
import com.fabricaescuela.micuenta.application.exception.ResourceNotFoundException;
import com.fabricaescuela.micuenta.domain.model.MovementType;
import com.fabricaescuela.micuenta.domain.model.User;
import com.fabricaescuela.micuenta.domain.repository.MovementRepository;
import com.fabricaescuela.micuenta.domain.repository.UserRepository;

import org.springframework.transaction.annotation.Transactional;

@Service
public class ListIncomesUseCase {

    private final MovementRepository movementRepository;
    private final UserRepository userRepository;

    public ListIncomesUseCase(
            MovementRepository movementRepository,
            UserRepository userRepository
    ) {
        this.movementRepository = movementRepository;
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public List<MovementResponse> execute(String authenticatedEmail) {
        User user = userRepository.findByEmail(authenticatedEmail.trim().toLowerCase())
                .orElseThrow(() -> new ResourceNotFoundException("Authenticated user not found"));

        return movementRepository.findByUserIdAndType(user.id(), MovementType.INGRESO)
                .stream()
                .map(movement -> new MovementResponse(
                        movement.id(),
                        movement.amount(),
                        movement.date(),
                        movement.type(),
                        movement.category(),
                        movement.description()
                ))
                .toList();
    }
}