package com.pna.pna.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pna.pna.model.UserLogin;
import com.pna.pna.model.Usuario;
import com.pna.pna.service.UsuarioService;

@RestController
@RequestMapping("/pna/usuarios")

@CrossOrigin("*")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@PostMapping("/logar")
	public ResponseEntity<UserLogin> Autentication(@RequestBody Optional<UserLogin> user)
	{
		return usuarioService.Logar(user).map(resp -> ResponseEntity.ok(resp)).orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
	}
	
	@PostMapping("/cadastrar")
	public ResponseEntity<Usuario> Post(@RequestBody Usuario usuario)
	{
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.CadastrarUsuario(usuario));
	}
	

}
