package com.spring.avaliacao.controller;

import com.spring.avaliacao.dto.AssociadoDTO;
import com.spring.avaliacao.dto.AssociadoFormDTO;
import com.spring.avaliacao.dto.PartidoDTO;
import com.spring.avaliacao.dto.PartidoFormDTO;
import com.spring.avaliacao.services.AssociadoServiceImpl;
import com.spring.avaliacao.services.PartidoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/partidos")
public class PartidoController {

    @Autowired
    PartidoServiceImpl partidoService;

    @GetMapping
    public ResponseEntity<List<PartidoDTO>> findAll(){
        return partidoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PartidoDTO> findById(@PathVariable Long id){
        return partidoService.findById(id);
    }

    @PostMapping
    public ResponseEntity<PartidoFormDTO> insert(@RequestBody PartidoFormDTO partidoForm){
        return partidoService.insert(partidoForm);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PartidoDTO> update(@PathVariable Long id, @RequestBody PartidoFormDTO partidoForm){
        return partidoService.update(id, partidoForm);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        return partidoService.deleteById(id);
    }
}
