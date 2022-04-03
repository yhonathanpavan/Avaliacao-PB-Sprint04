package com.spring.avaliacao.controller;

import com.spring.avaliacao.constants.Ideologia;
import com.spring.avaliacao.dto.AssociadoDTO;
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

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/partidos")
public class PartidoController {

    @Autowired
    PartidoServiceImpl partidoService;

    @GetMapping
    public ResponseEntity<Page<PartidoDTO>> findAll(@RequestParam(required = false) Ideologia ideologia, @PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable paginacao){
        return ResponseEntity.ok().body(partidoService.findAll(ideologia, paginacao));
    }

    @GetMapping("/{id}/associados")
    public ResponseEntity<List<AssociadoDTO>> findAllAssociados(@PathVariable Long id){
        return ResponseEntity.ok().body(partidoService.findAllAssociados(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PartidoDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok().body(partidoService.findById(id));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<PartidoFormDTO> insert(@RequestBody @Valid PartidoFormDTO partidoForm){
        return ResponseEntity.created(partidoService.insert(partidoForm)).build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<PartidoDTO> update(@PathVariable Long id, @RequestBody @Valid PartidoFormDTO partidoForm){
        return ResponseEntity.ok().body(partidoService.update(id, partidoForm));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        return ResponseEntity.ok().body(partidoService.deleteById(id));
    }
}
