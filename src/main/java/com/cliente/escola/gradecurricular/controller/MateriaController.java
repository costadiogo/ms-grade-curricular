package com.cliente.escola.gradecurricular.controller;

import java.util.List;

import com.cliente.escola.gradecurricular.entity.MateriaEntity;
import com.cliente.escola.gradecurricular.repository.MateriaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/materia")
    public ResponseEntity<List<MateriaEntity>> listMateria() {
        return ResponseEntity.status(HttpStatus.OK).body(materiaRepository.findAll());
    }

    @GetMapping("/materia/{id}")
    public ResponseEntity<MateriaEntity> listMateriaById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(materiaRepository.findById(id).get());
    }
    
    @PostMapping("/materia/create")
    public ResponseEntity<Boolean> createMateria(@RequestBody MateriaEntity materia) {
        try {
            this.materiaRepository.save(materia);
            return ResponseEntity.status(HttpStatus.OK).body(true);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);
        }
    }

    @PutMapping("/materia/{id}")
    public ResponseEntity<Boolean> updateMateria(@RequestBody MateriaEntity materia) {
        try {
            MateriaEntity  materiaAtualizada = this.materiaRepository.findById(materia.getId()).get();

            materiaAtualizada.setName(materia.getName());
            materiaAtualizada.setCode(materia.getCode());
            materiaAtualizada.setFrequency(materia.getFrequency());
            materiaAtualizada.setHour(materia.getHour());

            this.materiaRepository.save(materiaAtualizada);

            return ResponseEntity.status(HttpStatus.OK).body(true);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);
        }
    }
}
