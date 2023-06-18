package id.co.indivara.hotel.repositories;

import id.co.indivara.hotel.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
