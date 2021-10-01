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

import com.pna.pna.model.Postagem;
import com.pna.pna.repository.PostagemRepository;



@RestController
@RequestMapping("/pna/postagem")

@CrossOrigin("*")
public class PostagemController {

	@Autowired
	private PostagemRepository repositorio; 
		
	@GetMapping
	public ResponseEntity<List<Postagem>>todas()
	{
		return ResponseEntity.ok(repositorio.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Postagem> GetById(@PathVariable Long id)
	{
		return repositorio.findById(id).map(resp ->ResponseEntity.ok(resp)).orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/titulo/{titulo}")
	public ResponseEntity<List<Postagem>> GetById(@PathVariable String titulo)
	{
		
		return ResponseEntity.ok(repositorio.findAllByTituloContainingIgnoreCase(titulo));
		
		
	}
	
	@PostMapping
	public ResponseEntity<Postagem> postar(@RequestBody Postagem postagem)
	{
		try
		{
		return ResponseEntity.status(HttpStatus.CREATED).body(repositorio.save(postagem));
		}
		catch(Exception e)
		{
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	@PutMapping
	public ResponseEntity<Postagem> alterar(@RequestBody Postagem postagem)
	{
		try
		{
		return ResponseEntity.status(HttpStatus.OK).body(repositorio.save(postagem));
		}
		catch(Exception e)
		{
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Postagem> deletar(@PathVariable Long id)
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
