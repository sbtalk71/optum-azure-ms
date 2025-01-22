package com.demo.spring;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
//@EnableWebSecurity
@EnableWebFluxSecurity
public class SecurityConfig {

	@Bean
	public SecurityWebFilterChain  config(ServerHttpSecurity http) throws Exception {
		http.authorizeExchange(sec->sec.pathMatchers("/orders/**").hasRole("USER")
				.pathMatchers("/inventory/**").hasRole("ADMIN").anyExchange().permitAll());
		
		http.httpBasic(Customizer.withDefaults()).csrf(Customizer.withDefaults());
		return http.build();
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	@Bean
	public MapReactiveUserDetailsService  userDetailsService() throws Exception{
		
		UserDetails u1=(User.withUsername("shantanu").password(passwordEncoder().encode("welcome1")).roles("ADMIN").build()); 
		UserDetails u2=(User.withUsername("pavan").password(passwordEncoder().encode("welcome1")).roles("USER").build()); 
		UserDetails u3=(User.withUsername("ranga").password(passwordEncoder().encode("welcome1")).roles("USER").build()); 
		MapReactiveUserDetailsService manager= new MapReactiveUserDetailsService(Arrays.asList(u1,u2,u3));
		return manager;
	}
}
