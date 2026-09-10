package com.SistemaRh.sistemaRh.escola.entities;

import com.SistemaRh.sistemaRh.professor.entities.LotacaoProfessor;
import jakarta.persistence.*;

import java.util.*;

@Entity
@Table(name = "tb_segmentos")
public class Segmento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String codigo;

    @ManyToMany(mappedBy = "Segmentos")
    private Set<Escola> escolas = new HashSet<>();

    @Enumerated(EnumType.STRING)
    private Segmentos tipo;

    @OneToMany(mappedBy = "segmento")
    private List<LotacaoProfessor> lotacaoProf = new ArrayList<>();

    public Segmento() {
    }

    public Segmento(Long id, String nome, String codigo) {
        this.id = id;
        this.nome = nome;
        this.codigo = codigo;
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

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Segmentos getTipo() {
        return tipo;
    }

    public void setTipo(Segmentos tipo) {
        this.tipo = tipo;
    }

    public Set<Escola> getEscolas() {
        return escolas;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        Segmento segmento = (Segmento) o;
        return Objects.equals(id, segmento.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
