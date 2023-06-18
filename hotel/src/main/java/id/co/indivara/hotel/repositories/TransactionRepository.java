package id.co.indivara.hotel.repositories;

import id.co.indivara.hotel.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
