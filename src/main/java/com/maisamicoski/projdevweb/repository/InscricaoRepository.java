package com.maisamicoski.projdevweb.repository;

import com.maisamicoski.projdevweb.model.Inscricao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InscricaoRepository extends JpaRepository<Inscricao, Long> {

    List<Inscricao> findByAlunoId(Long alunoId);

    @Query("SELECT i FROM Inscricao i JOIN FETCH i.aluno WHERE i.turma.id = :turmaId")
    List<Inscricao> findInscricoesComAlunosByTurmaId(@Param("turmaId") Long turmaId);

    //List<Inscricao> findByTurmaId(Long turmaId);
    Page<Inscricao> findByTurmaId(Long turmaId, Pageable pageable);
}
