package com.carlos.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.carlos.service.Autenticacion;

@Configuration
@EnableWebSecurity(debug=false)
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private Autenticacion autenticacion;
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	        
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setPasswordEncoder(new BCryptPasswordEncoder(4));
		provider.setUserDetailsService(autenticacion);
		
		auth.authenticationProvider(provider);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
	   	http.authorizeRequests()
	   	.antMatchers("/genero/nuevoGenero").hasAuthority("ADMIN")
	   	.antMatchers("/autor/nuevoAutor").hasAuthority("ADMIN")
	   	.antMatchers("/autor/editar/**").hasAuthority("ADMIN")
	   	.antMatchers("/libro/nuevoLibro/**").hasAuthority("ADMIN")
	   	.antMatchers("/libro/editar/**").hasAuthority("ADMIN")
	   	.antMatchers("/genero/nuevoGenero/**").hasAuthority("ADMIN")
	   	.antMatchers("/estado/nuevoEstado/**").hasAuthority("ADMIN")
	   	.antMatchers("/seguir/dejarSeguir/**").authenticated()
		    .and()
	        .formLogin()
	        	.loginPage("/login")
	            .defaultSuccessUrl("/")
	            .failureUrl("/login-error")
	            .usernameParameter("username")
	            .passwordParameter("password")
	        .and()
	        .logout()
	        	.permitAll()
	            .logoutUrl("/logout")
	            .logoutSuccessUrl("/login")
	        .and()
	    .csrf().disable();
	 }
}
