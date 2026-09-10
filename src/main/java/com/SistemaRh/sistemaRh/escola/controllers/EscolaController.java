package com.SistemaRh.sistemaRh.escola.controllers;

import com.SistemaRh.sistemaRh.escola.dto.EscolaDTO;
import com.SistemaRh.sistemaRh.escola.entities.Escola;
import com.SistemaRh.sistemaRh.escola.services.ServiceEscola;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/escolas")
public class EscolaController {

    @Autowired
    private ServiceEscola service;

    @GetMapping(value = "/{id}")
    public EscolaDTO findById(@PathVariable Long id){
        return service.findById(id);
    }

    @GetMapping
    public ResponseEntity<List<EscolaDTO>> findAll(){
        List<EscolaDTO> dto = service.findAll();
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<EscolaDTO> insert(@Valid @RequestBody EscolaDTO dto){
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<EscolaDTO> update(@PathVariable Long id, @Valid  @RequestBody EscolaDTO dto){
        dto = service.update(id, dto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
