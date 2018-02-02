package nl.rabobank.training.orderservice.web;

import com.fasterxml.jackson.annotation.JsonView;
import nl.rabobank.training.orderservice.model.Bestelling;
import nl.rabobank.training.orderservice.model.OrderStatus;
import nl.rabobank.training.orderservice.persistence.OrderRepository;
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

	public OrderController(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
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
	public ResponseEntity createBestelling(@RequestBody @Valid Bestelling bestelling, UriComponentsBuilder builder) {
		Bestelling newBestelling = Bestelling.builder().items(bestelling.getItems()).status(OrderStatus.PENDING).build();
		Bestelling created = this.orderRepository.save(newBestelling);

		UriComponents uriComponents = builder.path("/api/v1/order/{id}").buildAndExpand(created.getId());

		return ResponseEntity.created(uriComponents.toUri()).build();
	}
}
