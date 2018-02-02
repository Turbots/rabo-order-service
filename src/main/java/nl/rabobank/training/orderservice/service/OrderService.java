package nl.rabobank.training.orderservice.service;

import nl.rabobank.training.orderservice.model.Bestelling;
import nl.rabobank.training.orderservice.persistence.OrderRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

	private final OrderCalculator orderCalculator;
	private final OrderRepository orderRepository;

	public OrderService(OrderCalculator orderCalculator,
		OrderRepository orderRepository) {
		this.orderCalculator = orderCalculator;
		this.orderRepository = orderRepository;
	}

	@Async
	public void updateTotalPrice(Bestelling bestelling) {
		double totalPrice = this.orderCalculator.calculateOrderPrice(bestelling);
		this.orderRepository.updateTotalPrice(bestelling.getId(), totalPrice);
	}
}
