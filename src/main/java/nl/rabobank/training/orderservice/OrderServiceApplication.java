package nl.rabobank.training.orderservice;

import nl.rabobank.training.orderservice.model.Bestelling;
import nl.rabobank.training.orderservice.model.OrderItem;
import nl.rabobank.training.orderservice.model.OrderStatus;
import nl.rabobank.training.orderservice.persistence.OrderRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collections;

@SpringBootApplication
public class OrderServiceApplication implements CommandLineRunner {

	public OrderServiceApplication(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(OrderServiceApplication.class, args);
	}

	private final OrderRepository orderRepository;

	@Override public void run(String... strings) {

	}
}
