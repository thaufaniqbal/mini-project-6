package id.co.indivara.hotel.controller.customer;

import id.co.indivara.hotel.controller.CustomerController;
import id.co.indivara.hotel.model.dto.ReserveRoomForm;
import id.co.indivara.hotel.model.dto.ReserveRoomReceipt;
import id.co.indivara.hotel.services.CustomerService;
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

@RunWith(MockitoJUnitRunner.class)
public class ReserveTest {
    @InjectMocks
    private CustomerController customerController;
    @Mock
    private CustomerService customerService;

    @Test
    public void validReserveTest(){
        ReserveRoomForm reserveRoomForm = ReserveRoomForm.builder()
                .customerId(1L)
                .roomId(1L)
                .checkInDatee(LocalDate.now())
                .checkOutDate(LocalDate.now().plusDays(2))
                .build();

        ReserveRoomReceipt reserveRoomReceipt = ReserveRoomReceipt.builder()
                .roomToken(12345)
                .build();

        Mockito.when(customerService.reserveRoom(reserveRoomForm)).thenReturn(reserveRoomReceipt);

        //controller
        ResponseEntity<?> responseEntity = customerController.reserve(reserveRoomForm);

        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assert.assertNotNull(responseEntity.getBody());
        Assert.assertEquals(reserveRoomReceipt, responseEntity.getBody());
    }
    @Test
    public void invalidReserveTest() {

        ReserveRoomForm reserveRoomForm = ReserveRoomForm.builder().
                customerId(1L).
                roomId(1L).
                checkInDatee(LocalDate.now().minusDays(5)).
                checkOutDate(LocalDate.now().minusDays(3)).
                build();

        ReserveRoomReceipt reserveRoomReceipt = ReserveRoomReceipt.builder()
                .roomToken(null)
                .build();

        Mockito.when(customerService.reserveRoom(reserveRoomForm)).thenReturn(reserveRoomReceipt);

        ResponseEntity<?> responseEntity = customerController.reserve(reserveRoomForm);

        Assert.assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        Assert.assertNotNull(responseEntity.getBody());
        Assert.assertEquals(reserveRoomReceipt, responseEntity.getBody());
    }
}
