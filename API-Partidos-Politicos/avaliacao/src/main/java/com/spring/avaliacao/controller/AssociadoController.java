package com.spring.avaliacao.controller;

import com.spring.avaliacao.constants.CargoPolitico;
import com.spring.avaliacao.dto.AssociadoDTO;
import com.spring.avaliacao.dto.AssociadoFormDTO;
import com.spring.avaliacao.dto.VincularFormDTO;
import com.spring.avaliacao.services.AssociadoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/associados")
public class AssociadoController{

    @Autowired
    AssociadoServiceImpl associadoService;

    @GetMapping
    public ResponseEntity<Page<AssociadoDTO>> findAll(@RequestParam(required = false)CargoPolitico cargoPolitico, @PageableDefault(sort = "id", direction = Sort.Direction.ASC)Pageable paginacao){
        return associadoService.findAll(cargoPolitico ,paginacao);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AssociadoDTO> findById(@PathVariable Long id){
        return associadoService.findById(id);
    }

    @PostMapping
    public ResponseEntity<AssociadoFormDTO> insert(@RequestBody @Valid AssociadoFormDTO associadoForm){
        return associadoService.insert(associadoForm);
    }

    @PostMapping("/partidos")
    public ResponseEntity<?> insert(@RequestBody @Valid VincularFormDTO vincularForm){
        return associadoService.insertPartido(vincularForm);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AssociadoDTO> update(@PathVariable Long id, @RequestBody @Valid AssociadoFormDTO associadoForm){
        return associadoService.update(id, associadoForm);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        return associadoService.deleteById(id);
    }

}
