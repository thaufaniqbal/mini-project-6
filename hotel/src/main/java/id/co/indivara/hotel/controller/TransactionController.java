package id.co.indivara.hotel.controller;

import id.co.indivara.hotel.model.CustomerValidationCheckIn;
import id.co.indivara.hotel.model.ReserveRoomForm;
import id.co.indivara.hotel.model.ReserveRoomReceipt;
import id.co.indivara.hotel.service.TransactionService;
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
        CustomerValidationCheckIn checkInResult = transactionService.checkIn(roomNumber, roomToken);
        if (checkInResult.getCustomerName() != null) {
            return ResponseEntity.ok().body(checkInResult);
        }
        return ResponseEntity.badRequest().build();
    }
    @PostMapping("/checkin/{customerId}/{roomNumber}/{roomToken}/{isCheckIn}")
    public ResponseEntity<?> customerValidationCheckIn(@PathVariable Long customerId,
                                                       @PathVariable Long roomNumber,
                                                       @PathVariable Integer roomToken,
                                                       @PathVariable Boolean isCheckIn){
        Boolean checkInResult = transactionService.customerValidationCheckIn( customerId,roomNumber, roomToken, isCheckIn);
        if (checkInResult) {
            return ResponseEntity.ok().body(checkInResult);
        }
        return ResponseEntity.badRequest().build();
    }


}
