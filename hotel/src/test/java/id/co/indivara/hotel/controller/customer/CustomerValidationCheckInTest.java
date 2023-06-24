package id.co.indivara.hotel.controller.customer;

import id.co.indivara.hotel.controller.CustomerController;
import id.co.indivara.hotel.model.dto.CustomerCheckInValidationForm;
import id.co.indivara.hotel.model.entity.Customer;
import id.co.indivara.hotel.model.entity.Reservation;
import id.co.indivara.hotel.model.entity.Room;
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
public class CustomerValidationCheckInTest {
    @InjectMocks
    private CustomerController customerController;
    @Mock
    private CustomerService customerService;

    @Test
    public void validCustomerValidationCheckInTest() {
        CustomerCheckInValidationForm customerCheckInValidationForm = CustomerCheckInValidationForm.builder()
                .customerId(1L)
                .isCheckIn(true)
                .roomNumber(2L)
                .roomToken(12345)
                .build();

        Mockito.when(customerService.customerCheckInValidation(customerCheckInValidationForm))
                .thenReturn(customerCheckInValidationForm.getIsCheckIn());

        ResponseEntity<?> responseEntity = customerController.customerValidationCheckIn(customerCheckInValidationForm);

        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assert.assertEquals(true, responseEntity.getBody());
    }
    @Test
    public void invalidCustomerValidationCheckInTest() {
        CustomerCheckInValidationForm customerCheckInValidationForm = CustomerCheckInValidationForm.builder()
                .customerId(1L)
                .isCheckIn(false)
                .roomNumber(2L)
                .roomToken(12345)
                .build();

        Mockito.when(customerService.customerCheckInValidation(customerCheckInValidationForm))
                .thenReturn(customerCheckInValidationForm.getIsCheckIn());


        ResponseEntity<?> responseEntity = customerController.customerValidationCheckIn(customerCheckInValidationForm);

        Assert.assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        Assert.assertEquals(false, responseEntity.getBody());
    }

}
