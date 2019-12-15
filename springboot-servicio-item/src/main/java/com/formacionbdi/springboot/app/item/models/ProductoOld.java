package com.formacionbdi.springboot.app.item.models;

import java.util.Date;

public class ProductoOld {
	
	private Long id;
	private String nombre;
	private String descripcin;
	private Double precio;
	private Date createAt;
	
	private String port;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Double getPrecio() {
		return precio;
	}
	public void setPrecio(Double precio) {
		this.precio = precio;
	}
	public Date getCreateAt() {
		return createAt;
	}
	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}
	public String getDescripcin() {
		return descripcin;
	}
	public void setDescripcin(String descripcin) {
		this.descripcin = descripcin;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}	

}
