package com.estudos.ecommerce.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.estudos.ecommerce.exception.UsuarioNaoEncontradoException;
import com.estudos.ecommerce.model.Usuario;
import com.estudos.ecommerce.repository.UsuarioRepository;
import com.estudos.ecommerce.security.JwtUtil;

@Service
public class AuthService {

    private final UsuarioService usuarioService;
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
	
	public AuthService(UsuarioService usuarioService, UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
		this.usuarioService = usuarioService; 
		this.usuarioRepository = usuarioRepository;
		this.passwordEncoder = passwordEncoder;
		
	}
	
	public String cadastrarUsuario(String username, String email, String passsword) {
		Usuario usuario = usuarioService.cadastrarUsuario(username, email, passsword);
		
		return JwtUtil.generateToken(username);
		
	}
	
	public String login(String email, String password) {
		Usuario usuario = usuarioRepository.findByEmail(email)
											.orElseThrow(() -> new UsuarioNaoEncontradoException("UsuarioNaoEncontrado"));
		
		if(!passwordEncoder.matches(password, usuario.getPassword())) {
			throw new UsuarioNaoEncontradoException("Senha invalida");
			
		}
		
		return JwtUtil.generateToken(usuario.getUsername());
		
		
		
	}

}
