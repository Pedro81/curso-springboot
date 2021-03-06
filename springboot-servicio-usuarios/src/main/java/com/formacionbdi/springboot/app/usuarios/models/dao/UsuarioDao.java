package com.formacionbdi.springboot.app.usuarios.models.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.PathVariable;

import com.formacionbdi.springboot.app.commons.usuarios.models.entity.Usuario;

@RepositoryRestResource (path = "usuarios")
public interface UsuarioDao extends PagingAndSortingRepository<Usuario, Long>{
	
	/*
	 * No es necesario implementar el metodo. JPA lo implementa en funcion del nombre del metodo.
	 *  
	 * Internamente esto hará un Select (find) en la tabla de Usuarios (Indicado en la declaracion del DAO) 
	 * Donde (By) Username sea el pasado por parametro.
	 * 
	 */
	@RestResource(path = "buscar-username")
	public Usuario findByUsername(@Param("username") String username);
	
	/*
	 * Tampoco es necesario implentar el metodo. JPA lanzará la query definida en @Query
	 * 
	 * En lugar de una query de JPA se puede lanzar una query nativa SQL para ello
	 * @Query(value = "SELECT username FROM usuarios WHERE username=?1", nativeQuery = true)
	 */
	@Query("select u from Usuario u where u.username=?1")
	public Usuario obtenerPorUsername(@PathVariable String username);

}
 