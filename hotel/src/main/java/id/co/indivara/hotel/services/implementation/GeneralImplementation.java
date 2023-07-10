package id.co.indivara.hotel.services.implementation;

import id.co.indivara.hotel.model.dto.GetAllAvailableRoomForm;
import id.co.indivara.hotel.model.entity.Reservation;
import id.co.indivara.hotel.model.entity.Room;
import id.co.indivara.hotel.repositories.ReservationRepository;
import id.co.indivara.hotel.repositories.RoomRepository;
import id.co.indivara.hotel.services.GeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class GeneralImplementation implements GeneralService {
    @Autowired
    ReservationRepository reservationRepository;
    @Autowired
    RoomRepository roomRepository;
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
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }
}
