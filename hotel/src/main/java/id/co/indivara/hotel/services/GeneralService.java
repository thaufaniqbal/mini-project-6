package id.co.indivara.hotel.services;

import id.co.indivara.hotel.model.dto.GetAllAvailableRoomForm;
import id.co.indivara.hotel.model.entity.Room;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;

public interface GeneralService {
    List<Room> getAvailableRooms(GetAllAvailableRoomForm getAllAvailableRoomForm);
    List<Room> getAllRooms();
}
