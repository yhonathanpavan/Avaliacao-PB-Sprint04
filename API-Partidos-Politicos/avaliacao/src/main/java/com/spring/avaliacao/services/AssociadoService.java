package com.spring.avaliacao.services;

import com.spring.avaliacao.constants.CargoPolitico;
import com.spring.avaliacao.dto.AssociadoDTO;
import com.spring.avaliacao.dto.AssociadoFormDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AssociadoService {

    ResponseEntity<Page<AssociadoDTO>> findAll(CargoPolitico cargoPolitico, Pageable paginacao);

    ResponseEntity<AssociadoDTO> findById(Long id);

    ResponseEntity<AssociadoFormDTO> insert(AssociadoFormDTO associadoForm);

    ResponseEntity<AssociadoDTO> update(Long id, AssociadoFormDTO associadoForm);

    ResponseEntity<?> deleteById(Long id);
}
