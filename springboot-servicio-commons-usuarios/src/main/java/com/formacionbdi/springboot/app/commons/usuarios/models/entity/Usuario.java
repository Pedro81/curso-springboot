package com.formacionbdi.springboot.app.commons.usuarios.models.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="usuarios")
public class Usuario implements Serializable {

	private static final long serialVersionUID = 4002221912401133094L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private  Long id;
	
	@Column(unique = true, length = 20)
	private String username;
	
	@Column(length = 60)
	private String pasword;
	
	private Boolean enabled;
	private String nombre;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "usuarios_roles", 
	joinColumns = @JoinColumn(name="usuario_id"), 
	inverseJoinColumns = @JoinColumn(name="rol_id"),
	//Restriccion para que el par usuario_id, rol_id sea unico (un usuario no puede tenere el rol repetido)
	uniqueConstraints = {@UniqueConstraint(columnNames = {"usuario_id","rol_id"})})
	private List<Rol> roles;

	private String apellido;
	
	@Column(unique = true, length = 100)
	private String email;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasword() {
		return pasword;
	}

	public void setPasword(String pasword) {
		this.pasword = pasword;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	public List<Rol> getRoles() {
		return roles;
	}

	public void setRoles(List<Rol> roles) {
		this.roles = roles;
	}

}
