package com.mil.sabores.pasteleria_backend.repository;


import com.mil.sabores.pasteleria_backend.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {
    // Aquí podemos añadir métodos de búsqueda si los necesitamos,
    // pero JpaRepository ya nos da save, findById, etc.
}