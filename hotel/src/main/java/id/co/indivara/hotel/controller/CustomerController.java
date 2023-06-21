package id.co.indivara.hotel.controller;

import id.co.indivara.hotel.model.*;
import id.co.indivara.hotel.model.entity.Room;
import id.co.indivara.hotel.services.CustomerService;
import id.co.indivara.hotel.services.GeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping ("/customer")
public class CustomerController {
    @Autowired
    CustomerService customerService;
    @Autowired
    GeneralService generalService;
    @PostMapping("/reserve/")
    public ResponseEntity<?> reserve(@RequestBody ReserveRoomForm reserveRoomForm) {
        return null;
    }
    @PostMapping("/checkin/{roomNumber}/{roomToken}")
    public ResponseEntity<?> checkIn(@PathVariable Long roomNumber, @PathVariable Integer roomToken){
        return null;
    }

    @PostMapping("/validation/")
    public ResponseEntity<?> customerValidationCheckIn(@RequestBody CustomerCheckInValidationForm customerCheckInValidationForm){
        return null;
    }
    @PutMapping("/checkout/{roomToken}")
    public ResponseEntity<?> checkOut(@PathVariable Integer roomToken){
        return null;
    }

    @GetMapping("/available-rooms")
    public ResponseEntity<List<Room>> getAvailableRooms(@RequestBody GetAllAvailableRoomForm getAllAvailableRoomForm) {
        return null;
    }
    @GetMapping("/get-rooms")
    public ResponseEntity<List<Room>> getAllRooms() {
        return null;
    }

}
