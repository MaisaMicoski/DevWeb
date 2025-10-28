package com.maisamicoski.projdevweb.controller;

import com.maisamicoski.projdevweb.model.Turma;

// Você pode criar um novo arquivo para este record
public record TurmaDTO(
        Long id,
        int ano,
        int periodo,
        String nome,
        String nomeProfessor,
        String nomeDisciplina
) {

    public TurmaDTO(Turma turma) {
        this(
                turma.getId(),
                turma.getAno(),
                turma.getPeriodo(),
                turma.getNome(),
                (turma.getProfessor() != null) ? turma.getProfessor().getNome() : "Não definido",
                (turma.getDisciplina() != null) ? turma.getDisciplina().getNome() : "Não definida"
        );
    }
}
