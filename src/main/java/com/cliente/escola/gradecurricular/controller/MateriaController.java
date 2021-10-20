package com.cliente.escola.gradecurricular.controller;

import java.util.List;

import com.cliente.escola.gradecurricular.entity.MateriaEntity;
import com.cliente.escola.gradecurricular.repository.MateriaRepository;
import com.cliente.escola.gradecurricular.service.MateriaService;

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
    private MateriaRepository materiaRepository;

    @Autowired
    private MateriaService materiaService;

    //Listando todas a Matérias
    @GetMapping("/materia")
    public ResponseEntity<List<MateriaEntity>> listMateria() {
        return ResponseEntity.status(HttpStatus.OK).body(materiaRepository.findAll());
    }


    //Listando as matérias por ID
    @GetMapping("/materia/{id}")
    public ResponseEntity<MateriaEntity> listMateriaById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(this.materiaService.consultar(id));
    }

    //Criando as Matérias
    @PostMapping("/materia/create")
    public ResponseEntity<Boolean> createMateria(@RequestBody MateriaEntity materia) {
        try {
            this.materiaRepository.save(materia);
            return ResponseEntity.status(HttpStatus.CREATED).body(true);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);
        }
    }

    //Atualizando as Matérias
    @PutMapping("/materia")
    public ResponseEntity<Boolean> updateMateria(@RequestBody MateriaEntity materia) {

       try {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(this.materiaService.atualizar(materia));
       } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);
       }
    }

    //Deletando as matérias
    @DeleteMapping("/materia/{id}")
    public ResponseEntity<Boolean> deleteMateria(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(this.materiaService.excluir(id));
    }

}
