package com.spring.avaliacao.config;

import com.spring.avaliacao.constants.Ideologia;
import com.spring.avaliacao.constants.Sexo;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class EnumSexoConverter implements Converter<String, Sexo>{

    @Override
    public Sexo convert(String source) {
        return null;
    }
}
