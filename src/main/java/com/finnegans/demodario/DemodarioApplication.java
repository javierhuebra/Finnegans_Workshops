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
									"Notebook Lenovo",
									BigDecimal.valueOf(10000),
									null) //Inicio la relacion con orderDetail nula
					);
			OrderDetail orderDetail = orderDetailRepository
					.save(
							new OrderDetail(null, product.getPrice(), 4.0, product)
					);
			System.out.println(product);
		};

	}
}
