package com.spring.avaliacao.entities;

import com.spring.avaliacao.constants.Ideologia;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Partido{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String sigla;
    @Enumerated(EnumType.STRING)
    private Ideologia ideologia;
    private LocalDate dataDeFundacao;

}
