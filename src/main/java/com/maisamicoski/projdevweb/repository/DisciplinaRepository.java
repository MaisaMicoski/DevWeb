package com.maisamicoski.projdevweb.repository;

import com.maisamicoski.projdevweb.model.Disciplina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DisciplinaRepository extends JpaRepository<Disciplina, Long> {
    List<Disciplina> findByNome(String nome);

    @Query("SELECT p FROM Disciplina p WHERE p.id = :id")
    Optional<Disciplina> recuperarDisciplinaPorIdComLock(@Param("id") Long id);

    @Query("SELECT p FROM Disciplina p ORDER BY p.id")
    List<Disciplina> recuperarDisciplinas();

    List<Disciplina> findAllByOrderByIdAsc();
}
