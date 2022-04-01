package com.spring.avaliacao.services;

import com.spring.avaliacao.constants.CargoPolitico;
import com.spring.avaliacao.dto.AssociadoDTO;
import com.spring.avaliacao.dto.AssociadoFormDTO;
import com.spring.avaliacao.dto.VincularFormDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.net.URI;

public interface AssociadoService {

    Page<AssociadoDTO> findAll(CargoPolitico cargoPolitico, Pageable paginacao);

    AssociadoDTO findById(Long id);

    URI insert(AssociadoFormDTO associadoForm);

    String insertPartido(VincularFormDTO vincularForm);

    AssociadoDTO update(Long id, AssociadoFormDTO associadoForm);

    String deleteById(Long id);
}
