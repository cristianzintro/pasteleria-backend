package com.mil.sabores.pasteleria_backend.controller;



import com.mil.sabores.pasteleria_backend.model.Usuario;
import com.mil.sabores.pasteleria_backend.repository.UsuarioRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/usuarios")
@Tag(name = "API de Usuarios y Auth", description = "Gestión de registro y acceso de usuarios")
@CrossOrigin(origins = "http://localhost:3000")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // 1. POST: Registrar un nuevo usuario
    // Ruta: POST http://localhost:8081/api/usuarios/registrar
    @PostMapping("/registrar")
    @Operation(summary = "Registrar nuevo usuario", description = "Crea una nueva cuenta de usuario (por defecto: CLIENTE).")
    @ApiResponse(responseCode = "201", description = "Usuario registrado exitosamente")
    @ApiResponse(responseCode = "400", description = "El email ya está registrado")
    public ResponseEntity<?> registrarUsuario(@RequestBody Usuario nuevoUsuario) {

        // 1. Verificar si el email ya existe
        if (usuarioRepository.existsById(nuevoUsuario.getEmail())) {
            return new ResponseEntity<>("El email ya se encuentra registrado.", HttpStatus.BAD_REQUEST);
        }

        // 2. Aquí se DEBE hashear la contraseña antes de guardar.
        //    (Por simplicidad, guardamos la contraseña sin hashear, lo cual es INSEGURO para producción).

        // Asegurar que el rol por defecto sea CLIENTE (si no se especifica)
        if (nuevoUsuario.getRol() == null || nuevoUsuario.getRol().isEmpty()) {
            nuevoUsuario.setRol("CLIENTE");
        }

        // 3. Guardar el nuevo usuario
        Usuario usuarioGuardado = usuarioRepository.save(nuevoUsuario);

        // 4. Devolver 201 Created
        return new ResponseEntity<>(usuarioGuardado, HttpStatus.CREATED);
    }

    // 2. POST: Iniciar Sesión (Login básico sin JWT)
    // Ruta: POST http://localhost:8081/api/usuarios/login
    @PostMapping("/login")
    @Operation(summary = "Iniciar Sesión", description = "Verifica credenciales y permite el acceso.")
    @ApiResponse(responseCode = "200", description = "Login exitoso")
    @ApiResponse(responseCode = "401", description = "Credenciales inválidas")
    public ResponseEntity<?> loginUsuario(@RequestBody Usuario loginRequest) {

        // 1. Buscar al usuario por email
        Usuario usuario = usuarioRepository.findById(loginRequest.getEmail()).orElse(null);

        // 2. Si no se encuentra el usuario O la contraseña no coincide
        //    (NOTA: En producción, se usaría un comparador BCrypt para la contraseña hasheada)
        if (usuario == null || !usuario.getPassword().equals(loginRequest.getPassword())) {
            return new ResponseEntity<>("Email o contraseña inválidos.", HttpStatus.UNAUTHORIZED);
        }

        // 3. Login exitoso. Devuelve el usuario (sin contraseña) y un 200 OK.
        //    (Aquí se generaría y devolvería un token JWT en un proyecto real).
        usuario.setPassword(null); // No enviar la contraseña al cliente
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }
}
