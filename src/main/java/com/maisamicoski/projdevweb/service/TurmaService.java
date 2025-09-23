package com.maisamicoski.projdevweb.service;

import com.maisamicoski.projdevweb.exception.EntidadeNaoEncontradaException;
import com.maisamicoski.projdevweb.model.Inscricao;
import com.maisamicoski.projdevweb.model.Professor;
import com.maisamicoski.projdevweb.model.Turma;
import com.maisamicoski.projdevweb.repository.InscricaoRepository;
import com.maisamicoski.projdevweb.repository.ProfessorRepository;
import com.maisamicoski.projdevweb.repository.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TurmaService {

    @Autowired
    private ProfessorRepository professorRepository;

    private final TurmaRepository turmaRepository;

    public TurmaService(TurmaRepository turmaRepository) {
        this.turmaRepository = turmaRepository;
    }

    public Turma cadastrarTurma(Turma turma) {
        return turmaRepository.save(turma);
    }


    public void removerTurmaPorId(Long id) {
        turmaRepository.deleteById(id);
    }
    public Turma associarProfessor(Long turmaId, Long professorId) {

        Turma turma = turmaRepository.findById(turmaId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Turma não encontrada!"));

        Professor professor = professorRepository.findById(professorId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Professor não encontrado!"));

        turma.setProfessor(professor);

        return turmaRepository.save(turma);
    }
}
