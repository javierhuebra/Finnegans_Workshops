package com.finnegans.demodario;

import com.finnegans.demodario.model.OrderDetail;
import com.finnegans.demodario.model.Product;
import com.finnegans.demodario.repository.OrderDetailRepository;
import com.finnegans.demodario.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.math.BigDecimal;
import java.util.Arrays;

@SpringBootApplication
public class DemodarioApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemodarioApplication.class, args);
	}

	/*@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration(); // Allow anyone and anything access.
		config.setAllowedOriginPatterns(Arrays.asList("*"));
		config.addAllowedHeader("*");
		config.addAllowedMethod("OPTIONS");
		config.addAllowedMethod("GET");
		config.addAllowedMethod("POST");
		config.addAllowedMethod("PUT");
		config.addAllowedMethod("DELETE");
		source.registerCorsConfiguration("/api/**", config);
		return new CorsFilter(source);
	}*/
	@Bean
	CommandLineRunner commandLineRunner(
			ProductRepository productRepository,
			OrderDetailRepository orderDetailRepository
	){
		return args -> {
			Product product = productRepository
					.save(
							new Product(null,
									"Notebook MSI",
									BigDecimal.valueOf(20000)) //Inicio la relacion con orderDetail nula
					);
			OrderDetail orderDetail = orderDetailRepository
					.save(
							new OrderDetail(null, product.getPrice(), 4.0, product)
					);
			System.out.println(product);
		};
	/*
		CommandLineRunner es una interfaz en Spring Boot que se utiliza para ejecutar código una vez que la aplicación
		Spring Boot se ha iniciado por completo. Esta interfaz define un método run(String... args) que debe
		implementarse en una clase concreta. El método run se ejecutará automáticamente después de que Spring Boot haya
		configurado todas las partes de la aplicación y esté lista para funcionar.
	*/
	}
}
