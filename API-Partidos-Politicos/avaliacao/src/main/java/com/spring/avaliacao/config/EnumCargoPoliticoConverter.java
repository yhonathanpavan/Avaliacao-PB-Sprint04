package com.spring.avaliacao.config;

import com.spring.avaliacao.constants.CargoPolitico;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class EnumCargoPoliticoConverter implements Converter<String, CargoPolitico> {

    @Override
    public CargoPolitico convert(String value) {
        return CargoPolitico.valueOf(value.toUpperCase());
    }
}
