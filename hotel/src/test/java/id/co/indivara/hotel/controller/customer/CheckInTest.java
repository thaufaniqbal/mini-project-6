package id.co.indivara.hotel.controller.customer;

import id.co.indivara.hotel.controller.CustomerController;
import id.co.indivara.hotel.model.dto.CustomerCheckInMessage;
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
public class CheckInTest {
    @InjectMocks
    private CustomerController customerController;
    @Mock
    private CustomerService customerService;

    @Test
    public void validCheckInTest(){
        Room room = Room.builder().
                id(1L).
                roomNumber(1L).
                roomType("Deluxe").
                build();

        Customer customer = Customer.builder().
                id(1L).
                customerName("thaufan").
                build();

        Reservation reservation = Reservation.builder().
                isCheckIn(false).
                checkInValidation(false).
                checkInValidation(false).
                checkIn(LocalDate.now()).
                checkOut(LocalDate.now().plusDays(1)).
                reservationDate(LocalDate.now().minusDays(2).atStartOfDay()).
                customer(customer).
                room(room).
                roomToken(12345).
                id(1L).
                build();

        CustomerCheckInMessage customerCheckInMessage = CustomerCheckInMessage.builder().
                customerName(customer.getCustomerName()).
                message("Check Notification").
                build();
        Mockito.when(customerService.checkIn(reservation.getRoom().getRoomNumber(), reservation.getRoomToken())).thenReturn(customerCheckInMessage);
        ResponseEntity<?> responseEntity = customerController.checkIn(reservation.getRoom().getRoomNumber(), reservation.getRoomToken());
        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assert.assertNotNull(responseEntity.getBody());
        Assert.assertEquals(customerCheckInMessage, responseEntity.getBody());
    }
    @Test
    public void invalidCheckInDateTest(){
        Room room = Room.builder()
                .id(1L)
                .roomNumber(1L)
                .roomType("Deluxe")
                .build();

        Customer customer = Customer.builder()
                .id(1L)
                .customerName("thaufan")
                .build();

        Reservation reservation = Reservation.builder()
                .isCheckIn(false)
                .checkInValidation(false)
                .checkIn(LocalDate.now().plusDays(3))
                .checkOut(LocalDate.now().plusDays(4))
                .reservationDate(LocalDate.now().minusDays(2).atStartOfDay())
                .customer(customer)
                .room(room)
                .roomToken(12345)
                .id(1L)
                .build();

        CustomerCheckInMessage customerCheckInMessage = CustomerCheckInMessage.builder()
                .customerName(null)
                .message("Invalid check-in request")
                .build();

        Mockito.when(customerService.checkIn(reservation.getRoom().getRoomNumber(), reservation.getRoomToken()))
                .thenReturn(customerCheckInMessage);

        ResponseEntity<?> responseEntity = customerController.checkIn(reservation.getRoom().getRoomNumber(),
                reservation.getRoomToken());

        Assert.assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        Assert.assertNotNull(responseEntity.getBody());
        Assert.assertEquals(customerCheckInMessage, responseEntity.getBody());
    }
}
