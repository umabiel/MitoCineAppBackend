package com.mitocode.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mitocode.exception.ModeloNotFoundException;
import com.mitocode.model.Pelicula;
import com.mitocode.service.IPeliculaService;

@RestController
@RequestMapping("/peliculas")
public class PeliculaController {

	@Autowired
	private IPeliculaService service;
	
	//@PreAuthorize("@restAuthService.hasAccess('listar')")
	//@PreAuthorize("hasAuthority('ADMIN') or hasAuthority('USER')")
	@GetMapping
	public ResponseEntity<List<Pelicula>> listar(){
		List<Pelicula> lista = service.listar();
		return new ResponseEntity<List<Pelicula>>(lista, HttpStatus.OK);		
	}
	
	@GetMapping(value = "/{id}")
	public Resource<Pelicula> listarPorId(@PathVariable("id") Integer id){
		
		Pelicula gen = service.leer(id);
		if(gen.getIdPelicula() == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO : " + id);
		}
		
		Resource<Pelicula> resource = new Resource<Pelicula>(gen);
		// /generos/{4}
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).listarPorId(id));
		resource.add(linkTo.withRel("genero-resource"));
		
		return resource;
	}
	
	@PostMapping
	public ResponseEntity<Pelicula> registrar(@RequestBody Pelicula gen) {
		Pelicula g = service.registrar(gen);
			
		// localhost:8080/generos/2
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(g.getIdPelicula()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping
	public ResponseEntity<Object> modificar(@RequestBody Pelicula gen) {
		service.modificar(gen);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}")
	public void eliminar(@PathVariable("id") Integer id){
		Pelicula gen = service.leer(id);

		if (gen.getIdPelicula() == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO: " + id);
		} else {
			service.eliminar(id);
		}
	}
	
	
	
}
