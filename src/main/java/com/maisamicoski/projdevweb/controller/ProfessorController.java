package com.maisamicoski.projdevweb.controller;

import com.maisamicoski.projdevweb.model.Professor;
import com.maisamicoski.projdevweb.model.Professor;
import com.maisamicoski.projdevweb.service.ProfessorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequiredArgsConstructor
@RestController
@RequestMapping("professores")
public class ProfessorController {

    private final ProfessorService professorService;

    public ProfessorController(ProfessorService professorService) {
        this.professorService = professorService;
    }
    
    @GetMapping
    public List<Professor> recuperarProfessors(){return professorService.recuperarProfessores();}

    @PostMapping
    public Professor cadastrarProfessor(@RequestBody Professor professor){return professorService.cadastrarProfessor(professor);}

    @PutMapping
    public Professor alterarProfessor(@RequestBody Professor professor){return professorService.alterarProfessor(professor);}

    @GetMapping("{idProfessor}")
    public ResponseEntity<ProfessorDTO> recuperarProfessorPorId(@PathVariable("idProfessor") Long id){
        Professor professor = professorService.recuperarProfessorPorId(id);
        ProfessorDTO professorDTO = new ProfessorDTO(professor.getId(), professor.getNome(), professor.getEmail());
        System.out.println(professorDTO.id());
        return ResponseEntity.ok(professorDTO);
    }
    @DeleteMapping("{idProfessor}")
    public ResponseEntity<Void> removerProfessorPorId(@PathVariable("idProfessor") Long id) {
        professorService.removerProfessorPorId(id);
        return ResponseEntity.ok().build();
    }
}
