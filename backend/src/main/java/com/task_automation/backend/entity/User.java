package com.task_automation.backend.entity;

import java.time.LocalDateTime;

import com.task_automation.backend.enums.AuthProvider;
import com.task_automation.backend.enums.Role;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false, unique = true)
    String email;

    String passwordHash;

    @Column(nullable = false, length = 100)
    String fullName;

    @Enumerated(EnumType.STRING) 
    Role role = Role.USER;

    @Enumerated(EnumType.STRING)
    AuthProvider authProvider = AuthProvider.LOCAL;

    String providerId;
    Boolean isActive = true;

    @Column(insertable = false, updatable = false)
    LocalDateTime createdAt;

    @Column(insertable = false, updatable = false)
    LocalDateTime updatedAt;
}
