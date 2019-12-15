package com.formacionbdi.springboot.app.zuul.filters;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class PostTiempoTranscurridoFilter extends ZuulFilter{

	private static Logger log = LoggerFactory.getLogger(PostTiempoTranscurridoFilter.class);
	
	//Determina si se ejecuta o no el metodo run
	// false -> No se ejecuta true -> Si
	@Override
	public boolean shouldFilter() {
		return true;
	}

	//logia del filtro
	@Override
	public Object run() throws ZuulException {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request=ctx.getRequest();
		log.info("Entrando a post");
		
		Long tiempoInicio = (Long) request.getAttribute("tiempoInicio");
		Long tiempoTranscurrido = System.currentTimeMillis() - tiempoInicio;
		
		log.info(String.format("Tiempo transcurrido en segundos %s", tiempoTranscurrido.doubleValue()/1000.00));
		log.info(String.format("Tiempo transcurrido en milisegundos %s", tiempoTranscurrido.doubleValue()));
		return null;
	}

	/**
	 * Indica el tipo de filtro
	 * 
	 *  - pre -> antes de resolver la ruta
	 *  - rout -> durante la resolucion de la ruta
	 *  - post -> despues de resolver la ruta
	 */
	@Override
	public String filterType() {
		return "post";
	}
	
	//Orden
	@Override
	public int filterOrder() {
		return 1;
	}

}
