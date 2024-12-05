package org.sid;

import java.security.Principal;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties.Jwt;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.config.Projection;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.util.Converter;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@SpringBootApplication
@EnableConfigurationProperties(ConfigParams.class)
public class CustomerService1Application {

	public static void main(String[] args) {
		SpringApplication.run(CustomerService1Application.class, args);
	}

	@Bean
	CommandLineRunner start(CustomerRepository customerRepository) {
		return args->{
			customerRepository.save(new Customer(null,"eni","contact@eni.tn")); 
			customerRepository.save(new Customer(null,"FST","contact@fst.tn")); 
			customerRepository.save(new Customer(null,"ENSI","contact@ensi.tn")); 
			customerRepository.findAll().forEach(System.out::println);


		};
	}
	
	
}
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
 class Customer {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String email;
	
}

@RepositoryRestResource
interface CustomerRepository extends JpaRepository<Customer,Long> {
}

@Projection(name = "fullCustomer",types = Customer.class)
interface CustomerProjection extends Projection{

	public Long getId();
	public String getName();
	public String getEmail();
}



@RestController
@RefreshScope
 class CustomerRestController {
 
	 @GetMapping("/auth") 
	 public Principal authentication(Principal principal){
	 return principal; }
}










