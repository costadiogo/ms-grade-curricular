package com.cliente.escola.gradecurricular.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_materia")
@Data
@NoArgsConstructor
public class MateriaEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonInclude(Include.NON_EMPTY)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonInclude(Include.NON_EMPTY)
    @Column(name = "name")
    private String name;

    @Column(name = "hrs")
    private int hour;

    @JsonInclude(Include.NON_EMPTY)
    @Column(name = "cd")
    private String code;

    @Column(name = "freq")
    private int frequency;

    
}
