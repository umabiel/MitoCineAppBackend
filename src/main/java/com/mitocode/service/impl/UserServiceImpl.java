package com.mitocode.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mitocode.dao.IUsuarioDAO;
import com.mitocode.model.Usuario;
import com.mitocode.service.IUsuarioService;

@Service("userDetailsService")
public class UserServiceImpl implements UserDetailsService, IUsuarioService {
	
	@Autowired
	private IUsuarioDAO userDAO;
	
	@Value("${mitocine.default-rol}")
	private Integer DEFAULT_ROL;
		
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario user = userDAO.findOneByUsername(username); //from usuario where username := username
		
		if (user == null) {
			throw new UsernameNotFoundException(String.format("Usuario no existe", username));
		}
		
		List<GrantedAuthority> authorities = new ArrayList<>();
		
		user.getRoles().forEach( role -> {
			authorities.add(new SimpleGrantedAuthority(role.getNombre()));
		});
		
		UserDetails userDetails = new User(user.getUsername(), user.getPassword(), authorities);
		
		return userDetails;
	}

	@Transactional
	@Override
	public Usuario registrarTransaccional(Usuario usuario) {	
		
		Usuario u = userDAO.save(usuario);	
		userDAO.registrarRolPorDefecto(u.getIdUsuario(), DEFAULT_ROL);
		return u;
		
	}

}
