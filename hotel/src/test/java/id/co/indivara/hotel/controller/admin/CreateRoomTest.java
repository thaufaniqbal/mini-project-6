package id.co.indivara.hotel.controller.admin;

import id.co.indivara.hotel.controller.AdminController;
import id.co.indivara.hotel.model.entity.Room;
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
import java.util.List;
import java.util.Objects;

@RunWith(MockitoJUnitRunner.class)
public class CreateRoomTest {
    @InjectMocks
    private AdminController adminController;

    @Mock
    private AdminService adminService;

    @Test
    public void createRoomSuccessTest(){
        List<Room> rooms = new ArrayList<>();
        Room room1 = Room.builder().roomNumber(1L).roomType("Deluxe").build();
        rooms.add(room1);

        Mockito.when(adminService.createRoom(room1)).thenReturn(room1);

        ResponseEntity<?>responseEntity = adminController.createRoom(room1);

        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assert.assertEquals(room1, responseEntity.getBody());
    }
    @Test
    public void createRoomFailedTest(){
        List<Room> rooms = new ArrayList<>();
        Room room1 = Room.builder().roomNumber(1L).roomType("Deluxe").build();
        rooms.add(room1);
        Room room2 = Room.builder().roomNumber(1L).roomType("Deluxe").build();

        ResponseEntity<?> responseEntity = null;
        if (Objects.equals(room1.getRoomNumber(), room2.getRoomNumber())) {
            responseEntity = adminController.createRoom(room2);
        }

        Assert.assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        Assert.assertNull(responseEntity.getBody());
    }

}
