 package com.formacionbdi.springboot.app.oauth.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter{
	
	private Logger log = LoggerFactory.getLogger(SpringSecurityConfig.class);
	
	@Autowired
	private UserDetailsService usuarioService;
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		log.info("[SpringSecurityConfig] INICIO passwordEncoder");
		return new BCryptPasswordEncoder();
	}

	@Override
	@Autowired //Es necesario este autowired?
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		log.info("[SpringSecurityConfig] INICIO configure AuthenticationManagerBuilder");
		auth.userDetailsService(usuarioService).passwordEncoder(passwordEncoder());
	}

	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		log.info("[SpringSecurityConfig] INICIO authenticationManager");
		return super.authenticationManager();
	}
	
	

}
