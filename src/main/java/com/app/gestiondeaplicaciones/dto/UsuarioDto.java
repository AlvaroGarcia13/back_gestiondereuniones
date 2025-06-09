package com.app.gestiondeaplicaciones.dto;

import lombok.Data;

@Data
public class UsuarioDto {
    private Integer id;
    private String name;
    private String email;
    private String password;
}