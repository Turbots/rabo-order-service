package nl.rabobank.training.orderservice.model;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;
import nl.rabobank.training.orderservice.web.OrderSummary;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Setter
@Getter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class OrderItem {

	@Id
	@GeneratedValue
	private Long id;

	@JsonView(OrderSummary.class)
	private String description;
	@JsonView(OrderSummary.class)
	private String ref;
	@JsonView(OrderSummary.class)
	private double price;
	@JsonView(OrderSummary.class)
	private int quantity;
}
