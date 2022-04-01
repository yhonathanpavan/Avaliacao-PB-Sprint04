package com.spring.avaliacao.services;

import com.spring.avaliacao.constants.CargoPolitico;
import com.spring.avaliacao.dto.AssociadoDTO;
import com.spring.avaliacao.dto.AssociadoFormDTO;
import com.spring.avaliacao.dto.VincularFormDTO;
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
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AssociadoServiceImpl implements AssociadoService{

    @Autowired
    AssociadoRepository associadoRepository;

    @Autowired
    PartidoRepository partidoRepository;

    @Autowired
    ModelMapper mapper;

    @Override
    public Page<AssociadoDTO> findAll(CargoPolitico cargoPolitico, Pageable paginacao){
        if(cargoPolitico == null) {
            Page<Associado> associado = associadoRepository.findAll(paginacao);
            Page<AssociadoDTO> associadoDTO = new PageImpl<>(associado.stream().map(element -> mapper.map(element, AssociadoDTO.class)).collect(Collectors.toList()));
            return associadoDTO;
        }else{
            Page<Associado> associado = associadoRepository.findByCargoPolitico(cargoPolitico, paginacao);
            Page<AssociadoDTO> associadoDTO = new PageImpl<>(associado.stream().map(element -> mapper.map(element, AssociadoDTO.class)).collect(Collectors.toList()));
            return associadoDTO;
        }
    }

    @Override
    public AssociadoDTO findById(Long id){
        Optional<Associado> associado = associadoRepository.findById(id);
        if (associado.isPresent()){
            return mapper.map(associado.get(), AssociadoDTO.class);
        }
        throw new ObjectNotFoundException("Associado não encontrado!");
    }

    @Override
    public URI insert(AssociadoFormDTO associadoForm) {
        Associado associado = mapper.map(associadoForm, Associado.class);
        associadoRepository.save(associado);
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(associado.getId()).toUri();
    }

    @Override
    public String insertPartido(VincularFormDTO vincularForm) {

        Optional<Associado> associado = associadoRepository.findById(vincularForm.getIdAssociado());
        Optional<Partido> partido = partidoRepository.findById(vincularForm.getIdPartido());

        if(associado.isPresent() && partido.isPresent()){

            associado.get().setPartido(partido.get());
            associadoRepository.save(associado.get());

            String nomeAssociado = associado.get().getNome();
            String siglaPartido = partido.get().getSigla();

            return "Associado(a) " + nomeAssociado + " foi vinculado(a) ao " + siglaPartido + " com sucesso!";
        }
        throw new ObjectNotFoundException("Associado ou partido não encontrado! Revise os Id's inseridos");
    }

    @Override
    public AssociadoDTO update(Long id, AssociadoFormDTO associadoForm) {
        Optional<Associado> associadoOptional = associadoRepository.findById(id);
        if (associadoOptional.isPresent()) {
            Associado associado = mapper.map(associadoForm, Associado.class);
            associado.setId(id);
            associadoRepository.save(associado);
            return mapper.map(associado, AssociadoDTO.class);
        }
        throw new ObjectNotFoundException("Associado não encontrado!");
    }

    @Override
    public String deleteById(Long id) {
        Optional<Associado> associadoOptional = associadoRepository.findById(id);
        if (associadoOptional.isPresent()) {
            associadoRepository.deleteById(id);

            String nomeAssociado = associadoOptional.get().getNome();
            return "Associado " + nomeAssociado + " excluído com sucesso!";
        }
        throw new ObjectNotFoundException("Associado não encontrado!");
    }

    @Override
    public String deletePartidoById(Long idAssociado, Long idPartido) {
        Optional<Associado> associadoOptional = associadoRepository.findById(idAssociado);
        Optional<Partido> partidoOptional = partidoRepository.findById(idPartido);

        if (associadoOptional.isPresent() && partidoOptional.isPresent() && associadoOptional.get().getPartido() != null){

            associadoOptional.get().setPartido(null);
            associadoRepository.save(associadoOptional.get());

            String nomeAssociado = associadoOptional.get().getNome();
            String siglaPartido = partidoOptional.get().getSigla();

            return nomeAssociado + " foi desvinculado do Partido " + siglaPartido + " com sucesso!";
        }
        throw new ObjectNotFoundException("Associado ou Partido não encontrado!");
    }
}
