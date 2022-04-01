package com.spring.avaliacao.repository;

import com.spring.avaliacao.constants.CargoPolitico;
import com.spring.avaliacao.entities.Associado;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AssociadoRepository extends JpaRepository<Associado, Long> {
    Page<Associado> findByCargoPolitico(CargoPolitico cargoPolitico, Pageable paginacao);

    List<Associado> findByPartidoId(Long id);
}
