package com.pna.pna.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.pna.pna.model.Tema;
import com.pna.pna.repository.TemaRepository;

@RestController
@RequestMapping("/pna/tema")

@CrossOrigin("*")
public class TemaController {
	
	@Autowired
	private TemaRepository repositorio;
	
	@GetMapping
	public ResponseEntity <List<Tema>> GetAll()
	{
		return ResponseEntity.ok(repositorio.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Tema> GetById(@PathVariable Long id)
	{
		return repositorio.findById(id).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Tema>> getByName(@PathVariable String nome)
	{
		try
		{
		return ResponseEntity.ok(repositorio.findAllByDescricaoContainingIgnoreCase(nome));
		}
		catch(Exception e)
		{
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	
	@PostMapping
	public ResponseEntity<Tema> postar (@RequestBody Tema tema)
	{
		try
		{
		return ResponseEntity.status(HttpStatus.CREATED).body(repositorio.save(tema));
		}
		catch(Exception e)
		{
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	@PutMapping
	public ResponseEntity<Tema> alterar (@RequestBody Tema tema)
	{
		try
		{
		return ResponseEntity.status(HttpStatus.OK).body(repositorio.save(tema));
		}
		catch(Exception e)
		{
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Tema> deletar(@PathVariable Long id)
	{
		try
		{
		repositorio.deleteById(id);
		return ResponseEntity.status(HttpStatus.OK).build();
		}
		catch(Exception e)
		{
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
	}
	

}
