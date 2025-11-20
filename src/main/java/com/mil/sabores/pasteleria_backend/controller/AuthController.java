package com.mil.sabores.pasteleria_backend.controller;

import com.mil.sabores.pasteleria_backend.model.Usuario;
import com.mil.sabores.pasteleria_backend.security.jwt.JwtService;
import com.mil.sabores.pasteleria_backend.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth") // Usamos /api/auth para las rutas de autenticación
@Tag(name = "API de Autenticación JWT", description = "Login, Registro y Gestión de Tokens")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    // Clase interna para el DTO de respuesta de login
    record AuthResponse(String jwt, String email, String rol) {}

    // 1. POST: Iniciar Sesión y Obtener Token JWT
    @PostMapping("/login")
    @Operation(summary = "Iniciar Sesión", description = "Autentica al usuario y devuelve un token JWT.")
    public ResponseEntity<?> loginUsuario(@RequestBody Usuario loginRequest) {

        try {
            // 1. Intentar autenticar con el AuthenticationManager
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
            );

            // 2. Si la autenticación es exitosa
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            // 3. Generar el token
            String jwt = jwtService.generateToken(userDetails.getUsername());

            // 4. Obtener el rol para la respuesta
            Optional<Usuario> usuario = usuarioService.findByEmail(userDetails.getUsername());
            String rol = usuario.map(Usuario::getRol).orElse("CLIENTE");

            // 5. Devolver la respuesta con el token
            return ResponseEntity.ok(new AuthResponse(jwt, userDetails.getUsername(), rol));

        } catch (Exception e) {
            // Si las credenciales son inválidas
            return new ResponseEntity<>("Credenciales inválidas. " + e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }

    // 2. POST: Registrar Nuevo Usuario
    @PostMapping("/register")
    @Operation(summary = "Registrar nuevo usuario", description = "Crea una nueva cuenta de usuario (por defecto 'CLIENTE').")
    public ResponseEntity<?> registerUsuario(@RequestBody Usuario nuevoUsuario) {

        if (usuarioService.findByEmail(nuevoUsuario.getEmail()).isPresent()) {
            return new ResponseEntity<>("El email ya se encuentra registrado.", HttpStatus.BAD_REQUEST);
        }

        // El servicio maneja la encriptación de la contraseña
        Usuario usuarioRegistrado = usuarioService.register(
                nuevoUsuario.getEmail(),
                nuevoUsuario.getPassword(),
                nuevoUsuario.getNombre(),
                nuevoUsuario.getRol() // Si el rol es nulo, se establecerá como CLIENTE en el service
        );

        // Generar token inmediatamente después del registro (opcional, pero útil)
        // Nota: Esto requiere que CustomUserDetailsService cargue el usuario recién creado

        // Simplemente devolvemos el usuario (sin password)
        usuarioRegistrado.setPassword(null);
        return new ResponseEntity<>(usuarioRegistrado, HttpStatus.CREATED);
    }
}