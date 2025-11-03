package com.maisamicoski.projdevweb.service;

import com.maisamicoski.projdevweb.controller.AlunoDTO;
import com.maisamicoski.projdevweb.controller.TurmaDTO;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TurmaService {

    @Autowired
    private ProfessorRepository professorRepository;
    @Autowired
    private DisciplinaRepository disciplinaRepository;
    @Autowired
    private InscricaoRepository inscricaoRepository;
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
//    public Page<Turma> recuperarTurmasComPaginacao(Pageable pageable) {
//        return turmaRepository.recuperarTurmasComPaginacao(pageable);
//    }
@Transactional(readOnly = true)
public Page<TurmaDTO> recuperarTurmasComPaginacao(String nome, Pageable pageable) {
    Page<Turma> pageDeTurmas;
    if (StringUtils.hasText(nome)) {
        pageDeTurmas = turmaRepository.findByNomeStartingWithIgnoreCase(nome, pageable);
    } else {
        pageDeTurmas = turmaRepository.findAll(pageable);
    }
    return pageDeTurmas.map(TurmaDTO::new);
}
    public List<Turma> recuperarTurmas() {
        return turmaRepository.recuperarTurmas();
    }
    public Turma recuperarTurmaPorId(Long id) {
        return turmaRepository.recuperarTurmaPorIdComLock(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        "Turma com id = " + id + " não encontrado."));
    }

    @Transactional(readOnly = true)
    public List<AlunoDTO> recuperarAlunosPorTurma(Long turmaId) {
        List<Inscricao> inscricoes = inscricaoRepository.findByTurmaId(turmaId);
        return inscricoes.stream()
                .map(inscricao -> {
                    Aluno aluno = inscricao.getAluno();
                    return new AlunoDTO(aluno.getId(), aluno.getNome(), aluno.getEmail());
                })
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<TurmaDTO> recuperarTodasAsTurmas() {

        return turmaRepository.findAll().stream()
                .map(TurmaDTO::new)
                .collect(Collectors.toList());
    }
    @Transactional(readOnly = true)
    public Page<AlunoDTO> recuperarAlunosPorTurmaPaginado(Long turmaId, Pageable pageable) {

        Page<Inscricao> inscricoesPaginadas = inscricaoRepository.findByTurmaId(turmaId, pageable);
        System.out.println("Encontradas " + inscricoesPaginadas.getTotalElements() + " inscrições para a turma ID: " + turmaId);
        return inscricoesPaginadas.map(inscricao -> new AlunoDTO(inscricao.getAluno()));
    }
    @Transactional(readOnly = true)
    public TurmaDTO recuperarDetalhesDaTurma(Long id) {
        Turma turma = turmaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Turma com id = " + id + " não encontrada."));

        return new TurmaDTO(turma);
    }
}
