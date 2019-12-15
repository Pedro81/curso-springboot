package com.formacionbdi.springboot.app.item.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.formacionbdi.springboot.app.commons.models.entity.Producto;
import com.formacionbdi.springboot.app.item.clientes.IProductoClienteRest;
import com.formacionbdi.springboot.app.item.models.Item;


@Service("serviceFeing")
// Con la anotacion @Primary podiramos indicar a spring que escogiese este servicio en lugar del otro,
public class ItemServiceFeing implements InterfaceItemService {
	
	@Autowired
	private IProductoClienteRest clientFeing;

	@Override
	public List<Item> findAll() {
		System.out.println("serviceFeing");
		return clientFeing.listar().stream().map(p-> new Item(p, 1)).collect(Collectors.toList());
	}

	@Override
	public Item findById(Long id, Integer cantidad) {
		System.out.println("serviceFeing");
		return new Item(clientFeing.detalle(id), cantidad);
	}

	@Override
	public Producto save(Producto producto) {
		System.out.println("serviceFeing");
		return clientFeing.save(producto);
	}

	@Override
	public Producto update(Producto producto, Long id) {
		System.out.println("serviceFeing");
		return clientFeing.update(producto, id);
	}

	@Override
	public void delete(Long id) {
		System.out.println("serviceFeing");
		clientFeing.delete(id);
	}


}
