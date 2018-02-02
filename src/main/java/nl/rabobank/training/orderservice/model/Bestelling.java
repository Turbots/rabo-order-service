package nl.rabobank.training.orderservice.model;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import nl.rabobank.training.orderservice.web.OrderSummary;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Bestelling {

	@Id
	@GeneratedValue
	private Long id;

	@Enumerated(EnumType.STRING)
	@JsonView(OrderSummary.class)
	private OrderStatus status;

	@OneToMany(cascade = CascadeType.ALL)
	@JsonView(OrderSummary.class)
	@NotEmpty
	private List<OrderItem> items;

	@JsonView(OrderSummary.class)
	private double totalPrice;
}
