package com.cliente.escola.gradecurricular.controller;

import java.util.List;

import javax.validation.Valid;

import com.cliente.escola.gradecurricular.dto.MateriaDto;
import com.cliente.escola.gradecurricular.model.Response;
import com.cliente.escola.gradecurricular.service.IMateriaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
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

    private static final String DELETE = "DELETE";

    private static final String UPDATE = "UPDATE";

    private static final String LIST = "GET_ALL";

    @Autowired
    private IMateriaService materiaService;

    //Listando todas a Matérias
    @GetMapping("/materia")
    public ResponseEntity<Response<List<MateriaDto>>> listMateria() {
        Response<List<MateriaDto>> response = new Response<>();
        response.setData(this.materiaService.listar());
        response.setStatusCode(HttpStatus.OK.value());
        response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MateriaController.class).listMateria())
                .withSelfRel());

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    //Listando as matérias por ID
    @GetMapping("/materia/{id}")
    public ResponseEntity<Response<MateriaDto>> listMateriaById(@PathVariable Long id) {
        Response<MateriaDto> response = new Response<>();
        MateriaDto materia = this.materiaService.consultar(id);
        response.setData(materia);
        response.setStatusCode(HttpStatus.OK.value());
        response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MateriaController.class).listMateriaById(id))
                .withSelfRel());
        response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MateriaController.class).deleteMateria(id))
                .withRel(DELETE));
        response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MateriaController.class)
                        .updateMateria(materia))
                        .withRel(UPDATE));

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    //Criando as Matérias
    @PostMapping("/materia/create")
    public ResponseEntity<Response<Boolean>> createMateria(@Valid @RequestBody MateriaDto materia){
        Response<Boolean> response = new Response<>();
        response.setData(this.materiaService.cadastrar(materia));
        response.setStatusCode(HttpStatus.CREATED.value());
        response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MateriaController.class)
                .createMateria(materia)).withSelfRel());
        response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MateriaController.class)
                .updateMateria(materia)).withRel(UPDATE));
        response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MateriaController.class)
                .listMateria()).withRel(LIST));

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    //Atualizando as Matérias
    @PutMapping("/materia")
    public ResponseEntity<Response<Boolean>> updateMateria(@Valid @RequestBody MateriaDto materia) {
        Response<Boolean> response = new Response<>();
        response.setData(this.materiaService.atualizar(materia));
        response.setStatusCode(HttpStatus.OK.value());
        response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MateriaController.class)
                .updateMateria(materia)).withSelfRel());
        response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MateriaController.class)
                .listMateria()).withRel(LIST));

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    //Deletando as matérias
    @DeleteMapping("/materia/{id}")
    public ResponseEntity<Response<Boolean>> deleteMateria(@PathVariable Long id) {
        Response<Boolean> response = new Response<>();
        response.setData(this.materiaService.excluir(id));
        response.setStatusCode(HttpStatus.OK.value());
        response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MateriaController.class)
                .deleteMateria(id)).withSelfRel());
        response.add(WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(MateriaController.class)
                .listMateria()).withRel(LIST));

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
