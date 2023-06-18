package id.co.indivara.hotel.service.implementation;

import id.co.indivara.hotel.model.ReserveRoomForm;
import id.co.indivara.hotel.model.entity.Customer;
import id.co.indivara.hotel.model.entity.Reservation;
import id.co.indivara.hotel.model.entity.Room;
import id.co.indivara.hotel.model.entity.Transaction;
import id.co.indivara.hotel.repositories.CustomerRepository;
import id.co.indivara.hotel.repositories.ReservationRepository;
import id.co.indivara.hotel.repositories.RoomRepository;
import id.co.indivara.hotel.repositories.TransactionRepository;
import id.co.indivara.hotel.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;

@Service
public class TransactionServiceImplementation implements TransactionService {

    @Autowired
    ReservationRepository reservationRepository;
    @Autowired
    RoomRepository roomRepository;
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    TransactionRepository transactionRepository;


    @Override
    public Boolean checkIn(Long roomNumber, Integer roomToken) {
        Reservation reservation = reservationRepository.findByRoomTokenAndIsCheckInFalse(roomToken);
        if (dateCheckInChecker(reservation.getCheckIn())){
            if(roomNumber==reservation.getRoom().getRoomNumber()){
                transactionRepository.save(Transaction.builder()
                        .checkIn(LocalDate.now()).
                        room(reservation.getRoom()).
                        customer(reservation.getCustomer()).
                        build());
                reservation.setIsCheckIn(true);
                reservationRepository.save(reservation);
                return true;
            }
        }
        return false;
    }

    @Override
    public void checkOut(Long roomId) {

    }

    @Override
    public Boolean reserveRoom(ReserveRoomForm reserveRoomForm) {
        Room room = roomRepository.findById(reserveRoomForm.getRoomId())
                .orElseThrow(() -> new RuntimeException("Room not found"));
        Customer customer = customerRepository.findById(reserveRoomForm.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        int date = reserveRoomForm.getCheckInDate().getDayOfMonth();
        Long roomNumber = room.getRoomNumber();
        int token = Integer.parseInt(date + String.valueOf(roomNumber) + new Random().nextInt(50));
        Optional<Reservation> existingReservation =
                reservationRepository.findExistingReservationNative(
                        room, reserveRoomForm.getCheckInDate(),
                        reserveRoomForm.getCheckOutDate());

        Reservation reservation = existingReservation.orElseGet(() -> {
            Reservation newReservation = Reservation.builder()
                    .room(room)
                    .reservationDate(LocalDateTime.now())
                    .checkIn(reserveRoomForm.getCheckInDate())
                    .checkOut(reserveRoomForm.getCheckOutDate())
                    .customer(customer)
                    .roomToken(token)
                    .isCheckIn(false)
                    .build();
            return reservationRepository.save(newReservation);
        });

        return !existingReservation.isPresent();
    }

    public static Boolean dateCheckInChecker(LocalDate dateCheckIn){
        return Objects.equals(dateCheckIn, LocalDate.now());
    }

}
