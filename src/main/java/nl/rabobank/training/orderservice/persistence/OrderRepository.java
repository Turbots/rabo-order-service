package nl.rabobank.training.orderservice.persistence;

import nl.rabobank.training.orderservice.model.Bestelling;
import nl.rabobank.training.orderservice.model.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Bestelling, Long> {

	List<Bestelling> findALlByStatusEquals(OrderStatus status);
}
