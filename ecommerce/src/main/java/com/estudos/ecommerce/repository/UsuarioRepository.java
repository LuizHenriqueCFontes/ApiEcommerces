package com.estudos.ecommerce.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.estudos.ecommerce.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	public Optional<Usuario> findByUsername(String username);
	public Optional<Usuario> findByEmail(String email);

}
