package com.cliente.escola.gradecurricular.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.cliente.escola.gradecurricular.dto.MateriaDto;
import com.cliente.escola.gradecurricular.entity.MateriaEntity;
import com.cliente.escola.gradecurricular.exception.MateriaException;
import com.cliente.escola.gradecurricular.repository.MateriaRepository;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class MateriaService implements IMateriaService {

    @Autowired
    private MateriaRepository materiaRepository;
    private ModelMapper mapper;

    @Autowired
    public MateriaService(MateriaRepository materiaRepository) {
        this.mapper = new ModelMapper();
        this.materiaRepository = materiaRepository;

    }

    @Override
    public List<MateriaDto> listar() {
        try {
            return this.mapper.map(this.materiaRepository.findAll(), new TypeToken<List<MateriaDto>>() {}.getType());
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    @Override
    public MateriaDto consultar(Long id) {
        try {
            Optional<MateriaEntity> materiaOptional = this.materiaRepository.findById(id);

            if (materiaOptional.isPresent()) {
                return this.mapper.map(materiaOptional.get(), MateriaDto.class);
            } else {

                throw new MateriaException("Matéria não encontrada", HttpStatus.NOT_FOUND);
            }      
          
        } catch (MateriaException m) {
            throw m;

        } catch (Exception e) {
            throw new MateriaException("Erro interno.",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public Boolean cadastrar(MateriaDto materia) {
        try {
            MateriaEntity materiaEnt = this.mapper.map(materia, MateriaEntity.class);
            this.materiaRepository.save(materiaEnt);

            return true;
        } catch (Exception e) {
            return false;
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
            return false;
        } catch (Exception e) {
            throw new MateriaException("Erro interno.",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public Boolean excluir(Long id) {
        try {
            this.materiaRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
