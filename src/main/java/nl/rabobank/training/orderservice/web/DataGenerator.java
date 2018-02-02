package nl.rabobank.training.orderservice.web;

import nl.rabobank.training.orderservice.model.Bestelling;
import nl.rabobank.training.orderservice.model.OrderItem;
import nl.rabobank.training.orderservice.model.OrderStatus;
import nl.rabobank.training.orderservice.persistence.OrderRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
@Profile("local")
public class DataGenerator implements CommandLineRunner {

	private final OrderRepository orderRepository;

	public DataGenerator(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}

	@Override public void run(String... strings) {
		this.orderRepository.save(Bestelling.builder()
			.items(Collections.singletonList(OrderItem.builder()
				.price(1.0)
				.quantity(1)
				.ref("BIER")
				.description("Biertje")
				.build()))
			.status(OrderStatus.PENDING)
			.build());
	}
}
