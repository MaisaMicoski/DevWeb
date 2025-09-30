package com.maisamicoski.projdevweb.controller;

import com.maisamicoski.projdevweb.model.Aluno;
import com.maisamicoski.projdevweb.model.Turma;
import com.maisamicoski.projdevweb.service.TurmaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PutMapping("/{turmaId}/associar-disciplina/{disciplinaId}")
    public ResponseEntity<Turma> associarDisciplina(
            @PathVariable Long turmaId,
            @PathVariable Long disciplinaId) {

        Turma turmaAtualizada = turmaService.associarDisciplina(turmaId, disciplinaId);
        return ResponseEntity.ok(turmaAtualizada);
    }

    @PostMapping
    public Turma cadastrarTurma(@RequestBody Turma turma) {

        return turmaService.cadastrarTurma(turma);
    }
    @GetMapping
    public List<Turma> recuperarTurmas(){return turmaService.recuperarTurmas();}

    @DeleteMapping("{idTurma}")
    public ResponseEntity<Void> removerTurmaPorId(@PathVariable("idTurma") Long id) {
        turmaService.removerTurmaPorId(id);
        return ResponseEntity.ok().build();
    }
    @GetMapping("paginacao")
    public ResultadoPaginado<Turma> recuperarTurmasComPaginacao(
            @RequestParam(name = "pagina", defaultValue = "0") int pagina,
            @RequestParam(name = "tamanho", defaultValue = "5") int tamanho
    ) {
        Pageable pageable = PageRequest.of(pagina, tamanho);
        Page<Turma> page = turmaService.recuperarTurmasComPaginacao(pageable);
        return new ResultadoPaginado<Turma>(
                page.getTotalElements(),
                page.getTotalPages(),
                page.getNumber(),
                page.getContent());
    }
//    @GetMapping("{idTurma}")
//    public ResponseEntity<TurmaDTO> recuperarAlunoPorId(@PathVariable ("idAluno") Long id){
//        Aluno aluno = alunoService.recuperarAlunoPorId(id);
//        AlunoDTO alunoDTO = new AlunoDTO(aluno.getId(), aluno.getNome(), aluno.getEmail());
//        System.out.println(alunoDTO.id());
//        return ResponseEntity.ok(alunoDTO);
//    }
}
