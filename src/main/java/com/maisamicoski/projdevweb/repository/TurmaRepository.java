package com.maisamicoski.projdevweb.repository;


import com.maisamicoski.projdevweb.model.Turma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TurmaRepository extends JpaRepository<Turma, Long> {

    List<Turma> findByProfessorId(Long professorId);
}