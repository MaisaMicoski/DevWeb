package com.maisamicoski.projdevweb.service;

import com.maisamicoski.projdevweb.exception.EntidadeNaoEncontradaException;
import com.maisamicoski.projdevweb.model.*;
import com.maisamicoski.projdevweb.repository.DisciplinaRepository;
import com.maisamicoski.projdevweb.repository.InscricaoRepository;
import com.maisamicoski.projdevweb.repository.ProfessorRepository;
import com.maisamicoski.projdevweb.repository.TurmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TurmaService {

    @Autowired
    private ProfessorRepository professorRepository;
    @Autowired
    private DisciplinaRepository disciplinaRepository;
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
    public Turma associarDisciplina(Long turmaId, Long disciplinaId) {

        Turma turma = turmaRepository.findById(turmaId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Turma não encontrada!"));

        Disciplina disciplina = disciplinaRepository.findById(disciplinaId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Disciplina não encontrado!"));

        turma.setDisciplina(disciplina);

        return turmaRepository.save(turma);
    }
    public Page<Turma> recuperarTurmasComPaginacao(Pageable pageable) {
        return turmaRepository.recuperarTurmasComPaginacao(pageable);
    }
    public List<Turma> recuperarTurmas() {
        return turmaRepository.recuperarTurmas();
    }
    public Turma recuperarTurmaPorId(Long id) {
        return turmaRepository.recuperarTurmaPorIdComLock(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        "Turma com id = " + id + " não encontrado."));
    }
}
