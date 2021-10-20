package com.cliente.escola.gradecurricular.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.cliente.escola.gradecurricular.entity.MateriaEntity;
import com.cliente.escola.gradecurricular.exception.MateriaException;
import com.cliente.escola.gradecurricular.repository.MateriaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class MateriaService implements IMateriaService {

    @Autowired
    private MateriaRepository materiaRepository;

    @Override
    public List<MateriaEntity> listar() {
        try {
            return this.materiaRepository.findAll();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    @Override
    public MateriaEntity consultar(Long id) {
        try {
            Optional<MateriaEntity> materiaOptional = this.materiaRepository.findById(id);

            if (materiaOptional.isPresent()) {
                return materiaOptional.get();
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
    public Boolean cadastrar(MateriaEntity materia) {
        try {
            this.materiaRepository.save(materia);

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Boolean atualizar(MateriaEntity materia) {
        try {
            Optional<MateriaEntity> materiaOptional = this.materiaRepository.findById(materia.getId());

            if (materiaOptional.isPresent()) {
                MateriaEntity materiaAtualizada = materiaOptional.get();

                materiaAtualizada.setName(materia.getName());
                materiaAtualizada.setCode(materia.getCode());
                materiaAtualizada.setFrequency(materia.getFrequency());
                materiaAtualizada.setHour(materia.getHour());

                this.materiaRepository.save(materiaAtualizada);

                return true;
            } else {

             throw new MateriaException("Não foi possível atualizar está matéria. Verifique se a matéria que está tentando atualizar está cadastrada em nosso banco de dados", HttpStatus.NOT_FOUND);
            }

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
