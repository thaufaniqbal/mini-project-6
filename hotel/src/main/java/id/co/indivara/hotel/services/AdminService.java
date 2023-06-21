package id.co.indivara.hotel.services;

import id.co.indivara.hotel.model.GetAllAvailableRoomForm;
import id.co.indivara.hotel.model.entity.Customer;
import id.co.indivara.hotel.model.entity.Room;

import java.util.List;
import java.util.Map;

public interface AdminService {

    Map<String, Integer> getHotelReport();

    //room
    Room createRoom(Room room);
    void removeRoom(Long roomId);
    Room updateRoom(Long roomId, Room room);

    //customer
    Customer getCustomer(Long id);
    List<Customer> getAllCustomer();
    void removeCustomer(Long customerId);

}
