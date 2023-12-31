package id.co.indivara.hotel.services.implementation;

import id.co.indivara.hotel.model.dto.CustomerCheckInMessage;
import id.co.indivara.hotel.model.dto.CustomerCheckInValidationForm;
import id.co.indivara.hotel.model.dto.ReserveRoomForm;
import id.co.indivara.hotel.model.dto.ReserveRoomReceipt;
import id.co.indivara.hotel.model.entity.*;
import id.co.indivara.hotel.repositories.*;
import id.co.indivara.hotel.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
@Service
public class CustomerServiceImplementation implements CustomerService {

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
    public CustomerCheckInMessage checkIn(Long roomNumber, Integer roomToken) {
        // find reservation by roomToken, isCheckIn = False, CheckInValidation = False
        Reservation reservation = reservationRepository.findByRoomTokenAndIsCheckInFalseAndCheckInValidationFalse(roomToken);
        // check date, if customer do checkIn, and is not match will return invalid checkIn request
        if (Boolean.TRUE.equals(dateCheckInChecker(reservation.getCheckIn())) && roomNumber.equals(reservation.getRoom().getRoomNumber())) {
            reservation.setCheckInValidation(true);
            reservationRepository.save(reservation);
            return new CustomerCheckInMessage(reservation.getCustomer().getCustomerName(), "Check Notification");
        }
        return new CustomerCheckInMessage(null, "Invalid check-in request");
    }

    @Override
    public Boolean customerCheckInValidation(CustomerCheckInValidationForm customerCheckInValidationForm) {
        // find reservation by roomToken, isCheckIn = False, CheckInValidation = True
        Reservation reservation = reservationRepository.findByRoomTokenAndIsCheckInFalseAndCheckInValidationTrue(customerCheckInValidationForm.getRoomToken());
        // check for customerCheckIn, if customer set "True" to customerCheckInValidationForm, and it will true. and save to transaction history
        if (Boolean.TRUE.equals(customerCheckInValidationForm.getIsCheckIn()) && reservation.getRoom().equals(roomRepository.findByRoomNumber(customerCheckInValidationForm.getRoomNumber()))) {
            transactionRepository.save(Transaction.builder()
                    .checkIn(LocalDateTime.now())
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
        return Integer.parseInt(date + String.valueOf(roomNumber) + 123);
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