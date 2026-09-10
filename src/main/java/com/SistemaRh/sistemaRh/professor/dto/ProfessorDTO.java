package com.SistemaRh.sistemaRh.professor.dto;

import com.SistemaRh.sistemaRh.professor.entities.Professor;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.CPF;

import java.time.Instant;

public class ProfessorDTO {

    private Long id;

    @Size(min = 3, max = 80, message = "Nome precisa ter de 3 a 80 caracteres.")
    @NotBlank(message = "Campo requerido")
    private String nome;

    @NotBlank(message = "Campo requerido")
    @CPF(message = "CPF inválido")
    private String cpf;

    @NotBlank(message = "Campo requerido")
    private String matricula;

    @NotNull(message = "Campo requerido")
    @Positive(message = "A carga horária deve ser positiva.")
    private Integer cargaHoraria;

    private String formacao;
    private String posGraduacao;

    @NotBlank(message = "Campo requerido")
    private String contato;

    @NotBlank(message = "Campo requerido")
    @Email(message = "Email inválido")
    private String email;

    private Instant criadoEm;

    public ProfessorDTO() {}

    public ProfessorDTO(Long id, String nome, String cpf, String matricula, Integer cargaHoraria, String formacao, String posGraduacao, String contato, String email, Instant criadoEm) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.matricula = matricula;
        this.cargaHoraria = cargaHoraria;
        this.formacao = formacao;
        this.posGraduacao = posGraduacao;
        this.contato = contato;
        this.email = email;
        this.criadoEm = criadoEm;
    }

    public ProfessorDTO(Professor entity){
        id = entity.getId();
        nome = entity.getNome();
        cpf = entity.getCpf();
        matricula = entity.getMatricula();
        cargaHoraria = entity.getCargaHoraria();
        formacao = entity.getFormacao();
        posGraduacao = entity.getPosGraduacao();
        contato = entity.getContato();
        email = entity.getEmail();
        criadoEm = entity.getCriadoEm();
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Integer getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(Integer cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public String getFormacao() {
        return formacao;
    }

    public void setFormacao(String formacao) {
        this.formacao = formacao;
    }

    public String getPosGraduacao() {
        return posGraduacao;
    }

    public void setPosGraduacao(String posGraduacao) {
        this.posGraduacao = posGraduacao;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Instant getCriadoEm() {
        return criadoEm;
    }

    public void setCriadoEm(Instant criadoEm) {
        this.criadoEm = criadoEm;
    }
}
