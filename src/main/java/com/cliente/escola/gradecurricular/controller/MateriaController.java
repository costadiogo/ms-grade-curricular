package com.cliente.escola.gradecurricular.controller;

import java.util.List;

import com.cliente.escola.gradecurricular.dto.MateriaDto;
import com.cliente.escola.gradecurricular.service.IMateriaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api")
public class MateriaController {

    @Autowired
    private IMateriaService materiaService;

    //Listando todas a Matérias
    @GetMapping("/materia")
    public ResponseEntity<List<MateriaDto>> listMateria() {
        return ResponseEntity.status(HttpStatus.OK).body(this.materiaService.listar());
    }


    //Listando as matérias por ID
    @GetMapping("/materia/{id}")
    public ResponseEntity<MateriaDto> listMateriaById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(this.materiaService.consultar(id));
    }

    //Criando as Matérias
    @PostMapping("/materia/create")
    public ResponseEntity<Boolean> createMateria(@RequestBody MateriaDto materia){
        return ResponseEntity.status(HttpStatus.CREATED).body(this.materiaService.cadastrar(materia));
    }

    //Atualizando as Matérias
    @PutMapping("/materia")
    public ResponseEntity<Boolean> updateMateria(@RequestBody MateriaDto materia) {
        return ResponseEntity.status(HttpStatus.OK).body(this.materiaService.atualizar(materia));
    }

    //Deletando as matérias
    @DeleteMapping("/materia/{id}")
    public ResponseEntity<Boolean> deleteMateria(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(this.materiaService.excluir(id));
    }

}
