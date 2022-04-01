package com.spring.avaliacao.repository;

import com.spring.avaliacao.constants.Ideologia;
import com.spring.avaliacao.entities.Partido;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartidoRepository extends JpaRepository<Partido, Long>{
    Page<Partido> findByIdeologia(Ideologia ideologia, Pageable paginacao);
}
