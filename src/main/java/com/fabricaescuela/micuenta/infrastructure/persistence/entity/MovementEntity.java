package com.fabricaescuela.micuenta.infrastructure.persistence.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fabricaescuela.micuenta.domain.model.MovementCategory;
import com.fabricaescuela.micuenta.domain.model.MovementType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "movements")
public class MovementEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal amount;

    @Column(nullable = false)
    private LocalDate date;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private MovementType type;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private MovementCategory category;

    @Column(length = 255)
    private String description;

    public MovementEntity() {
    }

    public MovementEntity(
            Long id,
            Long userId,
            BigDecimal amount,
            LocalDate date,
            MovementType type,
            MovementCategory category,
            String description
    ) {
        this.id = id;
        this.userId = userId;
        this.amount = amount;
        this.date = date;
        this.type = type;
        this.category = category;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public MovementType getType() {
        return type;
    }

    public MovementCategory getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setType(MovementType type) {
        this.type = type;
    }

    public void setCategory(MovementCategory category) {
        this.category = category;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}