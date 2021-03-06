package com.formacionbdi.springboot.app.oauth.security;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@RefreshScope
@Configuration
@EnableAuthorizationServer
public class AuthorizationServer extends AuthorizationServerConfigurerAdapter {
	
	private Logger log = LoggerFactory.getLogger(AuthorizationServer.class);
	
	@Autowired
	private Environment env;
	
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private InfoAdicionalToken infoAditionalToken;

	/**
	 * Configuracion de los permisos que van a tener los endpoints del servidor de autorizacion
	 */
	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		log.info("[AuthorizationServer] INICIO configure AuthorizationServerSecurityConfigurer");
		security.tokenKeyAccess("permitAll()") // Cualquiera puede acceder al endpoint para generar el token
		.checkTokenAccess("isAuthenticated()"); // Solo pueden acceder al endpoint para  validar el token aquellos que esten autenticados.		
	}

	/**
	 * Registro de los diferentes clientes (aplicaciones) que consumen los servicios.
	 * 
	 * Hay 2 tipos de autenticacion:
	 * 1- La de la aplicacion cliente
	 * 2- La de usuario que inicia la sesion
	 * 
	 * Hay que registrar tantos clienetes como aplicaciones.
	 * 
	 */
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		log.info("[AuthorizationServer] INICIO configure ClientDetailsServiceConfigurer");
		clients.inMemory().withClient(env.getProperty("config.security.oauth.client.id"))
		.secret(passwordEncoder.encode(env.getProperty("config.security.oauth.client.secret")))
		.scopes("read", "write")
		.authorizedGrantTypes("password", "refresh_token")// password -> Indica como los usuarios van a obtener el token refresh_token -> perimite tener un nuevo token antes de que caduque el actual
		.accessTokenValiditySeconds(3600)
		.refreshTokenValiditySeconds(3600);
		/*.and() // Para registrar otro cliente
		.withClient("adroidapp")
		.secret(passwordEncoder.encode("1234"))
		.scopes("read", "write")
		.authorizedGrantTypes("password", "refresh_token")
		.refreshTokenValiditySeconds(3600);*/
	}

	/**
	 * Configura el endpoint del servidor de autorizacion encargado de quenerar el token
	 *  - /oauth/token
	 */
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		log.info("[AuthorizationServer] INICIO configure AuthorizationServerEndpointsConfigurer");
		
		TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
		tokenEnhancerChain.setTokenEnhancers(Arrays.asList(infoAditionalToken,accessTokenConverter()));
		
		endpoints.authenticationManager(authenticationManager)
		.tokenStore(tokenStore())
		.accessTokenConverter(accessTokenConverter())
		.tokenEnhancer(tokenEnhancerChain);
	}

	/**
	 * JwtTokenStore se encarga de generar, guardar el token con los datos del JwtAccessTokenConverter
	 */
	@Bean
	public JwtTokenStore tokenStore() {
		return new JwtTokenStore(accessTokenConverter());
	}
	
	/**
	 * 
	 * JwtAccessTokenConverter se encarga de convertir el token en jwt con toda la informacion
	 */
	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter tokenConverter = new JwtAccessTokenConverter();
		tokenConverter.setSigningKey(env.getProperty("config.security.oauth.jwt.key"));
		return tokenConverter;
	}
	
	

}
