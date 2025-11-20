// Adaptado de [cite: 19, 20, 22, 24]


package com.mil.sabores.pasteleria_backend.security;

import com.mil.sabores.pasteleria_backend.model.Usuario;
import com.mil.sabores.pasteleria_backend.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    // Inyección por constructor [cite: 21]
    public CustomUserDetailsService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByEmail(email)
                // Adaptar el mensaje de error [cite: 22]
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + email));

        // Retorna un UserDetails de Spring Security [cite: 23, 24]
        return new org.springframework.security.core.userdetails.User(
                usuario.getEmail(),
                usuario.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + usuario.getRol().toUpperCase()))
        );
    }
}