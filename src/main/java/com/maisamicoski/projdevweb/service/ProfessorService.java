package com.maisamicoski.projdevweb.service;

import com.maisamicoski.projdevweb.exception.EntidadeNaoEncontradaException;
import com.maisamicoski.projdevweb.model.Aluno;
import com.maisamicoski.projdevweb.model.Professor;

import com.maisamicoski.projdevweb.model.Turma;
import com.maisamicoski.projdevweb.repository.ProfessorRepository;

import com.maisamicoski.projdevweb.repository.TurmaRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@RequiredArgsConstructor
@Service
public class ProfessorService {
    private final ProfessorRepository professorRepository;
    private final TurmaRepository turmaRepository;
    public ProfessorService(ProfessorRepository professorRepository, TurmaRepository turmaRepository) {
        this.professorRepository = professorRepository;
        this.turmaRepository = turmaRepository;
    }
    public List<Professor> recuperarProfessores() {
        return professorRepository.recuperarProfessores();
    }
    public Professor cadastrarProfessor(Professor professor) {

        return professorRepository.save(professor);
    }
    @Transactional
    public Professor alterarProfessor(Professor professor) {

        professorRepository.recuperarProfessorPorIdComLock(professor.getId())
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        "Professor com id = " + professor.getId() + " não encontrado."));
        return professorRepository.save(professor);
    }
    public Professor recuperarProfessorPorId(Long id) {
        return professorRepository.recuperarProfessorPorIdComLock(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        "Professor com id = " + id + " não encontrado."));
    }
    public void removerProfessorPorId(Long id) {
        Professor professor = recuperarProfessorPorId(id);
        List<Turma> turmasAssociadas = turmaRepository.findByProfessorId(id);

        for (Turma turma : turmasAssociadas) {
            turma.setProfessor(null);
        }
        turmaRepository.saveAll(turmasAssociadas);
        professorRepository.delete(professor);
    }
    }

