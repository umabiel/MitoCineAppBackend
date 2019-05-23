package com.mitocode.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
import com.mitocode.model.Genero;
import com.mitocode.service.IGeneroService;

@RestController
@RequestMapping("/generos")
//@CrossOrigin(origins = "http://localhost:4200")
public class GeneroController {

	@Autowired
	private IGeneroService service;
	
	@GetMapping
	public ResponseEntity<List<Genero>> listar(){
		List<Genero> lista = service.listar();
		return new ResponseEntity<List<Genero>>(lista, HttpStatus.OK);		
	}
	
	@GetMapping(value="/pageable", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Page<Genero>> listarPageable(Pageable pageable){
		Page<Genero> pacientes;
		pacientes = service.listarPageable(pageable);
		return new ResponseEntity<Page<Genero>>(pacientes, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	public Resource<Genero> listarPorId(@PathVariable("id") Integer id){
		
		Genero gen = service.leer(id);
		if(gen.getIdGenero() == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO : " + id);
		}
		
		Resource<Genero> resource = new Resource<Genero>(gen);
		// /generos/{4}
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).listarPorId(id));
		resource.add(linkTo.withRel("genero-resource"));
		
		return resource;
	}
	
	@PostMapping
	public ResponseEntity<Genero> registrar(@RequestBody Genero gen) {
		Genero g = service.registrar(gen);
			
		// localhost:8080/generos/2
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(g.getIdGenero()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping
	public ResponseEntity<Object> modificar(@RequestBody Genero gen) {
		service.modificar(gen);
		return new ResponseEntity<Object>(HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{id}")
	public void eliminar(@PathVariable("id") Integer id){
		Genero gen = service.leer(id);

		if (gen.getIdGenero() == null) {
			throw new ModeloNotFoundException("ID NO ENCONTRADO: " + id);
		} else {
			service.eliminar(id);
		}
	}
	
	
	
}
