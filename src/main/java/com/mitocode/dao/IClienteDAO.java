package com.mitocode.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mitocode.model.Cliente;

public interface IClienteDAO extends JpaRepository<Cliente, Integer>{

}
