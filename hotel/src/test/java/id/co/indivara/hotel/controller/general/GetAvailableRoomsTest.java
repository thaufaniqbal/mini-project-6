package id.co.indivara.hotel.controller.general;

import id.co.indivara.hotel.controller.GeneralController;
import id.co.indivara.hotel.model.dto.GetAllAvailableRoomForm;
import id.co.indivara.hotel.model.entity.Reservation;
import id.co.indivara.hotel.model.entity.Room;
import id.co.indivara.hotel.services.GeneralService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class GetAvailableRoomsTest {
    @InjectMocks
    private GeneralController generalController;

    @Mock
    private GeneralService generalService;

    @Test
    public void getAvailableRoomsWithDateTest(){

        List<Room> rooms = new ArrayList<>();
        Room room1 = Room.builder().roomNumber(1L).roomType("Deluxe").build();
        Room room2 = Room.builder().roomNumber(2L).roomType("Standar").build();
        rooms.add(room1);
        rooms.add(room2);

        Reservation reservation = Reservation.builder().
                checkIn(LocalDate.now().plusDays(3)).
                checkOut(LocalDate.now().plusDays(5)).
                room(room1).
                build();


        GetAllAvailableRoomForm getAllAvailableRoomForm = GetAllAvailableRoomForm.builder().
                checkInDate(LocalDate.now().plusDays(3)).
                checkOutDate(LocalDate.now().plusDays(5)).
                build();

        if (reservation.getCheckIn()==getAllAvailableRoomForm.getCheckInDate()){
            Room room = reservation.getRoom();
            rooms.forEach(room3 -> {
                if (room3==room){
                    rooms.remove(room3);
                }
            });
        }

        // untuk mengembalikan data rooms
        Mockito.when(generalService.getAvailableRooms(getAllAvailableRoomForm)).thenReturn(rooms);

        // memanggil metode getAllRooms() pada userController
        ResponseEntity<List<Room>> responseEntity = generalController.getAvailableRooms(getAllAvailableRoomForm);

        // memeriksa kode response adalah 200 (OK)
        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assert.assertEquals(rooms, responseEntity.getBody());
        Mockito.verify(generalService, Mockito.times(1)).getAvailableRooms(getAllAvailableRoomForm);
    }
    @Test
    public void getAvailableRoomsWithOutDateTest(){

        List<Room> rooms = new ArrayList<>();
        Room room1 = Room.builder().roomNumber(1L).roomType("Deluxe").build();
        Room room2 = Room.builder().roomNumber(2L).roomType("Standar").build();
        rooms.add(room1);
        rooms.add(room2);

        Reservation reservation = Reservation.builder().
                checkIn(LocalDate.now().plusDays(3)).
                checkOut(LocalDate.now().plusDays(5)).
                room(room1).
                build();

        GetAllAvailableRoomForm getAllAvailableRoomForm = GetAllAvailableRoomForm.builder().
                checkInDate(LocalDate.now()).
                checkOutDate(LocalDate.now()).
                build();

        if (reservation.getCheckIn()==getAllAvailableRoomForm.getCheckInDate()){
            Room room = reservation.getRoom();
            rooms.forEach(room3 -> {
                if (room3==room){
                    rooms.remove(room3);
                }
            });
        }

        // untuk mengembalikan data rooms
        Mockito.when(generalService.getAvailableRooms(getAllAvailableRoomForm)).thenReturn(rooms);

        // memanggil metode getAllRooms() pada userController
        ResponseEntity<List<Room>> responseEntity = generalController.getAvailableRooms(getAllAvailableRoomForm);

        // memeriksa kode response adalah 200 (OK)
        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assert.assertEquals(rooms, responseEntity.getBody());
        Mockito.verify(generalService, Mockito.times(1)).getAvailableRooms(getAllAvailableRoomForm);
    }
}
