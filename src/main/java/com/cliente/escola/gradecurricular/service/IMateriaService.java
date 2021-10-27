package com.cliente.escola.gradecurricular.service;

import java.util.List;

import com.cliente.escola.gradecurricular.dto.MateriaDto;

public interface IMateriaService {


    public Boolean atualizar(final MateriaDto materia);

    public Boolean excluir(final Long id);

    /*
	 * LISTAR todas matérias. 
	 */
	public List<MateriaDto> listar();
	
	/*
	 * CONSULTA uma matéria a partir do ID.  
	 */
	public MateriaDto consultar(final Long id);
	
	/*
	 * CADASTRAR uma matéria.  
	 */
	public Boolean cadastrar(final MateriaDto materia);

	/**
	 * Listar matéria por horário minímo de horas
	 */
	public List<MateriaDto> listByHourMinimum(int hourMin);

	/**
	 * Listar matéria por frequência
	 */
	public List<MateriaDto> listByFrequency(int frequency);
}
