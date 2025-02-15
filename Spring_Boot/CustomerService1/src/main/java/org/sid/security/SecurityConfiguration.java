package org.sid.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration {
	private final JwtAuthConverter jwtAuthConverter;
	public SecurityConfiguration(JwtAuthConverter jwtAuthConverter) {
		  this.jwtAuthConverter = jwtAuthConverter; }
    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
 
        http.csrf(csrf->csrf.disable());
        http.authorizeHttpRequests(auth->auth.anyRequest().authenticated());
        http.oauth2ResourceServer(ors -> {
        	ors.jwt(jwtConfigurer -> jwtConfigurer.jwtAuthenticationConverter(jwtAuthConverter));
        	//ors.jwt(Customizer.withDefaults());
        });
        http.sessionManagement(sm->sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();
    }
}