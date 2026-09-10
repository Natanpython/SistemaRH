package com.SistemaRh.sistemaRh.professor.entities;

import com.SistemaRh.sistemaRh.escola.entities.Escola;
import com.SistemaRh.sistemaRh.escola.entities.Segmento;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "tb_lotacao_professor")
public class LotacaoProfessor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer cargaHorariaEscola;
    private LocalDate dataInicio;
    private LocalDate dataFim;

    @ManyToOne
    @JoinColumn(name = "escola_id")
    private Escola escola;

    @ManyToOne
    @JoinColumn(name = "segmento_id")
    private Segmento segmento;


    public LotacaoProfessor() {
    }

    public LotacaoProfessor(Long id, Integer cargaHorariaEscola, LocalDate dataInicio, LocalDate dataFim) {
        this.id = id;
        this.cargaHorariaEscola = cargaHorariaEscola;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCargaHorariaEscola() {
        return cargaHorariaEscola;
    }

    public void setCargaHorariaEscola(Integer cargaHorariaEscola) {
        this.cargaHorariaEscola = cargaHorariaEscola;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        LotacaoProfessor that = (LotacaoProfessor) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
