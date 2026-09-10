package com.SistemaRh.sistemaRh.escola.dto;

import com.SistemaRh.sistemaRh.escola.entities.Escola;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

import java.time.Instant;

public class EscolaDTO {

    private Long id;

    @Size(min = 3, max = 80, message = "Nome precisa ter de 3 a 80 caracteres.")
    @NotBlank(message = "Campo requerido")
    private String nome;

    private String endereco;

    @NotBlank(message = "Campo requerido")
    private String inep;

    @Positive(message = "A quantidade de turmas deve ser positiva.")
    private Integer quantidadeTurmas;
    private Instant criadoEm;

    public EscolaDTO() {
    }

    public EscolaDTO(Long id, String nome, String endereco, String inep, Integer quantidadeTurmas, Instant criadoEm) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.inep = inep;
        this.quantidadeTurmas = quantidadeTurmas;
        this.criadoEm = criadoEm;
    }

    public EscolaDTO(Escola entity) {
        id = entity.getId();
        nome = entity.getNome();
        endereco = entity.getEndereco();
        inep = entity.getInep();
        quantidadeTurmas = entity.getQuantidadeTurmas();
        criadoEm = entity.getCriadoEm();
    }

    public Instant getCriadoEm() {
        return criadoEm;
    }

    public Integer getQuantidadeTurmas() {
        return quantidadeTurmas;
    }

    public String getInep() {
        return inep;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getNome() {
        return nome;
    }

    public Long getId() {
        return id;
    }
}
