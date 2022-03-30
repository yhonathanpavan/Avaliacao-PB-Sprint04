package com.spring.avaliacao.services;

import com.spring.avaliacao.dto.AssociadoDTO;
import com.spring.avaliacao.dto.AssociadoFormDTO;
import com.spring.avaliacao.dto.PartidoDTO;
import com.spring.avaliacao.dto.PartidoFormDTO;
import com.spring.avaliacao.entities.Partido;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PartidoService {

    ResponseEntity<List<PartidoDTO>> findAll();

    ResponseEntity<PartidoDTO> findById(Long id);

    ResponseEntity<PartidoFormDTO> insert(PartidoFormDTO partidoForm);

    ResponseEntity<PartidoDTO> update(Long id, PartidoFormDTO partidoForm);

    ResponseEntity<?> deleteById(Long id);

}
