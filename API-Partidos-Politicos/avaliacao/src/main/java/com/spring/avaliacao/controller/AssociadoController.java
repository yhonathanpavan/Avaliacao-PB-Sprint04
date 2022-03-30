package com.spring.avaliacao.controller;

import com.spring.avaliacao.dto.AssociadoDTO;
import com.spring.avaliacao.dto.AssociadoFormDTO;
import com.spring.avaliacao.services.AssociadoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/associados")
public class AssociadoController{

    @Autowired
    AssociadoServiceImpl associadoService;

    @GetMapping
    public ResponseEntity<List<AssociadoDTO>> findAll(){
        return associadoService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AssociadoDTO> findById(@PathVariable Long id){
        return associadoService.findById(id);
    }

    @PostMapping
    public ResponseEntity<AssociadoFormDTO> insert(@RequestBody AssociadoFormDTO associadoForm){
        return associadoService.insert(associadoForm);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AssociadoDTO> update(@PathVariable Long id, @RequestBody AssociadoFormDTO associadoForm){
        return associadoService.update(id, associadoForm);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id){
        return associadoService.deleteById(id);
    }

}
