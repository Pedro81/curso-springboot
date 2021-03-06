package com.formacionbdi.springboot.app.productos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.formacionbdi.springboot.app.commons.models.entity.Producto;
import com.formacionbdi.springboot.app.productos.models.dao.IProductoDao;

// Registra la clase como un componente de spring para la injección de dependecias.
@Service
public class ProductoServiceImpl implements IProductoService {
	
	@Autowired
	private IProductoDao productoDao;
	@Override
	//Opcional hacemos el metodo transaccionasl con la bbdd
	@Transactional (readOnly = true)
	public List<Producto> findAll() {		
		return (List<Producto>) productoDao.findAll();
	}

	@Override
	@Transactional (readOnly = true)
	public Producto findById(Long Id) {		
		/**
		 * el metodo findById devuelve un Opitonal, a apartird de la JDK 1.8
		 * Esta clase nos permite realizar varias cosas entre ellas devolver otro
		 * objeto si no se encuentra ninguno.
		 */
		return productoDao.findById(Id).orElse(null);
	}

	@Override
	@Transactional
	public Producto save(Producto producto) {	
		return productoDao.save(producto);
	}

	@Override
	@Transactional
	public void deleteById(Long id) {		
		productoDao.deleteById(id);
	}

}
