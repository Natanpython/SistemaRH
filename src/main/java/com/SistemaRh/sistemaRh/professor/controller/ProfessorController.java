package com.SistemaRh.sistemaRh.professor.controller;

import com.SistemaRh.sistemaRh.professor.dto.ProfessorDTO;
import com.SistemaRh.sistemaRh.professor.services.ProfessorService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/professores")
public class ProfessorController {

    @Autowired
    private ProfessorService service;

    @GetMapping(value = "/{id}")
    public ProfessorDTO findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @GetMapping
    public ResponseEntity<List<ProfessorDTO>> findAll() {
        List<ProfessorDTO> dto = service.findAll();
        return ResponseEntity.ok(dto);

    }

    @PostMapping
    public ResponseEntity<ProfessorDTO> insert(@Valid @RequestBody ProfessorDTO dto) {
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<ProfessorDTO> update(@PathVariable Long id, @Valid @RequestBody ProfessorDTO dto) {
        dto = service.update(id, dto);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}

