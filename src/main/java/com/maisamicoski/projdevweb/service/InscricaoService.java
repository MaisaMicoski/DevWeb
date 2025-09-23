package com.maisamicoski.projdevweb.service;

import com.maisamicoski.projdevweb.exception.EntidadeNaoEncontradaException;

import com.maisamicoski.projdevweb.model.Aluno;
import com.maisamicoski.projdevweb.model.Inscricao;
import com.maisamicoski.projdevweb.model.Turma;
import com.maisamicoski.projdevweb.repository.InscricaoRepository;
import com.maisamicoski.projdevweb.repository.AlunoRepository;
import com.maisamicoski.projdevweb.repository.TurmaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
@Service
public class InscricaoService {
    @Autowired
    private AlunoRepository alunoRepository;
    @Autowired
    private TurmaRepository turmaRepository;
    private final InscricaoRepository inscricaoRepository;

    public InscricaoService(InscricaoRepository inscricaoRepository) {
        this.inscricaoRepository = inscricaoRepository;
    }

//    public Inscricao cadastrarInscricao(Inscricao inscricao) {
//        return inscricaoRepository.save(inscricao);
//    }


    public void removerInscricaoPorId(Long id) {
        inscricaoRepository.deleteById(id);
    }
    public Inscricao cadastrarInscricao(Long alunoId, Long turmaId) {

        Aluno aluno = alunoRepository.findById(alunoId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Aluno não encontrado!"));

        Turma turma = turmaRepository.findById(turmaId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Turma não encontrada!"));

        Inscricao novaInscricao = new Inscricao();
        novaInscricao.setAluno(aluno);
        novaInscricao.setTurma(turma);
        novaInscricao.setDataCadastro(LocalDate.now());
        return inscricaoRepository.save(novaInscricao);
    }
}
