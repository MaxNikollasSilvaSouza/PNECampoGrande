package com.pna.pna.service;

import java.nio.charset.Charset;
import java.util.Optional;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.pna.pna.model.UserLogin;
import com.pna.pna.model.Usuario;
import com.pna.pna.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository repository;
	
	public Usuario CadastrarUsuario(Usuario usuario)
	{
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String  senhaCodificada = encoder.encode(usuario.getSenha());
		
		usuario.setSenha(senhaCodificada);
		
		return repository.save(usuario);
		
	}
	
	public Optional<UserLogin> Logar(Optional<UserLogin>user)
	{
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		Optional<Usuario> usuario_salvo = repository.findByUsuario(user.get().getUsuario());
		
		if(usuario_salvo.isPresent())
		{
			if(encoder.matches(user.get().getSenha(), usuario_salvo.get().getSenha()));
			{
				String auth = user.get().getUsuario() + ":"+user.get().getSenha();
				byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
				String authHeader = "Basic " + new String(encodedAuth);
			
				user.get().setToken(authHeader);
				user.get().setNome(usuario_salvo.get().getNome());
			
				return user;
			}
		}
		return null;
		
		
	}
	
	
	
	
	
	
	
}
