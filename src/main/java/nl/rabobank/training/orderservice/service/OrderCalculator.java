package nl.rabobank.training.orderservice.service;

import nl.rabobank.training.orderservice.model.Bestelling;
import nl.rabobank.training.orderservice.model.OrderItem;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Service
public class OrderCalculator {

	private final RestTemplate restTemplate;

	public OrderCalculator(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	public double calculateOrderPrice(Bestelling bestelling) {
		double totalPrice = 0.0;
		for (OrderItem item : bestelling.getItems()) {
			double itemPrice = getItemPrice(item.getRef());
			double orderItemPrice = itemPrice * item.getQuantity();
			totalPrice += orderItemPrice;
			item.setPrice(orderItemPrice);
		}
		return totalPrice;
	}

	private double getItemPrice(String ref) {
		URI uri = URI.create("https://rabo-menu-service.cfapps.io/api/v1/menu/price?ref=" + ref);

		return this.restTemplate.getForObject(uri, Double.class);
	}

}
