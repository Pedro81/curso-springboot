package com.formacionbdi.springboot.app.zuul.filters;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class PreTiempoTranscurridoFilter extends ZuulFilter{

	private static Logger log = LoggerFactory.getLogger(PreTiempoTranscurridoFilter.class);
	
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
		
		Long tiempoInicio = System.currentTimeMillis();
		log.info(String.format("%s request enrutado a %s", request.getMethod(), request.getRequestURL().toString()));
		request.setAttribute("tiempoInicio", tiempoInicio);
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
		return "pre";
	}
	
	//Orden
	@Override
	public int filterOrder() {
		return 1;
	}

}
