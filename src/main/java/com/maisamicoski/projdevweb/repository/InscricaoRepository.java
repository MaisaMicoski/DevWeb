package com.maisamicoski.projdevweb.repository;

import com.maisamicoski.projdevweb.model.Inscricao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InscricaoRepository extends JpaRepository<Inscricao, Long> {
    List<Inscricao> findByAlunoId(Long alunoId);
}