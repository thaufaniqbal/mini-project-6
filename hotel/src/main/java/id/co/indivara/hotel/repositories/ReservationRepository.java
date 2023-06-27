package id.co.indivara.hotel.repositories;

import id.co.indivara.hotel.model.entity.Reservation;
import id.co.indivara.hotel.model.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Query(value = "SELECT * FROM trx_reservations WHERE room_id = :room " +
               "AND ((:checkInDatee >= check_in AND :checkInDatee <= check_out) "+
               "OR (:checkOutDate >= check_in AND :checkOutDate <= check_out))", nativeQuery = true)
    Optional<Reservation> findExistingReservationNative(
            @Param("room") Room room,
            @Param("checkInDatee") LocalDate checkInDate,
            @Param("checkOutDate") LocalDate checkOutDate
    );

    @Query(value = "SELECT * FROM trx_reservations WHERE ((:checkInDatee >= check_in AND :checkInDatee <= check_out)"+
            "OR (:checkOutDate >= check_in AND :checkOutDate <= check_out))", nativeQuery = true)
    List<Reservation> findExistingReservationNative(
            @Param("checkInDatee") LocalDate checkInDate,
            @Param("checkOutDate") LocalDate checkOutDate
    );

    Reservation findByRoomTokenAndIsCheckInFalseAndCheckInValidationFalse(Integer roomToken);
    Reservation findByRoomTokenAndIsCheckInFalseAndCheckInValidationTrue(Integer roomToken);
    Reservation findByRoomTokenAndIsCheckInTrueAndCheckInValidationTrue(Integer roomToken);

}
