package id.co.indivara.hotel.services;

import id.co.indivara.hotel.model.GetAllAvailableRoomForm;
import id.co.indivara.hotel.model.entity.Room;

import java.util.List;
import java.util.Map;

public interface ReportService {
    List<Room> getAvailableRooms(GetAllAvailableRoomForm getAllAvailableRoomForm);
    Map<String, Integer> getHotelReport();
}
