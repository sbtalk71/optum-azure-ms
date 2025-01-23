package com.demo.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain config(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(sec->sec.requestMatchers("/orders/**").hasRole("USER")
				.requestMatchers("/inventory/**").hasRole("ADMIN").anyRequest().permitAll());
		
		http.httpBasic(Customizer.withDefaults()).csrf(Customizer.withDefaults());
		return http.build();
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	@Bean
	public UserDetailsService userDetailsService() throws Exception{
		InMemoryUserDetailsManager manager= new InMemoryUserDetailsManager();
		manager.createUser(User.withUsername("shantanu").password(passwordEncoder().encode("welcome1")).roles("ADMIN").build()); 
		manager.createUser(User.withUsername("pavan").password(passwordEncoder().encode("welcome1")).roles("USER").build()); 
		manager.createUser(User.withUsername("ranga").password(passwordEncoder().encode("welcome1")).roles("USER").build()); 
		return manager;
	}
}
