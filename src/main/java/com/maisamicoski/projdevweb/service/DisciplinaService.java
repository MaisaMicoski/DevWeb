package com.maisamicoski.projdevweb.service;

import com.maisamicoski.projdevweb.exception.EntidadeNaoEncontradaException;
import com.maisamicoski.projdevweb.model.Disciplina;
import com.maisamicoski.projdevweb.model.Turma;
import com.maisamicoski.projdevweb.repository.DisciplinaRepository;
import com.maisamicoski.projdevweb.repository.TurmaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class DisciplinaService {
    private final DisciplinaRepository disciplinaRepository;
    private final TurmaRepository turmaRepository;
    public DisciplinaService(DisciplinaRepository disciplinaRepository, TurmaRepository turmaRepository) {
        this.disciplinaRepository = disciplinaRepository;
        this.turmaRepository = turmaRepository;
    }
    public List<Disciplina> recuperarDisciplinaes() {
        return disciplinaRepository.recuperarDisciplinas();
    }
    public Disciplina cadastrarDisciplina(Disciplina disciplina) {

        return disciplinaRepository.save(disciplina);
    }
    @Transactional
    public Disciplina alterarDisciplina(Disciplina disciplina) {

        disciplinaRepository.recuperarDisciplinaPorIdComLock(disciplina.getId())
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        "Disciplina com id = " + disciplina.getId() + " não encontrado."));
        return disciplinaRepository.save(disciplina);
    }
    public Disciplina recuperarDisciplinaPorId(Long id) {
        return disciplinaRepository.recuperarDisciplinaPorIdComLock(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        "Disciplina com id = " + id + " não encontrado."));
    }
    public void removerDisciplinaPorId(Long id) {
        Disciplina disciplina = recuperarDisciplinaPorId(id);
        List<Turma> turmasAssociadas = turmaRepository.findByDisciplinaId(id);

        for (Turma turma : turmasAssociadas) {
            turma.setDisciplina(null);
        }
        turmaRepository.saveAll(turmasAssociadas);
        disciplinaRepository.delete(disciplina);
    }
}
