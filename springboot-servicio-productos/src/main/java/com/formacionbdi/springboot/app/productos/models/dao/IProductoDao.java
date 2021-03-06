package com.formacionbdi.springboot.app.productos.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.formacionbdi.springboot.app.commons.models.entity.Producto;
	

/**
 * 
 * @author pedro
 * 
 * Clase Dao para trabajar con la tabla de productos.
 * 
 * CrudRepository nos oferce metodos para acciones comunes:
 * 	guardar
 * 	buscar
 * 	eliminar
 * 
 * Podemos personalizar metodos con la anotación @Query cuya implementacios se realiza
 * en tiempo de ejecución
 * 
 * CrudRepository<T, ID> 
 * 		T --> Entidad
 * 		ID --> Tipo de la Clave primaria
 * 
 * Al heredar de CrudRepository, no es necesario reqgistrar nuestro Dao para la injección de dependencia
 * lo hace la clase padre (¿Es cierto?  )
 *
 */
public interface IProductoDao extends 	CrudRepository<Producto,Long> {

}
