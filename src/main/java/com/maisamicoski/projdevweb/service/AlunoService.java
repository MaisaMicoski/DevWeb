package com.maisamicoski.projdevweb.service;

import com.maisamicoski.projdevweb.exception.EntidadeNaoEncontradaException;
import com.maisamicoski.projdevweb.model.Aluno;
import com.maisamicoski.projdevweb.model.Inscricao;
import com.maisamicoski.projdevweb.repository.AlunoRepository;
import com.maisamicoski.projdevweb.repository.InscricaoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AlunoService {
    private final AlunoRepository alunoRepository;
    private final InscricaoRepository inscricaoRepository;
    public AlunoService(AlunoRepository alunoRepository, InscricaoRepository inscricaoRepository) {
        this.alunoRepository = alunoRepository;
        this.inscricaoRepository = inscricaoRepository;
    }
    public List<Aluno> recuperarAlunos() {
        return alunoRepository.recuperarAlunos();
    }
    public Aluno cadastrarAluno(Aluno aluno) {
        return alunoRepository.save(aluno);
    }

    @Transactional
    public Aluno alterarAluno(Aluno aluno) {

        alunoRepository.recuperarAlunoPorIdComLock(aluno.getId())
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        "Aluno com id = " + aluno.getId() + " não encontrado."));
        return alunoRepository.save(aluno);
    }
    public Aluno recuperarAlunoPorId(Long id) {
        return alunoRepository.recuperarAlunoPorIdComLock(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        "Aluno com id = " + id + " não encontrado."));
    }

    public void removerAlunoPorId(Long id) {
        Aluno aluno = recuperarAlunoPorId(id);
        alunoRepository.delete(aluno);
    }

}
