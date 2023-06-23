package id.co.indivara.hotel.controller;

import id.co.indivara.hotel.model.dto.GetAllAvailableRoomForm;
import id.co.indivara.hotel.model.entity.Customer;
import id.co.indivara.hotel.model.entity.Room;
import id.co.indivara.hotel.services.AdminService;
import id.co.indivara.hotel.services.GeneralService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    AdminService adminService;

    @GetMapping("/hotel-report")
    public ResponseEntity<Map<String, Integer>> getHotelReport() {
        return ResponseEntity.ok(adminService.getHotelReport());
    }
    @PostMapping("/create-room/")
    public ResponseEntity<Room> createRoom(@RequestBody Room room) {
        Room createdRoom = adminService.createRoom(room);
        if (createdRoom != null) {
            return ResponseEntity.ok(createdRoom);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    public ResponseEntity<String> removeRoom(Long roomId) {
        adminService.removeRoom(roomId);
        return ResponseEntity.ok("remove success");
    }


    public ResponseEntity<?> updateRoom(Long roomId, Room room) {
        return ResponseEntity.ok(adminService.updateRoom(roomId,room)) ;
    }


    public ResponseEntity<?> getCustomer(Long id) {
        return ResponseEntity.ok(adminService.getCustomer(id));
    }

    public ResponseEntity<List<Customer>> getAllCustomer() {
        return ResponseEntity.ok(adminService.getAllCustomer());
    }
    public ResponseEntity<?> removeCustomer(Long customerId) {
        adminService.removeCustomer(customerId);
        return ResponseEntity.ok("remove success");
    }
}
