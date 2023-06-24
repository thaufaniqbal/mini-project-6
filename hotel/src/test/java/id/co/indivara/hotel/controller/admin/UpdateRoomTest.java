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

@RunWith(MockitoJUnitRunner.class)
public class UpdateRoomTest {
    @InjectMocks
    private AdminController adminController;

    @Mock
    private AdminService adminService;
    @Test
    public void updateRoomTest(){
        Long roomId = 1L;

        Room updatedRoom = new Room();
        updatedRoom.setRoomNumber(null);
        updatedRoom.setRoomType("Deluxe");

        Room originalRoom = new Room();
        originalRoom.setRoomNumber(456L);
        originalRoom.setRoomType("Standard");

        Mockito.when(adminService.updateRoom(roomId, updatedRoom)).thenReturn(originalRoom);

        ResponseEntity<?> responseEntity = adminController.updateRoom(roomId, updatedRoom);

        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assert.assertEquals(originalRoom, responseEntity.getBody());
    }
}
