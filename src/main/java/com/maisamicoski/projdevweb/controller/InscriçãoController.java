package com.maisamicoski.projdevweb.controller;

import com.maisamicoski.projdevweb.model.Aluno;
import com.maisamicoski.projdevweb.model.Inscricao;
import com.maisamicoski.projdevweb.model.Turma;
import com.maisamicoski.projdevweb.service.InscricaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("inscricoes")
public class InscriçãoController {

    @Autowired
    private InscricaoService inscricaoService;

     //Endpoint para criar uma nova inscrição
    @PostMapping
    public ResponseEntity<Inscricao> cadastrarInscricao(@RequestBody InscricaoRequestDTO inscricaoRequestDTO) {
        Inscricao novaInscricao = inscricaoService.cadastrarInscricao(inscricaoRequestDTO.alunoId(), inscricaoRequestDTO.turmaId());
        return new ResponseEntity<>(novaInscricao, HttpStatus.CREATED);
    }

//    @PostMapping
//    public Turma cadastrarInscricao(@RequestBody Aluno aluno) {
//
//        return inscricaoService.cadastrarInscricao();
//    }


    @DeleteMapping("{idInscricao}")
    public ResponseEntity<Void> removerInscricaoPorId(@PathVariable("idInscricao") Long id) {
       inscricaoService.removerInscricaoPorId(id);
        return ResponseEntity.ok().build();
    }

}
