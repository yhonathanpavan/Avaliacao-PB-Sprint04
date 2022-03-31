package com.spring.avaliacao.constants;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum CargoPolitico {
    VEREADOR("vereador"),
    PREFEITO("prefeito"),
    DEPUTADO_ESTADUAL("deputado_estadual"),
    DEPUTADO_FEDERAL("deputado_federal"),
    SENADOR("senador"),
    GOVERNADOR("governador"),
    PRESIDENTE("presidente"),
    NENHUM("nenhum");

    CargoPolitico(String value) {
        this.value = value;
    }

    private String value;

    @JsonCreator
    public static CargoPolitico fromValue(String value) {
        for (CargoPolitico cargo : CargoPolitico.values()) {
            if (cargo.value.equalsIgnoreCase(value)) {
                return cargo;
            }
        }
        return null;
    }

}
