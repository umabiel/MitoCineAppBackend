package com.mitocode.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mitocode.dao.IGeneroDAO;
import com.mitocode.model.Genero;
import com.mitocode.service.IGeneroService;

@Service
public class GeneroServiceImpl implements IGeneroService{

	@Autowired
	private IGeneroDAO dao;
	
	@Override
	public Genero registrar(Genero obj) {
		return dao.save(obj);
	}

	@Override
	public Genero modificar(Genero obj) {
		return dao.save(obj);
	}

	@Override
	public List<Genero> listar() {
		return dao.findAll();
	}
	
	@Override
	public Page<Genero> listarPageable(Pageable pageable) {
		return dao.findAll(pageable);
	}

	@Override
	public Genero leer(Integer id) {
		Optional<Genero> op = dao.findById(id);
		return op.isPresent() ? op.get() : new Genero();
	}

	@Override
	public void eliminar(Integer id) {
		dao.deleteById(id);
	}

	
}
