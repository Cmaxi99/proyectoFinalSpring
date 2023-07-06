package com.example.carrito;

import com.example.carrito.persistence.entities.Carrito;
import com.example.carrito.persistence.entities.DetalleCarrito;
import com.example.carrito.persistence.repository.CarritoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;

@SpringBootApplication
public class CarritoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarritoApplication.class, args);
	}
	@Bean
	CommandLineRunner commandLineRunner(
			CarritoRepository carritoRepository
	)
	{
		return args -> {
			Carrito carrito = new Carrito(1, new ArrayList<>());
			carritoRepository.save(carrito);
		};
	}
}
