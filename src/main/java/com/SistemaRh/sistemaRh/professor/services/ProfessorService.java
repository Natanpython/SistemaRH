package com.SistemaRh.sistemaRh.professor.services;

import com.SistemaRh.sistemaRh.compartilhado.exceptions.DatabaseException;
import com.SistemaRh.sistemaRh.compartilhado.exceptions.ResourceNotFoundException;
import com.SistemaRh.sistemaRh.professor.dto.ProfessorDTO;
import com.SistemaRh.sistemaRh.professor.entities.Professor;
import com.SistemaRh.sistemaRh.professor.repositories.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Service
public class ProfessorService {

    @Autowired
    private ProfessorRepository repository;

    private void copyDTOtoEntity(ProfessorDTO dto, Professor entity){
        entity.setNome(dto.getNome());
        entity.setCpf(dto.getCpf());
        entity.setEmail(dto.getEmail());
        entity.setMatricula(dto.getMatricula());
        entity.setCargaHoraria(dto.getCargaHoraria());
        entity.setFormacao(dto.getFormacao());
        entity.setPosGraduacao(dto.getPosGraduacao());
        entity.setContato(dto.getContato());
    }

    @Transactional(readOnly = true)
    public ProfessorDTO findById(Long id){
        Professor entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Professor não encontrado "));
        return  new ProfessorDTO(entity);
    }

    @Transactional(readOnly = true)
    public List<ProfessorDTO> findAll(){
        List<Professor> list = repository.findAll();
        return list.stream().map(x -> new ProfessorDTO(x)).toList();
    }

    @Transactional
    public ProfessorDTO insert(ProfessorDTO dto){
        Professor professor = new Professor();
        copyDTOtoEntity(dto, professor);
        professor.setCriadoEm(Instant.now());

        professor = repository.save(professor);
        return new ProfessorDTO(professor);

    }

    @Transactional
    public ProfessorDTO update(Long id, ProfessorDTO dto){
        Professor entity = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado"));
        copyDTOtoEntity(dto, entity);
        entity = repository.save(entity);
        return new ProfessorDTO(entity);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Recurso não encontrado");
        }
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Falha de integridade referencial");
        }

    }
}
