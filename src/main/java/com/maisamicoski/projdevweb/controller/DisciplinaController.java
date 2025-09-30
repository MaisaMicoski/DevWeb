package com.maisamicoski.projdevweb.controller;

import com.maisamicoski.projdevweb.model.Disciplina;
import com.maisamicoski.projdevweb.model.Disciplina;
import com.maisamicoski.projdevweb.service.DisciplinaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequiredArgsConstructor
@RestController
@RequestMapping("disciplinas")
public class DisciplinaController {

    private final DisciplinaService disciplinaService;

    public DisciplinaController(DisciplinaService disciplinaService) {
        this.disciplinaService = disciplinaService;
    }

    @GetMapping
    public List<Disciplina> recuperarDisciplinas(){return disciplinaService.recuperarDisciplinaes();}

    @PostMapping
    public Disciplina cadastrarDisciplina(@RequestBody Disciplina disciplina){return disciplinaService.cadastrarDisciplina(disciplina);}

    @PutMapping
    public Disciplina alterarDisciplina(@RequestBody Disciplina disciplina){return disciplinaService.alterarDisciplina(disciplina);}

    @GetMapping("{idDisciplina}")
    public ResponseEntity<DisciplinaDTO> recuperarDisciplinaPorId(@PathVariable("idDisciplina") Long id){
        Disciplina disciplina = disciplinaService.recuperarDisciplinaPorId(id);
        DisciplinaDTO disciplinaDTO = new DisciplinaDTO(disciplina.getId(), disciplina.getNome(), disciplina.getCargaHoraria());
        System.out.println(disciplinaDTO.id());
        return ResponseEntity.ok(disciplinaDTO);
    }
    @DeleteMapping("{idDisciplina}")
    public ResponseEntity<Void> removerDisciplinaPorId(@PathVariable("idDisciplina") Long id) {
        disciplinaService.removerDisciplinaPorId(id);
        return ResponseEntity.ok().build();
    }
}
