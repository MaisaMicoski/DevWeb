package com.maisamicoski.projdevweb.repository;


import com.maisamicoski.projdevweb.model.Aluno;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

    public interface AlunoRepository extends JpaRepository<Aluno, Long> {
        List<Aluno> findByNome(String nome);

        @Query("SELECT a FROM Aluno a WHERE a.id = :id")
        Optional<Aluno> recuperarAlunoPorIdComLock(@Param("id") Long id);

        @Query("SELECT a FROM Aluno a ORDER BY a.id")
        List<Aluno> recuperarAlunos();


    }

