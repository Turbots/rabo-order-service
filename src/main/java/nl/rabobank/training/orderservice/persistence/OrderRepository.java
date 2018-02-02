package nl.rabobank.training.orderservice.persistence;

import nl.rabobank.training.orderservice.model.Bestelling;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Bestelling, Long> {

	@Query("update Bestelling b set totalPrice = :totalPrice where b.id = :id")
	void updateTotalPrice(@Param("id") Long id, @Param("totalPrice") double totalPrice);
}
