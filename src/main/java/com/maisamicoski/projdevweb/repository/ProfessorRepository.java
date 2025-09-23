package com.maisamicoski.projdevweb.repository;

import com.maisamicoski.projdevweb.model.Professor;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {

    List<Professor> findByNome(String nome);

    @Query("SELECT p FROM Professor p WHERE p.id = :id")
    Optional<Professor> recuperarProfessorPorIdComLock(@Param("id") Long id);

    @Query("SELECT p FROM Professor p ORDER BY p.id")
    List<Professor> recuperarProfessores();

    List<Professor> findAllByOrderByIdAsc();
}
