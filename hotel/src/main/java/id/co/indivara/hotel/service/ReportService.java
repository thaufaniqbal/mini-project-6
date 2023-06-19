package id.co.indivara.hotel.service;

import id.co.indivara.hotel.model.GetAllAvailableForm;
import id.co.indivara.hotel.model.entity.Room;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface ReportService {
    List<Room> getAvailableRooms(GetAllAvailableForm getAllAvailableForm);

    Map<String, Integer> getTotalRoomsBooked();
}
