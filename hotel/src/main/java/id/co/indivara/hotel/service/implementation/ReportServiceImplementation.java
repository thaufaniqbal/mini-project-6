package id.co.indivara.hotel.service.implementation;

import id.co.indivara.hotel.model.GetAllAvailableRoomForm;
import id.co.indivara.hotel.model.entity.Reservation;
import id.co.indivara.hotel.model.entity.Room;
import id.co.indivara.hotel.model.entity.Transaction;
import id.co.indivara.hotel.repositories.*;
import id.co.indivara.hotel.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImplementation implements ReportService {
    @Autowired
    ReservationRepository reservationRepository;
    @Autowired
    RoomRepository roomRepository;
    @Autowired
    TransactionRepository transactionRepository;
    @Override
    public List<Room> getAvailableRooms(GetAllAvailableRoomForm getAllAvailableRoomForm) {
        LocalDate checkInDate = getAllAvailableRoomForm.getCheckInDate();
        LocalDate checkOutDate = getAllAvailableRoomForm.getCheckOutDate();

        if (checkInDate == null && checkOutDate == null) {
            checkInDate = LocalDate.now();
            checkOutDate = LocalDate.now();
        }

        List<Room> availableRooms = roomRepository.findAll();
        List<Reservation> reservations = reservationRepository.findExistingReservationNative(checkInDate, checkOutDate);

        for (Reservation reservation : reservations) {
            Room reservedRoom = reservation.getRoom();
            availableRooms.remove(reservedRoom);
        }

        return availableRooms;
    }

    @Override
    public Map<String, Integer> getHotelReport() {
        List<Transaction> transactions = transactionRepository.findAll();
        Map<String, Integer> roomsBookedMap = new HashMap<>();

        for (Transaction transaction : transactions) {
            Room room = transaction.getRoom();
            String roomType = room.getRoomType();
            int roomsBooked = roomsBookedMap.getOrDefault(roomType, 0);
            roomsBookedMap.put(roomType, roomsBooked + 1);
        }

        return roomsBookedMap;
    }
}
