package com.maisamicoski.projdevweb.controller;


import com.maisamicoski.projdevweb.model.Aluno;
import com.maisamicoski.projdevweb.service.AlunoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("http://localhost:5173")
@RequiredArgsConstructor
@RestController
@RequestMapping("alunos")
public class AlunoController {
    private final AlunoService alunoService;

    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }
    @GetMapping
    public List<Aluno> recuperarAlunos(){return alunoService.recuperarAlunos();}

    @PostMapping
    public Aluno cadastrarAluno(@RequestBody Aluno aluno){
        return alunoService.cadastrarAluno(aluno);}

    @PutMapping
    public Aluno alterarAluno(@RequestBody Aluno aluno){return alunoService.alterarAluno(aluno);}

    @GetMapping("{idAluno}")
    public ResponseEntity<AlunoDTO> recuperarAlunoPorId(@PathVariable ("idAluno") Long id){
        Aluno aluno = alunoService.recuperarAlunoPorId(id);
        AlunoDTO alunoDTO = new AlunoDTO(aluno.getId(), aluno.getNome(), aluno.getEmail());
        System.out.println(alunoDTO.id());
        return ResponseEntity.ok(alunoDTO);
    }
    @DeleteMapping("{idAluno}")
    public ResponseEntity<Void> removerAlunoPorId(@PathVariable("idAluno") Long id) {
        alunoService.removerAlunoPorId(id);
        return ResponseEntity.ok().build();
    }


}
