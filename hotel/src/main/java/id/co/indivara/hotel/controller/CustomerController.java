package id.co.indivara.hotel.controller;

import id.co.indivara.hotel.model.dto.*;
import id.co.indivara.hotel.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping ("/customer")
public class CustomerController {
    @Autowired
    CustomerService customerService;
    @PostMapping("/reserve/")
    public ResponseEntity<?> reserve(@RequestBody ReserveRoomForm reserveRoomForm) {
        ReserveRoomReceipt customerCheckInMessage = customerService.reserveRoom(reserveRoomForm);
        if (customerCheckInMessage.getRoomToken() != null) {
            return ResponseEntity.ok(customerCheckInMessage);
        }
        return ResponseEntity.badRequest().body(customerCheckInMessage);
    }
    @PostMapping("/checkin/{roomNumber}/{roomToken}")
    public ResponseEntity<?> checkIn(@PathVariable Long roomNumber, @PathVariable Integer roomToken){
        CustomerCheckInMessage customerCheckInMessage = customerService.checkIn(roomNumber,roomToken);
        if (customerCheckInMessage.getCustomerName()!=null){
            return ResponseEntity.ok(customerCheckInMessage);
        }
        return ResponseEntity.badRequest().body(customerCheckInMessage);
    }

    @PostMapping("/validation/")
    public ResponseEntity<?> customerValidationCheckIn(@RequestBody CustomerCheckInValidationForm customerCheckInValidationForm){
        if (customerService.customerCheckInValidation(customerCheckInValidationForm)) {
            return ResponseEntity.ok().body(customerService.customerCheckInValidation(customerCheckInValidationForm));
        }
        return ResponseEntity.badRequest().body(customerService.customerCheckInValidation(customerCheckInValidationForm));
    }
    @PutMapping("/checkout/{roomToken}")
    public ResponseEntity<?> checkOut(@PathVariable Integer roomToken){
        if (customerService.checkOut(roomToken)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }


}
