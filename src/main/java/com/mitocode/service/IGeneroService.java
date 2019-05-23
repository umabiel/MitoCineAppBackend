package com.mitocode.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.mitocode.model.Genero;

public interface IGeneroService extends ICRUD<Genero>{

	public Page<Genero> listarPageable(Pageable pageable);
}
