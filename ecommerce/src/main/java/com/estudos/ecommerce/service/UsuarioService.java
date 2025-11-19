package com.estudos.ecommerce.service;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.estudos.ecommerce.exception.UsuarioNaoEncontradoException;
import com.estudos.ecommerce.model.Usuario;
import com.estudos.ecommerce.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	private final UsuarioRepository usuarioRepository;
	private final PasswordEncoder passwordEncoder;
	
	public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
		this.usuarioRepository = usuarioRepository;
		this.passwordEncoder = passwordEncoder;
	}
	
	public Usuario cadastrarUsuario(String username, String email, String password) {
		String senhaCriptografada = passwordEncoder.encode(password);
		
		Usuario usuario = new Usuario(username, email, senhaCriptografada);
		
		return usuarioRepository.save(usuario);
		
	}
	
	public List<Usuario> listarUsuarios() {
		return usuarioRepository.findAll();
		
	}
	
	public Usuario editarUsuario(Long id, Usuario usuario) {
		Usuario usuarioAtual = usuarioRepository.findById(id)
										.orElseThrow(() -> new UsuarioNaoEncontradoException("Usuario não encontrado"));
		
		usuarioAtual.setUsername(usuario.getUsername());
		usuarioAtual.setEmail(usuario.getEmail());
		usuarioAtual.setPassword(passwordEncoder.encode(usuario.getPassword()));
		
		return usuarioRepository.save(usuarioAtual);
					
		
		
	}
	
	public void deletarUsuario(Long id) {
		if(!usuarioRepository.existsById(id)) {
			throw new UsuarioNaoEncontradoException("usuario não encontrado");
			
		}
		
		usuarioRepository.deleteById(id);
		
	}
	


}
