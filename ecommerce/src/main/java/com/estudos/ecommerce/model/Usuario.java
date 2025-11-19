package com.estudos.ecommerce.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuario")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String username;

	@Column(unique = true, nullable = false)
	private String email;
	
	@Column(unique = true, nullable = false)
	private String password;
	
	@Column(nullable = false)
	private String roles;

	public Usuario() {}
	
	public Usuario(String username, String email, String password) {
		this.username = username;
		this.email = email;
		this.password = password;
		
	}
	
	
	public Long getId() {
		return this.id;
		
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}


	
	

}
