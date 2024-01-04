package com.microservice.passwordmanagerservice.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "PasswordManager")
public class PasswordEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    private Long id;

    @Column(name = "Url", nullable = false)
    private String url;
    @Column(name = "Category", nullable = false)
    private String category;
    @Column(name = "Username", nullable = false,unique = true)
    private String username;
    @Column(name = "Password", nullable = false)
    private String password;
}
