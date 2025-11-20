package com.mil.sabores.pasteleria_backend.repository;


import com.mil.sabores.pasteleria_backend.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {
    // Aquí podemos añadir métodos de búsqueda si los necesitamos,
    // pero JpaRepository ya nos da save, findById, etc.
        // Adaptar findByUsername para usar el email
        Optional<Usuario> findByEmail(String email);
        // Nota: El ID de tu Usuario ya es el email (String)
    }
