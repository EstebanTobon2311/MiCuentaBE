package com.fabricaescuela.micuenta.infrastructure.persistence.mapper;

import org.springframework.stereotype.Component;

import com.fabricaescuela.micuenta.domain.model.Movement;
import com.fabricaescuela.micuenta.infrastructure.persistence.entity.MovementEntity;

@Component
public class MovementMapper {

    public Movement toDomain(MovementEntity entity) {
        return new Movement(
                entity.getId(),
                entity.getUserId(),
                entity.getAmount(),
                entity.getDate(),
                entity.getType(),
                entity.getCategory(),
                entity.getDescription()
        );
    }

    public MovementEntity toEntity(Movement movement) {
        return new MovementEntity(
                movement.id(),
                movement.userId(),
                movement.amount(),
                movement.date(),
                movement.type(),
                movement.category(),
                movement.description()
        );
    }
}