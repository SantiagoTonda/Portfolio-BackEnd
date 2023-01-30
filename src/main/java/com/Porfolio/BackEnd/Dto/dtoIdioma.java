package com.Porfolio.BackEnd.Dto;

import javax.validation.constraints.NotBlank;

public class dtoIdioma {
    @NotBlank
    private String nombre;
    @NotBlank
    private String nivel;

    public dtoIdioma() {
    }

    public dtoIdioma(String nombre, String nivel) {
        this.nombre = nombre;
        this.nivel = nivel;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }
}