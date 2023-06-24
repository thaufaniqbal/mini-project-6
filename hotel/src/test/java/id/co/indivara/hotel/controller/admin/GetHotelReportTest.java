package id.co.indivara.hotel.controller.admin;

import id.co.indivara.hotel.controller.AdminController;
import id.co.indivara.hotel.model.entity.Reservation;
import id.co.indivara.hotel.model.entity.Room;
import id.co.indivara.hotel.model.entity.Transaction;
import id.co.indivara.hotel.services.AdminService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RunWith(MockitoJUnitRunner.class)
public class GetHotelReportTest {
    @InjectMocks
    private AdminController adminController;

    @Mock
    private AdminService adminService;
    @Test
    public void gethotelReportTest(){
        List<Reservation> reservations = new ArrayList<>();
        List<Transaction> transactions = new ArrayList<>();
        List<Room> rooms = new ArrayList<>();
        for (long i = 0; i <= 3; i++) {
            rooms.add(Room.builder().id(i).roomType("Deluxe").build());
            reservations.add(Reservation.builder().id(i).room(rooms.get((int) i)).build());
            transactions.add(Transaction.builder().id(i).reservation(reservations.get((int) i)).build());
        }
        for (long i = 4; i <= 6; i++) {
            rooms.add(Room.builder().id(i).roomType("Standar").build());
            reservations.add(Reservation.builder().id(i).room(rooms.get((int) i)).build());
            transactions.add(Transaction.builder().id(i).reservation(reservations.get((int) i)).build());
        }
        for (long i = 7; i <= 9; i++) {
            rooms.add(Room.builder().id(i).roomType("Superior").build());
            reservations.add(Reservation.builder().id(i).room(rooms.get((int) i)).build());
            transactions.add(Transaction.builder().id(i).reservation(reservations.get((int) i)).build());
        }
        Map<String, Integer> roomsBookedMap = new HashMap<>();
        for (Transaction transaction : transactions) {
            Room room = transaction.getReservation().getRoom();
            String roomType = room.getRoomType();
            int roomsBooked = roomsBookedMap.getOrDefault(roomType, 0);
            roomsBookedMap.put(roomType, roomsBooked + 1);
        }
        Mockito.when(adminService.getHotelReport()).thenReturn(roomsBookedMap);

        ResponseEntity<Map<String, Integer>> mapResponseEntity = adminController.getHotelReport();
        Assert.assertEquals(HttpStatus.OK, mapResponseEntity.getStatusCode());
        Assert.assertEquals(roomsBookedMap, mapResponseEntity.getBody());
    }
}
