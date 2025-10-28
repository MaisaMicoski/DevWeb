package com.maisamicoski.projdevweb.repository;


import com.maisamicoski.projdevweb.model.Aluno;
import com.maisamicoski.projdevweb.model.Inscricao;
import com.maisamicoski.projdevweb.model.Turma;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TurmaRepository extends JpaRepository<Turma, Long> {

    List<Turma> findByProfessorId(Long professorId);
    List<Turma> findByDisciplinaId(Long professorId);
    @Query(
            value = "select p from Turma p order by p.id",
            countQuery = "select count(p) from Turma p"
    )
    Page<Turma> recuperarTurmasComPaginacao(Pageable pageable);
    @Query("SELECT a FROM Turma a ORDER BY a.id")
    List<Turma> recuperarTurmas();

    @Query("SELECT a FROM Turma a WHERE a.id = :id")
    Optional<Turma> recuperarTurmaPorIdComLock(@Param("id") Long id);

    Page<Turma> findByNomeStartingWithIgnoreCase(String nome, Pageable pageable);
    //List<Inscricao> findByTurmaId(Long turmaId);

}