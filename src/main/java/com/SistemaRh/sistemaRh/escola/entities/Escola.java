package com.SistemaRh.sistemaRh.escola.entities;


import com.SistemaRh.sistemaRh.professor.entities.LotacaoProfessor;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.*;

@Entity
@Table(name = "tb_escolas")
public class Escola {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String endereco;

    @Column(unique = true)
    private String inep;

    private Integer quantidadeTurmas;
    private Instant criadoEm;

    @ElementCollection
    @Enumerated(EnumType.STRING)
    private Set<Turno> turnos = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "tb_escolas_segmentos",
            joinColumns = @JoinColumn(name = "escolas_id"),
            inverseJoinColumns = @JoinColumn(name = "segmentos_id"))
    private Set<Segmento> Segmentos = new HashSet<>();

    @OneToMany(mappedBy = "escola")
    private List<LotacaoProfessor> lotacaoProf = new ArrayList<>();

    public Escola() {
    }

    public Escola(Long id, Instant criadoEm, Integer quantidadeTurmas, String inep, String endereco, String nome) {
        this.id = id;
        this.criadoEm = criadoEm;
        this.quantidadeTurmas = quantidadeTurmas;
        this.inep = inep;
        this.endereco = endereco;
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getInep() {
        return inep;
    }

    public Integer getQuantidadeTurmas() {
        return quantidadeTurmas;
    }

    public Instant getCriadoEm() {
        return criadoEm;
    }

    public Set<Turno> getTurnos() {
        return turnos;
    }

    public Set<Segmento> getSegmentos() {
        return Segmentos;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public void setInep(String inep) {
        this.inep = inep;
    }

    public void setQuantidadeTurmas(Integer quantidadeTurmas) {
        this.quantidadeTurmas = quantidadeTurmas;
    }

    public void setCriadoEm(Instant criadoEm) {
        this.criadoEm = criadoEm;
    }

    public void setTurnos(Set<Turno> turnos) {
        this.turnos = turnos;
    }

    public void setSegmentos(Set<Segmento> segmentos) {
        Segmentos = segmentos;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Escola escola = (Escola) o;
        return Objects.equals(id, escola.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

}
