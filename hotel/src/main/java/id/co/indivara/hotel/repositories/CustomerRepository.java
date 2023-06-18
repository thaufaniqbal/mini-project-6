package id.co.indivara.hotel.repositories;

import id.co.indivara.hotel.model.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
