package com.app.gestiondeaplicaciones.service;

import com.app.gestiondeaplicaciones.dto.UsuarioDto;
import com.app.gestiondeaplicaciones.entities.UsuarioEntity;
import com.app.gestiondeaplicaciones.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UsuarioEntity saveUser(UsuarioDto userDto) {
        UsuarioEntity user = new UsuarioEntity();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        return userRepository.save(user);
    }

    public UsuarioEntity createUser(UsuarioEntity user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public List<UsuarioEntity> getAllUsers() {
        return userRepository.findAll();
    }
    public UsuarioDto validateUser(String email, String password) {
        UsuarioEntity user = userRepository.findByEmail(email);
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            UsuarioDto dto = new UsuarioDto();
            dto.setId(user.getId());
            dto.setName(user.getName());
            dto.setEmail(user.getEmail());
            return dto;
        }
        return null;
    }

    public UsuarioEntity saveUser(String name, String email, String password) {
        UsuarioEntity user = new UsuarioEntity();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        return userRepository.save(user);
    }
}
