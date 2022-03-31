package com.spring.avaliacao.controller;

import com.spring.avaliacao.constants.Ideologia;
import com.spring.avaliacao.dto.PartidoDTO;
import com.spring.avaliacao.dto.PartidoFormDTO;
import com.spring.avaliacao.services.PartidoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/partidos")
public class PartidoController {

    @Autowired
    PartidoServiceImpl partidoService;

    @GetMapping
    public ResponseEntity<Page<PartidoDTO>> findAll(@RequestParam(required = false) Ideologia ideologia, @PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable paginacao){
        return partidoService.findAll(ideologia, paginacao);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PartidoDTO> findById(@PathVariable Long id){
        return partidoService.findById(id);
    }

    @PostMapping
    public ResponseEntity<PartidoFormDTO> insert(@RequestBody @Valid PartidoFormDTO partidoForm){
        return partidoService.insert(partidoForm);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PartidoDTO> update(@PathVariable Long id, @RequestBody @Valid PartidoFormDTO partidoForm){
        return partidoService.update(id, partidoForm);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        return partidoService.deleteById(id);
    }
}
