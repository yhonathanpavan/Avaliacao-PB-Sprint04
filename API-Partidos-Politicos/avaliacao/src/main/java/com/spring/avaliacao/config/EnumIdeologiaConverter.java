package com.spring.avaliacao.config;

import com.spring.avaliacao.constants.CargoPolitico;
import com.spring.avaliacao.constants.Ideologia;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class EnumIdeologiaConverter implements Converter<String, Ideologia>{

        @Override
        public Ideologia convert(String value) {
            return Ideologia.valueOf(value.toUpperCase());
        }

}
