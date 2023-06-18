package id.co.indivara.hotel.repositories;

import id.co.indivara.hotel.model.entity.Reservation;
import id.co.indivara.hotel.model.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Query(value = "SELECT * FROM trx_reservations WHERE room_id = :room " +
               "AND ((:checkInDate >= check_in AND :checkInDate <= check_out) "+
               "OR (:checkOutDate >= check_in AND :checkOutDate <= check_out))", nativeQuery = true)
    Optional<Reservation> findExistingReservationNative(
            @Param("room") Room room,
            @Param("checkInDate") LocalDate checkInDate,
            @Param("checkOutDate") LocalDate checkOutDate
    );

    Reservation findByRoomTokenAndIsCheckInFalse(Integer roomToken);

}
