package nl.rabobank.training.orderservice.web;

import com.fasterxml.jackson.annotation.JsonView;
import nl.rabobank.training.orderservice.model.Bestelling;
import nl.rabobank.training.orderservice.model.OrderItem;
import nl.rabobank.training.orderservice.model.OrderStatus;
import nl.rabobank.training.orderservice.persistence.OrderRepository;
import nl.rabobank.training.orderservice.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {

	private final OrderRepository orderRepository;
	private final OrderService orderService;

	public OrderController(OrderRepository orderRepository, OrderService orderService) {
		this.orderRepository = orderRepository;
		this.orderService = orderService;
	}

	@GetMapping
	@JsonView(OrderSummary.class)
	public List<Bestelling> getOrders() {
		return this.orderRepository.findAll();
	}

	@GetMapping("/{id}")
	public Bestelling getOrder(@PathVariable("id") Long id) {
		return this.orderRepository.findOne(id);
	}

	@PostMapping
	public ResponseEntity createBestelling(@RequestBody @Valid List<OrderItem> orderItems,
		UriComponentsBuilder builder) {
		Bestelling nieuweBestelling = Bestelling.builder().items(orderItems).status(OrderStatus.PENDING).build();
		Bestelling created = this.orderRepository.save(nieuweBestelling);

		UriComponents uriComponents = builder.path("/api/v1/order/{id}").buildAndExpand(created.getId());

		this.orderService.updateTotalPrice(created);

		return ResponseEntity.created(uriComponents.toUri()).build();
	}
}
