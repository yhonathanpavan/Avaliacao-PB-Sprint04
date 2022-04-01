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

import java.net.URI;
import java.util.List;

public interface PartidoService {

    Page<PartidoDTO> findAll(Ideologia ideologia, Pageable paginacao);

    List<AssociadoDTO> findAllAssociados(Long id);

    PartidoDTO findById(Long id);

    URI insert(PartidoFormDTO partidoForm);

    PartidoDTO update(Long id, PartidoFormDTO partidoForm);

    String deleteById(Long id);

}
