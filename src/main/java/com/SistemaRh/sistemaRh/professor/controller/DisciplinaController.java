package com.SistemaRh.sistemaRh.professor.controller;

import com.SistemaRh.sistemaRh.professor.dto.DisciplinaDTO;
import com.SistemaRh.sistemaRh.professor.entities.Disciplina;
import com.SistemaRh.sistemaRh.professor.services.DisciplinaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/disciplinas")
public class DisciplinaController {

    @Autowired
    private DisciplinaService service;

    @GetMapping(value =  "/{id}")
    public DisciplinaDTO findById(@PathVariable Long id){
        return service.findById(id);
    }

    @GetMapping
    public ResponseEntity<List<DisciplinaDTO>> findAll(){
        List<DisciplinaDTO> dto = service.findAll();
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<DisciplinaDTO> insert(@Valid @RequestBody DisciplinaDTO dto){
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(dto.getId()).toUri();

        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value =  "/{id}")
    public ResponseEntity<DisciplinaDTO> update(@PathVariable Long id, @Valid @RequestBody DisciplinaDTO dto){
        dto = service.update(id, dto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping(value =  "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
