package com.spring.avaliacao.services;


import com.spring.avaliacao.constants.Ideologia;
import com.spring.avaliacao.dto.AssociadoDTO;
import com.spring.avaliacao.dto.PartidoDTO;
import com.spring.avaliacao.dto.PartidoFormDTO;
import com.spring.avaliacao.entities.Associado;
import com.spring.avaliacao.entities.Partido;
import com.spring.avaliacao.exception.ObjectNotFound.ObjectNotFoundException;
import com.spring.avaliacao.repository.AssociadoRepository;
import com.spring.avaliacao.repository.PartidoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PartidoServiceImpl implements PartidoService {

    @Autowired
    PartidoRepository partidoRepository;

    @Autowired
    AssociadoRepository associadoRepository;

    @Autowired
    ModelMapper mapper;

    @Override
    public Page<PartidoDTO> findAll(Ideologia ideologia, Pageable paginacao) {
        if (ideologia == null) {
            Page<Partido> partidos = partidoRepository.findAll(paginacao);
            Page<PartidoDTO> partidosDTO = new PageImpl<>(partidos.stream().map(element -> mapper.map(element, PartidoDTO.class)).collect(Collectors.toList()));
            return partidosDTO;
        }else{
            Page<Partido> partidos = partidoRepository.findByIdeologia(ideologia, paginacao);
            Page<PartidoDTO> partidosDTO = new PageImpl<>(partidos.stream().map(element -> mapper.map(element, PartidoDTO.class)).collect(Collectors.toList()));
            return partidosDTO;
        }
    }

    @Override
    public List<AssociadoDTO> findAllAssociados(Long id){

        List<Associado> associadosDoPartido = associadoRepository.findByPartidoId(id);

        if(associadosDoPartido.isEmpty()){
            throw new ObjectNotFoundException("Partido não possui nenhum associado!");
        }else{
            List<AssociadoDTO> associadoDTO = associadosDoPartido.stream().map(element -> mapper.map(element, AssociadoDTO.class)).collect(Collectors.toList());
            return associadoDTO;
        }
    }


    @Override
    public PartidoDTO findById(Long id) {
        Optional<Partido> partido = partidoRepository.findById(id);
        if (partido.isPresent()) {
            return mapper.map(partido.get(), PartidoDTO.class);
        }
        throw new ObjectNotFoundException("Partido não encontrado!");
    }

    @Override
    public URI insert(PartidoFormDTO partidoForm) {
        Partido partido = mapper.map(partidoForm, Partido.class);
        partidoRepository.save(partido);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(partido.getId()).toUri();
        return uri;
    }

    @Override
    public PartidoDTO update(Long id, PartidoFormDTO partidoForm) {
        Optional<Partido> partidoOptional = partidoRepository.findById(id);
        if (partidoOptional.isPresent()) {
            Partido partido = mapper.map(partidoForm, Partido.class);
            partido.setId(id);
            partidoRepository.save(partido);
            return mapper.map(partido, PartidoDTO.class);
        }
        throw new ObjectNotFoundException("Partido não encontrado!");
    }

    @Override
    public String deleteById(Long id){
        Optional<Partido> partidoOptional = partidoRepository.findById(id);
        List<Associado> associado = associadoRepository.findByPartidoId(id);

        if (partidoOptional.isPresent()) {

            //Desvinculando associados do partido para conseguir excluir
            if(!associado.isEmpty()){
                associado.stream().forEach(e -> e.setPartido(null));
            }

            partidoRepository.deleteById(id);

            String siglaPartido = partidoOptional.get().getSigla();
            return "Partido " + siglaPartido + " excluído com sucesso!";
        }
        throw new ObjectNotFoundException("Partido não encontrado!");
    }


}
