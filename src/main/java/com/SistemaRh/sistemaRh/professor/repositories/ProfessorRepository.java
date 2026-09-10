package com.SistemaRh.sistemaRh.professor.repositories;

import com.SistemaRh.sistemaRh.professor.entities.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {
}
