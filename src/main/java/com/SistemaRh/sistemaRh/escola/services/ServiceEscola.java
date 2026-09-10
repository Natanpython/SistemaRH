package com.SistemaRh.sistemaRh.escola.services;

import com.SistemaRh.sistemaRh.escola.dto.EscolaDTO;
import com.SistemaRh.sistemaRh.escola.entities.Escola;
import com.SistemaRh.sistemaRh.escola.repositories.EscolaRepository;
import com.SistemaRh.sistemaRh.compartilhado.exceptions.DatabaseException;
import com.SistemaRh.sistemaRh.compartilhado.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Service
public class ServiceEscola {

    @Autowired
    private EscolaRepository repository;

    private void copyDTOtoEntity(EscolaDTO dto, Escola entity) {
        entity.setNome(dto.getNome());
        entity.setEndereco(dto.getEndereco());
        entity.setInep(dto.getInep());
        entity.setQuantidadeTurmas(dto.getQuantidadeTurmas());
    }

    //Busca uma escola pelo ID e retorna um DTO da escola
    @Transactional(readOnly = true)
    public EscolaDTO findById(Long id) {
        Escola entity = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Escola não encontrada "));
        return new EscolaDTO(entity);
    }

    //Buscar todas as escolas e retornar uma lista de DTOs
    @Transactional(readOnly = true)
    public List<EscolaDTO> findAll() {
        List<Escola> list = repository.findAll();
        return list.stream().map(x -> new EscolaDTO(x)).toList();
    }

    // Adicionar nova escola
    @Transactional
    public EscolaDTO insert(EscolaDTO dto) {
        Escola escola = new Escola();
        copyDTOtoEntity(dto, escola);
        escola.setCriadoEm(Instant.now());

        escola = repository.save(escola);
        return new EscolaDTO(escola);
    }

    // Atualizar uma escola existente
    @Transactional
    public EscolaDTO update(Long id, EscolaDTO dto) {
        Escola entity = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado"));
        copyDTOtoEntity(dto, entity);
        entity = repository.save(entity);
        return new EscolaDTO(entity);
    }

    // Deletando escola
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
