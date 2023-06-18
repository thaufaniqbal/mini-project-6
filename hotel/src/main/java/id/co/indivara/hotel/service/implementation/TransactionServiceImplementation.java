package id.co.indivara.hotel.service.implementation;

import id.co.indivara.hotel.model.CustomerValidationCheckIn;
import id.co.indivara.hotel.model.ReserveRoomForm;
import id.co.indivara.hotel.model.ReserveRoomReceipt;
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
    public CustomerValidationCheckIn checkIn(Long roomNumber, Integer roomToken) {
        Reservation reservation = reservationRepository.findByRoomTokenAndIsCheckInFalseAndValidationCheckInFalse(roomToken);
        if (dateCheckInChecker(reservation.getCheckIn()) && roomNumber.equals(reservation.getRoom().getRoomNumber())) {
            reservation.setValidationCheckIn(true);
            reservationRepository.save(reservation);
            return new CustomerValidationCheckIn(reservation.getCustomer().getCustomerName(), "Check Notification");
        }
        return new CustomerValidationCheckIn(null, "Invalid check-in request");
    }

    @Override
    public Boolean customerValidationCheckIn(Long customerId, Long roomNumber, Integer roomToken, Boolean isCheckIn) {
        Reservation reservation = reservationRepository.findByRoomTokenAndIsCheckInFalseAndValidationCheckInTrue(roomToken);
        if (isCheckIn && customerId.equals(reservation.getCustomer().getId())) {
            transactionRepository.save(Transaction.builder()
                    .checkIn(LocalDate.now())
                    .room(reservation.getRoom())
                    .customer(reservation.getCustomer())
                    .build());
            reservation.setIsCheckIn(true);
            reservationRepository.save(reservation);
            return true;
        }
        return false;
    }

    @Override
    public void checkOut(Long roomId) {
        // Implement the check-out functionality
    }

    @Override
    public ReserveRoomReceipt reserveRoom(ReserveRoomForm reserveRoomForm) {
        Room room = roomRepository.findById(reserveRoomForm.getRoomId())
                .orElseThrow(() -> new RuntimeException("Room not found"));
        Customer customer = customerRepository.findById(reserveRoomForm.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        Optional<Reservation> existingReservation = reservationRepository.findExistingReservationNative(
                room, reserveRoomForm.getCheckInDate(), reserveRoomForm.getCheckOutDate());

        if (existingReservation.isPresent()) {
            return ReserveRoomReceipt.builder()
                    .roomNumber(room.getRoomNumber())
                    .roomToken(null)
                    .systemMessage("Reservation Room: " + room.getRoomNumber() + ", Already exists for the specified dates: " +
                            reserveRoomForm.getCheckInDate() +
                            " - " + reserveRoomForm.getCheckOutDate())
                    .build();
        }

        int token = generateRoomToken(reserveRoomForm.getCheckInDate(), room.getRoomNumber());
        Reservation reservation = createReservation(room, customer, reserveRoomForm.getCheckInDate(), reserveRoomForm.getCheckOutDate(), token);
        reservationRepository.save(reservation);

        return ReserveRoomReceipt.builder()
                .roomNumber(room.getRoomNumber())
                .roomToken(token)
                .systemMessage("to: " + reservation.getCustomer().getCustomerName() +
                        " Please keep this token secure, and perform check-in on the date specified: " +
                        reservation.getCheckIn())
                .build();
    }

    private Boolean dateCheckInChecker(LocalDate dateCheckIn) {
        return dateCheckIn.equals(LocalDate.now());
    }

    private int generateRoomToken(LocalDate checkInDate, Long roomNumber) {
        int date = checkInDate.getDayOfMonth();
        return Integer.parseInt(date + String.valueOf(roomNumber) + new Random().nextInt(50));
    }

    private Reservation createReservation(Room room, Customer customer, LocalDate checkInDate,
                                          LocalDate checkOutDate, int roomToken) {
        return Reservation.builder()
                .room(room)
                .reservationDate(LocalDateTime.now())
                .checkIn(checkInDate)
                .checkOut(checkOutDate)
                .customer(customer)
                .roomToken(roomToken)
                .validationCheckIn(false)
                .isCheckIn(false)
                .build();
    }
}