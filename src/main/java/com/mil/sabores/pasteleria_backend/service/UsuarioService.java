package com.mil.sabores.pasteleria_backend.service;



import com.mil.sabores.pasteleria_backend.model.Usuario;
import com.mil.sabores.pasteleria_backend.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;


public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Usuario register(String email, String password, String nombre, String rol) {
        // Adaptar a la estructura de tu modelo Usuario, que no usa @Builder
        Usuario usuario = new Usuario();
        usuario.setEmail(email);
        usuario.setNombre(nombre);
        usuario.setRol(rol != null ? rol : "CLIENTE");


        usuario.setPassword(passwordEncoder.encode(password));

        return usuarioRepository.save(usuario);
    }


    public Optional<Usuario> findByEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }
}
