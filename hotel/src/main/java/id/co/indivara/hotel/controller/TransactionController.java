package id.co.indivara.hotel.controller;

import id.co.indivara.hotel.model.ReserveRoomForm;
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
    public ResponseEntity<?> reserve(@RequestBody ReserveRoomForm reserveRoomForm){
        Boolean reserved = transactionService.reserveRoom(reserveRoomForm);
        if (reserved){
            return ResponseEntity.ok().body("reserve sukses");
        }
        return ResponseEntity.badRequest().build();
    }
    @PostMapping("/checkin/{roomNumber}/{roomToken}")
    public ResponseEntity<?> checkIn(@PathVariable Long roomNumber, @PathVariable Integer roomToken){
        Boolean checkIn = transactionService.checkIn(roomNumber, roomToken);
        if (checkIn){
            return ResponseEntity.ok().body("checkIn sukses..");
        }
        return ResponseEntity.badRequest().build();
    }

}
