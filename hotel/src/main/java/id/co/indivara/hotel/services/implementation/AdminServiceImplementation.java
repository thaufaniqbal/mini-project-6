package id.co.indivara.hotel.services.implementation;

import id.co.indivara.hotel.model.GetAllAvailableRoomForm;
import id.co.indivara.hotel.model.entity.Customer;
import id.co.indivara.hotel.model.entity.Reservation;
import id.co.indivara.hotel.model.entity.Room;
import id.co.indivara.hotel.model.entity.Transaction;
import id.co.indivara.hotel.repositories.*;
import id.co.indivara.hotel.services.AdminService;
import id.co.indivara.hotel.services.GeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AdminServiceImplementation implements AdminService{
    @Autowired
    RoomRepository roomRepository;
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    CustomerRepository customerRepository;

    @Override
    public Map<String, Integer> getHotelReport() {
        List<Transaction> transactions = transactionRepository.findAll();
        Map<String, Integer> roomsBookedMap = new HashMap<>();

        for (Transaction transaction : transactions) {
            Room room = transaction.getReservation().getRoom();
            String roomType = room.getRoomType();
            int roomsBooked = roomsBookedMap.getOrDefault(roomType, 0);
            roomsBookedMap.put(roomType, roomsBooked + 1);
        }
        return roomsBookedMap;
    }

    @Override
    public Room createRoom(Room room) {
        if (roomRepository.existbyRoomNumber(room.getRoomNumber())){
            return null;
        }
        return roomRepository.save(room);
    }

    @Override
    public void removeRoom(Long roomId) {
        roomRepository.findById(roomId).orElse(null);
        roomRepository.deleteById(roomId);
    }

    @Override
    public Room updateRoom(Long roomId, Room room) {
        Room room1 = roomRepository.findById(roomId).orElse(null);
        room1.setRoomNumber(room.getRoomNumber());
        room1.setRoomType(room.getRoomType());
        return roomRepository.save(room1);
    }

    @Override
    public Customer getCustomer(Long id) {
        return customerRepository.findById(id).orElse(null);
    }

    @Override
    public List<Customer> getAllCustomer() {
        return customerRepository.findAll();
    }

    @Override
    public void removeCustomer(Long customerId) {
        customerRepository.findById(customerId).orElse(null);
        customerRepository.deleteById(customerId);
    }

}
