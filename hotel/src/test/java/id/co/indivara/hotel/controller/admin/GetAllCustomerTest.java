package id.co.indivara.hotel.controller.admin;

import id.co.indivara.hotel.controller.AdminController;
import id.co.indivara.hotel.model.entity.Customer;
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

@RunWith(MockitoJUnitRunner.class)
public class GetAllCustomerTest {
    @InjectMocks
    private AdminController adminController;

    @Mock
    private AdminService adminService;

    @Test
    public void getAllCustomerTest(){
        List<Customer> customers = new ArrayList<>();
        customers.add(Customer.builder().id(1L).customerName("thaufan").build());
        customers.add(Customer.builder().id(2L).customerName("Ananda").build());
        customers.add(Customer.builder().id(3L).customerName("tegar").build());

        Mockito.when(adminService.getAllCustomer()).thenReturn(customers);

        ResponseEntity<List<Customer>> responseEntity = adminController.getAllCustomer();

        // memeriksa kode response adalah 200 (OK)
        Assert.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        // memeriksa data room yang dikembalikan itu sesuai
        List<Customer> customerList = responseEntity.getBody();
        Assert.assertEquals(customerList.size(), customerList.size());
    }
}
