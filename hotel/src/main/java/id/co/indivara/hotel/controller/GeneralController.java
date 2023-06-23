package id.co.indivara.hotel.controller;

import id.co.indivara.hotel.model.dto.GetAllAvailableRoomForm;
import id.co.indivara.hotel.model.entity.Room;
import id.co.indivara.hotel.services.GeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GeneralController {
    @Autowired
    GeneralService generalService;

    @GetMapping("/available-rooms")
    public ResponseEntity<List<Room>> getAvailableRooms(@RequestBody GetAllAvailableRoomForm getAllAvailableRoomForm) {
        return ResponseEntity.ok(generalService.getAvailableRooms(getAllAvailableRoomForm));
    }
    @GetMapping("/get-rooms")
    public ResponseEntity<List<Room>> getAllRooms() {
        return ResponseEntity.ok(generalService.getAllRooms());
    }
}
