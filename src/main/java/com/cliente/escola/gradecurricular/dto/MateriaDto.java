package com.cliente.escola.gradecurricular.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MateriaDto {

    
    private Long id;

    @NotBlank(message = "Informe o nome da matéria!")
    private String name;

    @Min(value = 25, message = "O mínimo permitido é de 25 horas por matéria!")
    @Max(value = 120, message = "O máximo permitido é de 120 horas por matéria!")
    private int hour;

    @NotBlank(message = "Informe o código da matéria!")
    private String code;

    @Min(value = 1, message = "A frequência mínima permitida é de 1 vez por ano!")
    @Max(value = 2, message = "A frequência máxima permitida é de 2 vezes por ano!")
    private int frequency;
    
}

//A classe DTO é um espelho da entidade...