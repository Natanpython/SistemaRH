package com.SistemaRh.sistemaRh.professor.dto;


import com.SistemaRh.sistemaRh.professor.entities.Disciplina;

import java.time.Instant;

public class DisciplinaDTO {

    private Long id;
    private String nome;
    private Boolean ativo;
    private Instant criadoEm;

    public DisciplinaDTO(){

    }

    public DisciplinaDTO(Long id, String nome, Boolean ativo, Instant criadoEm) {
        this.id = id;
        this.nome = nome;
        this.ativo = ativo;
        this.criadoEm = criadoEm;
    }

    public DisciplinaDTO(Disciplina entiry){
        id = entiry.getId();
        nome = entiry.getNome();
        ativo = entiry.getAtivo();
        criadoEm = entiry.getCriadoEm();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public Instant getCriadoEm() {
        return criadoEm;
    }

    public void setCriadoEm(Instant criadoEm) {
        this.criadoEm = criadoEm;
    }
}
