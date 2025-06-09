package com.app.gestiondeaplicaciones.controller;

import com.app.gestiondeaplicaciones.dto.UsuarioDto;
import com.app.gestiondeaplicaciones.entities.UsuarioEntity;
import com.app.gestiondeaplicaciones.service.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("users")
public class UsuarioController {
    private final UsuarioService userService;
    @GetMapping
    public List<UsuarioEntity> getAllUsers() {
        return userService.getAllUsers();
    }
    @PostMapping
    public UsuarioEntity createUser(@RequestBody UsuarioEntity user) {
        return userService.createUser(user);
    }

    @PostMapping("/login")
    public ResponseEntity<UsuarioDto> login(@RequestBody UsuarioDto loginRequest) {
        UsuarioDto user = userService.validateUser(loginRequest.getEmail(), loginRequest.getPassword());
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
    @PostMapping("/register")
    public ResponseEntity<UsuarioEntity> register(@RequestBody UsuarioDto userDto) {
        UsuarioEntity createdUser = userService.saveUser(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }
}



