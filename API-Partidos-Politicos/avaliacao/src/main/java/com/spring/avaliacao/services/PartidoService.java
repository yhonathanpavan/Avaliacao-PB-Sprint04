package com.spring.avaliacao.services;

import com.spring.avaliacao.constants.Ideologia;
import com.spring.avaliacao.dto.AssociadoDTO;
import com.spring.avaliacao.dto.AssociadoFormDTO;
import com.spring.avaliacao.dto.PartidoDTO;
import com.spring.avaliacao.dto.PartidoFormDTO;
import com.spring.avaliacao.entities.Partido;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PartidoService {

    ResponseEntity<Page<PartidoDTO>> findAll(Ideologia ideologia, Pageable paginacao);

    ResponseEntity<PartidoDTO> findById(Long id);

    ResponseEntity<PartidoFormDTO> insert(PartidoFormDTO partidoForm);

    ResponseEntity<PartidoDTO> update(Long id, PartidoFormDTO partidoForm);

    ResponseEntity<?> deleteById(Long id);

}
