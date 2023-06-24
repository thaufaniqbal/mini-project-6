package id.co.indivara.hotel.controller.admin;

import id.co.indivara.hotel.controller.AdminController;
import id.co.indivara.hotel.services.AdminService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

@RunWith(MockitoJUnitRunner.class)
public class RemoveRoomTest {
    @InjectMocks
    private AdminController adminController;

    @Mock
    private AdminService adminService;
    @Test
    public void removeRoomTest(){
        Long roomId = 1L;
        ResponseEntity<?> responseEntity = adminController.removeRoom(roomId);
        Assert.assertEquals("remove success", responseEntity.getBody());
    }
}
