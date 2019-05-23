package com.mitocode.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitocode.dao.IPeliculaDAO;
import com.mitocode.model.Pelicula;
import com.mitocode.service.IPeliculaService;

@Service
public class PeliculaServiceImpl implements IPeliculaService{

	@Autowired
	private IPeliculaDAO dao;
	
	@Override
	public Pelicula registrar(Pelicula obj) {
		return dao.save(obj);
	}

	@Override
	public Pelicula modificar(Pelicula obj) {
		return dao.save(obj);
	}

	
	@Override
	public List<Pelicula> listar() {
		return dao.findAll();
	}

	@Override
	public Pelicula leer(Integer id) {
		Optional<Pelicula> op = dao.findById(id);
		return op.isPresent() ? op.get() : new Pelicula();
	}

	@Override
	public void eliminar(Integer id) {
		dao.deleteById(id);
	}

	
}
