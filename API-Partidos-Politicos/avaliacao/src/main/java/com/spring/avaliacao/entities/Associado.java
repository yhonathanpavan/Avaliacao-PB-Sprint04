package com.spring.avaliacao.entities;

import com.spring.avaliacao.constants.CargoPolitico;
import com.spring.avaliacao.constants.Sexo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Associado{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @Enumerated(EnumType.STRING)
    private CargoPolitico cargoPolitico;
    private Date dataNascimento;
    @Enumerated(EnumType.STRING)
    private Sexo sexo;

}
