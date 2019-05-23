package com.mitocode.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mitocode.model.Config;

public interface IConfigDAO extends JpaRepository<Config, Integer> {
	
	Config findByParametro(String param);
}
