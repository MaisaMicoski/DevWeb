package com.maisamicoski.projdevweb.controller;

import com.maisamicoski.projdevweb.model.Turma;
import com.maisamicoski.projdevweb.service.TurmaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RequiredArgsConstructor
@RestController
@RequestMapping("turmas")
public class TurmaController {

    @Autowired
    private TurmaService turmaService;

    @PutMapping("/{turmaId}/associar-professor/{professorId}")
    public ResponseEntity<Turma> associarProfessor(
            @PathVariable Long turmaId,
            @PathVariable Long professorId) {

        Turma turmaAtualizada = turmaService.associarProfessor(turmaId, professorId);
        return ResponseEntity.ok(turmaAtualizada);
}

    @PostMapping
    public Turma cadastrarTurma(@RequestBody Turma turma) {

        return turmaService.cadastrarTurma(turma);
    }


    @DeleteMapping("{idTurma}")
    public ResponseEntity<Void> removerTurmaPorId(@PathVariable("idTurma") Long id) {
        turmaService.removerTurmaPorId(id);
        return ResponseEntity.ok().build();
    }

}
