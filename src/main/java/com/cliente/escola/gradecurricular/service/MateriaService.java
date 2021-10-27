package com.cliente.escola.gradecurricular.service;

import java.util.List;
import java.util.Optional;

import com.cliente.escola.gradecurricular.controller.MateriaController;
import com.cliente.escola.gradecurricular.dto.MateriaDto;
import com.cliente.escola.gradecurricular.entity.MateriaEntity;
import com.cliente.escola.gradecurricular.exception.MateriaException;
import com.cliente.escola.gradecurricular.repository.MateriaRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@CacheConfig(cacheNames = "materia")
@Service
public class MateriaService implements IMateriaService {

    private static final String MESSAGE_ERROR = "Erro interno identificado contate o suporte.";
    private static final String MATERIA_NAO_ENCONTRADA = "Matéria não encontrada";

    @Autowired
    private MateriaRepository materiaRepository;
    private ModelMapper mapper;

    @Autowired
    public MateriaService(MateriaRepository materiaRepository) {
        this.mapper = new ModelMapper();
        this.materiaRepository = materiaRepository;

    }

    @CachePut(unless = "#result.size() < 3")
    @Override
    public List<MateriaDto> listar() {
        try {

            List<MateriaDto> materiaDto = this.mapper.map(this.materiaRepository.findAll(),
                    new TypeToken<List<MateriaDto>>() {}.getType());

            materiaDto.forEach(mat -> mat.add(WebMvcLinkBuilder
                    .linkTo(WebMvcLinkBuilder.methodOn(MateriaController.class).listMateriaById(mat.getId()))
                    .withSelfRel()));

            return materiaDto;

        } catch (Exception e) {
            throw new MateriaException(MESSAGE_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CachePut(key = "#id")
    @Override
    public MateriaDto consultar(Long id) {
        try {
            Optional<MateriaEntity> materiaOptional = this.materiaRepository.findById(id);

            if (materiaOptional.isPresent()) {
                return this.mapper.map(materiaOptional.get(), MateriaDto.class);
            } else {

                throw new MateriaException(MATERIA_NAO_ENCONTRADA, HttpStatus.NOT_FOUND);
            }      
          
        } catch (MateriaException m) {
            throw m;

        } catch (Exception e) {
            throw new MateriaException(MESSAGE_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public Boolean cadastrar(MateriaDto materia) {
        try {
            MateriaEntity materiaEnt = this.mapper.map(materia, MateriaEntity.class);
            this.materiaRepository.save(materiaEnt);

            return true;
        } catch (Exception e) {
            throw new MateriaException(MESSAGE_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public Boolean atualizar(MateriaDto materia) {
        try {
            this.consultar(materia.getId());
            MateriaEntity materiaEntityAtualizada = this.mapper.map(materia, MateriaEntity.class);

            this.materiaRepository.save(materiaEntityAtualizada);

            return Boolean.TRUE;

        } catch (MateriaException m) {
            throw m;
        } catch (Exception e) {
            throw new MateriaException(MESSAGE_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public Boolean excluir(Long id) {
        try {
            this.materiaRepository.deleteById(id);
            return Boolean.TRUE;
        } catch (Exception e) {
            throw new MateriaException(MESSAGE_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
