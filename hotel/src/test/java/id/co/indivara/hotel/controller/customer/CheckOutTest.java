package id.co.indivara.hotel.controller.customer;

import id.co.indivara.hotel.controller.CustomerController;
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


@RunWith(MockitoJUnitRunner.class)
public class CheckOutTest {

    @InjectMocks
    private CustomerController customerController;

    @Mock
    private CustomerService customerService;


    @Test
    public void testCheckOut_Success() {
        Integer roomToken = 12345;

        Mockito.when(customerService.checkOut(roomToken)).thenReturn(true);

        ResponseEntity<?> responseEntity = customerController.checkOut(roomToken);

        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void testCheckOut_Failure() {
        Integer roomToken = 12345;

        Mockito.when(customerService.checkOut(roomToken)).thenReturn(false);

        ResponseEntity<?> responseEntity = customerController.checkOut(roomToken);

        Assert.assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
    }
}


