package id.co.indivara.hotel.controller.general;

import id.co.indivara.hotel.controller.GeneralController;
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

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class GetAllRoomsTest {
    @InjectMocks
    private GeneralController generalController;

    @Mock
    private GeneralService generalService;

    @Test
    public void getAllRoomsTest() {
        // membuat list room untuk contoh data
        List<Room> rooms = new ArrayList<>();
        Room room1 = Room.builder().roomNumber(1L).roomType("Deluxe").build();
        Room room2 = Room.builder().roomNumber(2L).roomType("Standar").build();
        rooms.add(room1);
        rooms.add(room2);

        // untuk mengembalikan data rooms
        Mockito.when(generalService.getAllRooms()).thenReturn(rooms);

        // memanggil metode getAllRooms() pada userController
        ResponseEntity<List<Room>> responseEntity = generalController.getAllRooms();

        // memeriksa kode response adalah 200 (OK)
        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        // memeriksa data room yang dikembalikan itu sesuai
        List<Room> returnedRooms = responseEntity.getBody();
        Assert.assertEquals(2, returnedRooms.size());
        Assert.assertEquals(1L, (long) returnedRooms.get(0).getRoomNumber());
        Assert.assertEquals("Deluxe", returnedRooms.get(0).getRoomType());
        Assert.assertEquals(2L, (long) returnedRooms.get(1).getRoomNumber());
        Assert.assertEquals("Standar", returnedRooms.get(1).getRoomType());
        // memeriksa metode generalService.getAllRooms() dipanggil itu sebanyak 1 kali
        Mockito.verify(generalService, Mockito.times(1)).getAllRooms();
    }
}
