package com.SistemaRh.sistemaRh.compartilhado.exceptions;

public class DisciplinaEmUsoException extends RuntimeException {
    public DisciplinaEmUsoException() {
        super("Não é possível excluir a disciplina porque existem professores vinculados a ela.");
    }
}
