package com.mitocode.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.mitocode.model.Comida;

public interface IComidaDAO extends JpaRepository<Comida, Integer> {

	@Modifying
	@Query("UPDATE Comida set foto = :foto where id = :id")
	void modificarFoto(@Param("id") Integer id, @Param("foto") byte[] foto);
}
