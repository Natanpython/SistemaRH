package com.SistemaRh.sistemaRh.professor.services;

import com.SistemaRh.sistemaRh.compartilhado.exceptions.DatabaseException;
import com.SistemaRh.sistemaRh.compartilhado.exceptions.DisciplinaEmUsoException;
import com.SistemaRh.sistemaRh.compartilhado.exceptions.ResourceNotFoundException;
import com.SistemaRh.sistemaRh.professor.dto.DisciplinaDTO;
import com.SistemaRh.sistemaRh.professor.entities.Disciplina;
import com.SistemaRh.sistemaRh.professor.repositories.DisciplinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Service
public class DisciplinaService {

    @Autowired
    private DisciplinaRepository repository;

    private void copyDTOtoEntity(DisciplinaDTO dto, Disciplina entity) {
        entity.setNome(dto.getNome());
        entity.setAtivo(dto.getAtivo());
    }


    @Transactional(readOnly = true)
    public DisciplinaDTO findById(Long id){
        Disciplina entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Disciplina não encontrado "));
        return new DisciplinaDTO(entity);
    }

    @Transactional(readOnly = true)
    public List<DisciplinaDTO> findAll(){
        List<Disciplina> list = repository.findAll();
        return  list.stream().map(x -> new DisciplinaDTO(x)).toList();
    }

    @Transactional
    public DisciplinaDTO insert(DisciplinaDTO dto){
        Disciplina disciplina = new Disciplina();
        copyDTOtoEntity(dto, disciplina);

        disciplina.setCriadoEm(Instant.now());

        disciplina = repository.save(disciplina);

        return new DisciplinaDTO(disciplina);
    }

    @Transactional
    public DisciplinaDTO update(long id, DisciplinaDTO dto){
        Disciplina entity = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado"));
        copyDTOtoEntity(dto, entity);
        entity = repository.save(entity);
        return new DisciplinaDTO(entity);
    }

    @Transactional
    public void delete(Long id) {
        Disciplina disciplina = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado"));
        if (!disciplina.getProfessores().isEmpty()) {
            throw new DisciplinaEmUsoException();
        }
        try {
            repository.delete(disciplina);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Falha de integridade referencial");
        }
    }
}
