package com.mil.sabores.pasteleria_backend.model;

import jakarta.persistence.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usuarios")
@Schema(description = "Representa un usuario registrado en la pastelería (cliente o administrador)")
public class Usuario {

    @Id // El email será el identificador único
    @Schema(description = "Correo electrónico del usuario", example = "gonzalo@ejemplo.com")
    private String email;

    @Schema(description = "Nombre completo del usuario", example = "Gonzalo Pérez")
    private String nombre;

    @Schema(description = "Contraseña, almacenada de forma segura (hasheada)", example = "password_hash")
    private String password;

    @Schema(description = "Rol del usuario (CLIENTE o ADMIN)", example = "CLIENTE")
    private String rol = "CLIENTE";
}