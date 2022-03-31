package com.spring.avaliacao.services;

import com.spring.avaliacao.constants.CargoPolitico;
import com.spring.avaliacao.dto.AssociadoDTO;
import com.spring.avaliacao.dto.AssociadoFormDTO;
import com.spring.avaliacao.entities.Associado;
import com.spring.avaliacao.exception.ObjectNotFound.ObjectNotFoundException;
import com.spring.avaliacao.repository.AssociadoRepository;
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
public class AssociadoServiceImpl implements AssociadoService{

    @Autowired
    AssociadoRepository associadoRepository;

    @Autowired
    ModelMapper mapper;

    @Override
    public ResponseEntity<Page<AssociadoDTO>> findAll(CargoPolitico cargoPolitico, Pageable paginacao){
        if(cargoPolitico == null) {
            Page<Associado> associado = associadoRepository.findAll(paginacao);
            Page<AssociadoDTO> associadoDTO = new PageImpl<>(associado.stream().map(element -> mapper.map(element, AssociadoDTO.class)).collect(Collectors.toList()));
            return ResponseEntity.ok().body(associadoDTO);
        }else{
            Page<Associado> associado = associadoRepository.findByCargoPolitico(cargoPolitico, paginacao);
            Page<AssociadoDTO> associadoDTO = new PageImpl<>(associado.stream().map(element -> mapper.map(element, AssociadoDTO.class)).collect(Collectors.toList()));
            return ResponseEntity.ok().body(associadoDTO);
        }
    }

    @Override
    public ResponseEntity<AssociadoDTO> findById(Long id){
        Optional<Associado> associado = associadoRepository.findById(id);
        if (associado.isPresent()){
            return ResponseEntity.ok().body(mapper.map(associado.get(), AssociadoDTO.class));
        }
        throw new ObjectNotFoundException("Associado não encontrado!");
    }

    @Override
    public ResponseEntity<AssociadoFormDTO> insert(AssociadoFormDTO associadoForm) {
        Associado associado = mapper.map(associadoForm, Associado.class);
        associadoRepository.save(associado);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(associado.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @Override
    public ResponseEntity<AssociadoDTO> update(Long id, AssociadoFormDTO associadoForm) {
        Optional<Associado> associadoOptional = associadoRepository.findById(id);
        if (associadoOptional.isPresent()) {
            Associado associado = mapper.map(associadoForm, Associado.class);
            associado.setId(id);
            associadoRepository.save(associado);
            return ResponseEntity.ok().body(mapper.map(associado, AssociadoDTO.class));
        }
        throw new ObjectNotFoundException("Associado não encontrado!");
    }

    @Override
    public ResponseEntity<?> deleteById(Long id) {
        Optional<Associado> associadoOptional = associadoRepository.findById(id);
        if (associadoOptional.isPresent()) {
            associadoRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        throw new ObjectNotFoundException("Associado não encontrado!");
    }
}
