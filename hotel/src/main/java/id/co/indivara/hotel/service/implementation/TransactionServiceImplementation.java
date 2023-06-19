package id.co.indivara.hotel.service.implementation;

import id.co.indivara.hotel.model.CustomerValidationCheckIn;
import id.co.indivara.hotel.model.ReserveRoomForm;
import id.co.indivara.hotel.model.ReserveRoomReceipt;
import id.co.indivara.hotel.model.Validation;
import id.co.indivara.hotel.model.entity.*;
import id.co.indivara.hotel.repositories.*;
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
    @Autowired
    RoomStatusRepository roomStatusRepository;


    @Override
    public CustomerValidationCheckIn checkIn(Long roomNumber, Integer roomToken) {
        Reservation reservation = reservationRepository.findByRoomTokenAndIsCheckInFalseAndCheckInValidationFalse(roomToken);
        if (dateCheckInChecker(reservation.getCheckIn()) && roomNumber.equals(reservation.getRoom().getRoomNumber())) {
            reservation.setCheckInValidation(true);
            reservationRepository.save(reservation);
            return new CustomerValidationCheckIn(reservation.getCustomer().getCustomerName(), "Check Notification");
        }
        return new CustomerValidationCheckIn(null, "Invalid check-in request");
    }

    @Override
    public Boolean customerValidationCheckIn(Validation validation) {
        Reservation reservation = reservationRepository.findByRoomTokenAndIsCheckInFalseAndCheckInValidationTrue(validation.getRoomToken());
        if (validation.getIsCheckIn() && validation.getCustomerId().equals(reservation.getCustomer().getId())) {
            transactionRepository.save(Transaction.builder()
                    .checkIn(LocalDateTime.now())
                    .room(reservation.getRoom())
                    .customer(reservation.getCustomer())
                    .reservation(reservation)
                    .build());
            reservation.setIsCheckIn(true);
            reservationRepository.save(reservation);
            RoomStatus roomStatus = reservation.getRoom().getRoomStatus();
            roomStatus.setRoomStatus(false);
            roomStatusRepository.save(roomStatus);
            return true;
        }
        return false;
    }

    @Override
    public Boolean checkOut(Integer roomToken) {
        Reservation reservation = reservationRepository.findByRoomTokenAndIsCheckInTrueAndCheckInValidationTrue(roomToken);
        Transaction transaction = transactionRepository.findByReservation(reservation);
        transaction.setCheckOut(LocalDateTime.now());
        transaction.setIsCheckOut(true);
        transactionRepository.save(transaction);
        RoomStatus roomStatus = reservation.getRoom().getRoomStatus();
        roomStatus.setRoomStatus(true);
        roomStatusRepository.save(roomStatus);
        return true;
    }
    @Override
    public ReserveRoomReceipt reserveRoom(ReserveRoomForm reserveRoomForm) {
        LocalDate checkInDate = reserveRoomForm.getCheckInDate();

        Room room = findRoomById(reserveRoomForm.getRoomId());
        Customer customer = findCustomerById(reserveRoomForm.getCustomerId());

        Optional<Reservation> existingReservation = findExistingReservation(room, checkInDate, reserveRoomForm.getCheckOutDate());
        if (existingReservation.isPresent()||!isValidCheckInDate(checkInDate)) {
            return createInvalidReservationReceipt(room.getRoomNumber(), "Cannot reserve for the specified dates: " +
                    checkInDate + " - " + reserveRoomForm.getCheckOutDate());
        }

        int token = generateRoomToken(checkInDate, room.getRoomNumber());
        Reservation reservation = createReservation(room, customer, checkInDate, reserveRoomForm.getCheckOutDate(), token);
        reservationRepository.save(reservation);

        return createReservationReceipt(reservation.getRoom().getRoomNumber(), token, reservation.getCustomer().getCustomerName(), reservation.getCheckIn());
    }

    private Boolean dateCheckInChecker(LocalDate dateCheckIn) {
        return dateCheckIn.equals(LocalDate.now());
    }

    private boolean isValidCheckInDate(LocalDate checkInDate) {
        return checkInDate.isAfter(LocalDate.now().minusDays(1));
    }

    private ReserveRoomReceipt createInvalidReservationReceipt(Long roomNumber, String message) {
        return ReserveRoomReceipt.builder()
                .roomNumber(roomNumber)
                .roomToken(null)
                .systemMessage(message)
                .build();
    }

    private Room findRoomById(Long roomId) {
        return roomRepository.findById(roomId)
                .orElseThrow(() -> new RuntimeException("Room not found"));
    }

    private Customer findCustomerById(Long customerId) {
        return customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
    }

    private Optional<Reservation> findExistingReservation(Room room, LocalDate checkInDate, LocalDate checkOutDate) {
        return reservationRepository.findExistingReservationNative(room, checkInDate, checkOutDate);
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
                .checkInValidation(false)
                .isCheckIn(false)
                .build();
    }

    private ReserveRoomReceipt createReservationReceipt(Long roomNumber, int roomToken, String customerName, LocalDate checkInDate) {
        return ReserveRoomReceipt.builder()
                .roomNumber(roomNumber)
                .roomToken(roomToken)
                .systemMessage("Dear: " + customerName +
                        " Please keep this token secure, and perform check-in on the date specified: " +
                        checkInDate)
                .build();
    }
}