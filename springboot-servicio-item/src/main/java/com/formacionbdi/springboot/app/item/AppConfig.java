package com.formacionbdi.springboot.app.item;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * 
 * @author pedro
 * 
 * Clase para registrar beans en le contenedor de spring y poder injectarlos con @Autowired
 * 
 * Para ello necesitamos indicar que la calse es de configuracion con @Configuration e instanciar los beans
 * con metodos anotados com o @beans
 *
 */
@Configuration
public class AppConfig {
	
	@Bean("clienteRest")
	@LoadBalanced //Para configurar el balanceo de carga con Ribbon 
	public RestTemplate regiRestTemplate() {
		return new RestTemplate();
	}
	
	

}
