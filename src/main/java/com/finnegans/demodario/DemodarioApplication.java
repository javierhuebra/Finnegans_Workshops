package com.finnegans.demodario;

import com.finnegans.demodario.model.Customer;
import com.finnegans.demodario.model.Order;
import com.finnegans.demodario.model.OrderDetail;
import com.finnegans.demodario.model.Product;
import com.finnegans.demodario.repository.CustomerRepository;
import com.finnegans.demodario.repository.OrderDetailRepository;
import com.finnegans.demodario.repository.OrderRepository;
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
import java.util.Date;
import java.util.Set;

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
			OrderDetailRepository orderDetailRepository,
			OrderRepository orderRepository,
			CustomerRepository customerRepository
	){
		return args -> {
			//Creo dos productos
			Product product = productRepository
					.save(
							new Product(null,
									"Notebook MSI",
									BigDecimal.valueOf(20000)) //Inicio la relacion con orderDetail nula
					);
			Product product2 = productRepository
					.save(
							new Product(null,
									"Router TP-Link",
									BigDecimal.valueOf(1000)) //Inicio la relacion con orderDetail nula
					);

			//Creo un customer
			Customer customer = customerRepository
					.save(
							new Customer(
									null,
									"Juan",
									"558"
							)
					);

			//Crea una orden
			Order order = orderRepository
					.save(
							new Order(
									null,
									"Pedido descripto de onda",
									new Date(),
									customer,
									null
							)
					);

			//Creo dos detalles de compra orderdetail
			OrderDetail orderDetail = orderDetailRepository
					.save(
							new OrderDetail(
									null,
									product.getPrice(),
									4.0,
									product,
									null
							)
					);
			OrderDetail orderDetail2 = orderDetailRepository
					.save(
							new OrderDetail(
									null,
									product2.getPrice(),
									1.0,
									product2,
									null
							)
					);

			//Se agrega el detalle de compra a la orden
			//Esto es porque es un Set Set<OrderDetail> orderDetails = Set.of(orderDetail, orderDetail2);
			order.setOrderDetails(
					Set.of(orderDetail, orderDetail2)
			);



			//Ahora hago una asignación individual, ya no por arreglo aunque eso se solucionaria con lo que puse anteriormente

			orderRepository.save(order);
			System.out.println("Ordenes detalle" + order.getOrderDetails());//Aca se ve que order tiene dos orderDetails sin embargo cada orderDetail tiene una order null

			//Voy a ver que order tiene el primer OrderDetail
			System.out.println("Order del primer orderDetail: " + orderDetail.getOrder()); //Me tira null, la tabla horrible no me genera la relación automatica, tendria que empezar relacionando la orden a orderDetail y no al reves
		};
	/*
		CommandLineRunner es una interfaz en Spring Boot que se utiliza para ejecutar código una vez que la aplicación
		Spring Boot se ha iniciado por completo. Esta interfaz define un método run(String... args) que debe
		implementarse en una clase concreta. El método run se ejecutará automáticamente después de que Spring Boot haya
		configurado todas las partes de la aplicación y esté lista para funcionar.
	*/
	}
}
