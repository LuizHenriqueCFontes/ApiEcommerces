package com.estudos.ecommerce.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.estudos.ecommerce.model.Usuario;

import com.estudos.ecommerce.repository.UsuarioRepository;

@Service
public class UsuarioDetails implements UserDetailsService {
	
	private final UsuarioRepository usuarioRepository;
	
	public UsuarioDetails(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
		
	}
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepository.findByUsername(username)
										.orElseThrow(() -> new UsernameNotFoundException("Usuario não encontrado"));
		
		return User.builder()
				.username(usuario.getUsername())
				.password(usuario.getPassword())
				.roles(usuario.getRoles())
				.build();		
	}

}
