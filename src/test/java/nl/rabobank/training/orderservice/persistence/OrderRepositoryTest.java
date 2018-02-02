package nl.rabobank.training.orderservice.persistence;

import nl.rabobank.training.orderservice.model.Bestelling;
import nl.rabobank.training.orderservice.model.OrderItem;
import nl.rabobank.training.orderservice.model.OrderStatus;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;
import java.util.Collections;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class OrderRepositoryTest {

	@Autowired
	private OrderRepository orderRepository;

	@Before
	public void init() {
		this.orderRepository.save(Bestelling.builder()
			.items(Collections.singletonList(OrderItem.builder()
				.quantity(1)
				.description("Biertje")
				.ref("BIER")
				.build()))
			.status(OrderStatus.PENDING)
			.build());
	}

	@Test
	@Transactional
	public void updatePrice() {
		this.orderRepository.updateTotalPrice(1L, 10.0);

		Bestelling bestelling = this.orderRepository.findOne(1L);
		assertEquals(10.0, bestelling.getTotalPrice(), 0.0);
	}
}
