package id.co.indivara.hotel.service.implementation;

import id.co.indivara.hotel.model.GetAllAvailableForm;
import id.co.indivara.hotel.model.entity.Reservation;
import id.co.indivara.hotel.model.entity.Room;
import id.co.indivara.hotel.repositories.*;
import id.co.indivara.hotel.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class ReportServiceImplementation implements ReportService {
    @Autowired
    ReservationRepository reservationRepository;
    @Autowired
    RoomRepository roomRepository;
    @Override
    public List<Room> getAvailableRooms(GetAllAvailableForm getAllAvailableForm) {
        List<Room> availableRooms = roomRepository.findAll();
        List<Reservation> reservations = reservationRepository.findExistingReservationNative(
                getAllAvailableForm.getCheckInDate(),
                getAllAvailableForm.getCheckOutDate());
        for (Reservation reservation : reservations) {
                Room reservedRoom = reservation.getRoom();
                availableRooms.remove(reservedRoom);
            }
        return availableRooms;
    }

    @Override
    public Map<String, Integer> getTotalRoomsBooked() {
        return null;
    }
}
