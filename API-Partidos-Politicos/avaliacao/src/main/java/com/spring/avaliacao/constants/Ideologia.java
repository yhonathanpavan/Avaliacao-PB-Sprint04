package com.spring.avaliacao.constants;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Ideologia {
    DIREITA("ideologia"),
    CENTRO("centro"),
    ESQUERDA("esquerda");

    Ideologia(String value) {
        this.value = value;
    }

    private String value;

    @JsonCreator
    public static Ideologia fromValue(String value) {
        for (Ideologia ideologia : Ideologia.values()) {
            if (ideologia.value.equalsIgnoreCase(value)) {
                return ideologia;
            }
        }
        return null;
    }
}
