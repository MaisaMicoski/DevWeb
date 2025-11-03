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

//@CrossOrigin("http://localhost:5173")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/turmas")
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
//    @GetMapping
//    public List<Turma> recuperarTurmas(){return turmaService.recuperarTurmas();}

    @GetMapping("/todas")
    public ResponseEntity<List<TurmaDTO>> recuperarTodasAsTurmas() {
        List<TurmaDTO> turmas = turmaService.recuperarTodasAsTurmas();
        return ResponseEntity.ok(turmas);
    }
    @DeleteMapping("{idTurma}")
    public ResponseEntity<Void> removerTurmaPorId(@PathVariable("idTurma") Long id) {
        turmaService.removerTurmaPorId(id);
        return ResponseEntity.ok().build();
    }
//    @GetMapping("paginacao")
//    public ResultadoPaginado<Turma> recuperarTurmasComPaginacao(
//            @RequestParam(name = "nome", defaultValue = "") String nome,
//            @RequestParam(name = "pagina", defaultValue = "0") int pagina,
//            @RequestParam(name = "tamanho", defaultValue = "5") int tamanho
//    ) {
//        Pageable pageable = PageRequest.of(pagina, tamanho);
//        Page<Turma> page = turmaService.recuperarTurmasComPaginacao(nome, pageable);
//        return new ResultadoPaginado<Turma>(
//                page.getTotalElements(),
//                page.getTotalPages(),
//                page.getNumber(),
//                page.getContent());
//    }
 //Em TurmaController.java
@GetMapping
public ResponseEntity<Page<TurmaDTO>> recuperarTurmasComPaginacao(
        @RequestParam(name = "nome", defaultValue = "") String nome,
        Pageable pageable) {

    Page<TurmaDTO> pageDto = turmaService.recuperarTurmasComPaginacao(nome, pageable);
    return ResponseEntity.ok(pageDto);
}
    @GetMapping("{idTurma}")
    public ResponseEntity<TurmaDTO> recuperarTurmaPorId(@PathVariable ("idTurma")Long id) {
        TurmaDTO turmaDto = turmaService.recuperarDetalhesDaTurma(id);
        return ResponseEntity.ok(turmaDto);
    }
    @GetMapping("/{id}/alunos")
    public ResponseEntity<Page<AlunoDTO>> recuperarAlunosDaTurmaPaginado(
            @PathVariable Long id,
            Pageable pageable) {

        Page<AlunoDTO> alunosDtoPage = turmaService.recuperarAlunosPorTurmaPaginado(id, pageable);
        return ResponseEntity.ok(alunosDtoPage);
    }
    @GetMapping("/{idTurma}/alunos/todos")
    public ResponseEntity<List<AlunoDTO>> recuperarTodosAlunosDaTurma(@PathVariable("idTurma") Long id) {
        List<AlunoDTO> alunosDto = turmaService.recuperarAlunosPorTurma(id);
        return ResponseEntity.ok(alunosDto);
    }
//    @GetMapping("/{idTurma}/alunos")
//    public ResponseEntity<List<AlunoDTO>> recuperarAlunosDaTurma(@PathVariable ("idTurma") Long id) {
//        List<AlunoDTO> alunosDto = turmaService.recuperarAlunosPorTurma(id);
//        return ResponseEntity.ok(alunosDto);
//    }
//    @GetMapping("/{turmaId}/recuperar-alunos-por-inscricoes")
//    public List<Aluno> recuperarAlunosPorInscricoes(
//            @RequestBody()
 //   )
}
