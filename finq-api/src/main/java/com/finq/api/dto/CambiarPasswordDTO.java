package com.finq.api.dto;

public class CambiarPasswordDTO {
    private String passActual;
    private String passNuevo;

    // Getters y Setters
    public String getPassActual() {
        return passActual;
    }

    public void setPassActual(String passActual) {
        this.passActual = passActual;
    }

    public String getPassNuevo() {
        return passNuevo;
    }

    public void setPassNuevo(String passNuevo) {
        this.passNuevo = passNuevo;
    }
}