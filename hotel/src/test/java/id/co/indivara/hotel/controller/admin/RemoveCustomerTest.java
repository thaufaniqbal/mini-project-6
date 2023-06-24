package id.co.indivara.hotel.controller.admin;

import id.co.indivara.hotel.controller.AdminController;
import id.co.indivara.hotel.model.entity.Customer;
import id.co.indivara.hotel.services.AdminService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@RunWith(MockitoJUnitRunner.class)
public class RemoveCustomerTest {
    @InjectMocks
    private AdminController adminController;

    @Mock
    private AdminService adminService;
    @Test
    public void removeCustomerTest(){
        Long customerId = 1L;
        ResponseEntity<?> responseEntity = adminController.removeCustomer(customerId);
        Assert.assertEquals("remove success", responseEntity.getBody());
    }
}
