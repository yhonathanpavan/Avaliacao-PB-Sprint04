package com.spring.avaliacao.services;


import com.spring.avaliacao.constants.Ideologia;
import com.spring.avaliacao.dto.PartidoDTO;
import com.spring.avaliacao.dto.PartidoFormDTO;
import com.spring.avaliacao.entities.Partido;
import com.spring.avaliacao.exception.ObjectNotFoundException;
import com.spring.avaliacao.repository.PartidoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PartidoServiceImpl implements PartidoService {

    @Autowired
    PartidoRepository partidoRepository;

    @Autowired
    ModelMapper mapper;

    @Override
    public ResponseEntity<Page<PartidoDTO>> findAll(Ideologia ideologia, Pageable paginacao) {
        if (ideologia == null) {
            Page<Partido> partidos = partidoRepository.findAll(paginacao);
            Page<PartidoDTO> partidosDTO = new PageImpl<>(partidos.stream().map(element -> mapper.map(element, PartidoDTO.class)).collect(Collectors.toList()));
            return ResponseEntity.ok().body(partidosDTO);
        }else{
            Page<Partido> partidos = partidoRepository.findByIdeologia(ideologia, paginacao);
            Page<PartidoDTO> partidosDTO = new PageImpl<>(partidos.stream().map(element -> mapper.map(element, PartidoDTO.class)).collect(Collectors.toList()));
            return ResponseEntity.ok().body(partidosDTO);
        }
    }



    @Override
    public ResponseEntity<PartidoDTO> findById(Long id) {
        Optional<Partido> partido = partidoRepository.findById(id);
        if (partido.isPresent()) {
            return ResponseEntity.ok().body(mapper.map(partido.get(), PartidoDTO.class));
        }
        throw new ObjectNotFoundException("Partido não encontrado!");
    }

    @Override
    public ResponseEntity<PartidoFormDTO> insert(PartidoFormDTO partidoForm) {
        Partido partido = mapper.map(partidoForm, Partido.class);
        partidoRepository.save(partido);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(partido.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @Override
    public ResponseEntity<PartidoDTO> update(Long id, PartidoFormDTO partidoForm) {
        Optional<Partido> partidoOptional = partidoRepository.findById(id);
        if (partidoOptional.isPresent()) {
            Partido partido = mapper.map(partidoForm, Partido.class);
            partido.setId(id);
            partidoRepository.save(partido);
            return ResponseEntity.ok().body(mapper.map(partido, PartidoDTO.class));
        }
        throw new ObjectNotFoundException("Partido não encontrado!");
    }

    @Override
    public ResponseEntity<?> deleteById(Long id) {
        Optional<Partido> partidoOptional = partidoRepository.findById(id);

        if (partidoOptional.isPresent()) {
            partidoRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        throw new ObjectNotFoundException("Partido não encontrado!");
    }


}
