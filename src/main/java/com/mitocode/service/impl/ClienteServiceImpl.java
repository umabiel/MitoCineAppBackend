package com.mitocode.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitocode.dao.IClienteDAO;
import com.mitocode.model.Cliente;
import com.mitocode.service.IClienteService;

@Service
public class ClienteServiceImpl implements IClienteService{

	@Autowired
	private IClienteDAO dao;
	
	@Override
	public Cliente registrar(Cliente obj) {
		return dao.save(obj);
	}

	@Override
	public Cliente modificar(Cliente obj) {
		return dao.save(obj);
	}

	@Override
	public List<Cliente> listar() {
		return dao.findAll();
	}

	@Override
	public Cliente leer(Integer id) {
		Optional<Cliente> op = dao.findById(id);
		return op.isPresent() ? op.get() : new Cliente();
	}

	@Override
	public void eliminar(Integer id) {
		dao.deleteById(id);
	}

	
}
