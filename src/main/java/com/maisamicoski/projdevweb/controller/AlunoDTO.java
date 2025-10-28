package com.maisamicoski.projdevweb.controller;

import com.maisamicoski.projdevweb.model.Aluno;

public record AlunoDTO(Long id, String nome, String email) {
    public AlunoDTO(Aluno aluno) {
        this(aluno.getId(), aluno.getNome(), aluno.getEmail());
    }
}
