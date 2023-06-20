package id.co.indivara.hotel.controller;

import id.co.indivara.hotel.model.CustomerCheckInMessage;
import id.co.indivara.hotel.model.CustomerCheckInValidationForm;
import id.co.indivara.hotel.model.ReserveRoomForm;
import id.co.indivara.hotel.model.ReserveRoomReceipt;
import id.co.indivara.hotel.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping ("/transaction")
public class TransactionController {
    @Autowired
    TransactionService transactionService;
    @PostMapping("/reserve/")
    public ResponseEntity<?> reserve(@RequestBody ReserveRoomForm reserveRoomForm) {
        ReserveRoomReceipt receipt = transactionService.reserveRoom(reserveRoomForm);
        if (receipt != null) {
            return ResponseEntity.ok().body(receipt);
        }
        return ResponseEntity.badRequest().build();
    }
    @PostMapping("/checkin/{roomNumber}/{roomToken}")
    public ResponseEntity<?> checkIn(@PathVariable Long roomNumber, @PathVariable Integer roomToken){
        CustomerCheckInMessage checkInResult = transactionService.checkIn(roomNumber, roomToken);
        if (checkInResult.getCustomerName() != null) {
            return ResponseEntity.ok().body(checkInResult);
        }
        return ResponseEntity.badRequest().build();
    }
    @PostMapping("/validation/")
    public ResponseEntity<?> customerValidationCheckIn(@RequestBody CustomerCheckInValidationForm customerCheckInValidationForm){
        if (transactionService.customerCheckInValidation(customerCheckInValidationForm)) {
            return ResponseEntity.ok().body(true);
        }
        return ResponseEntity.badRequest().build();
    }
    @PutMapping("/checkout/{roomToken}")
    public ResponseEntity<?> checkOut(@PathVariable Integer roomToken){
        if (transactionService.checkOut(roomToken)) {
            return ResponseEntity.ok().body(true);
        }
        return ResponseEntity.badRequest().build();
    }

}
