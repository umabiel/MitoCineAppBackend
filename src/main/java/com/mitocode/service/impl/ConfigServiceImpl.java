package com.mitocode.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mitocode.dao.IConfigDAO;
import com.mitocode.model.Config;
import com.mitocode.service.IConfigService;

@Service
public class ConfigServiceImpl implements IConfigService {

	@Autowired
	private IConfigDAO dao;

	@Override
	public Config registrar(Config t){
		return dao.save(t);
	}

	@Override
	public Config modificar(Config t){
		return dao.save(t);
	}

	@Override
	public List<Config> listar() {
		return dao.findAll();
	}

	@Override
	public Config leer(Integer id){
		Optional<Config> op = dao.findById(id);
		return op.isPresent() ? op.get() : new Config();
	}

	@Override
	public void eliminar(Integer id) {
		dao.deleteById(id);
	}

	@Override
	public Config leerParametro(String param) {
		return dao.findByParametro(param);
	}

}
