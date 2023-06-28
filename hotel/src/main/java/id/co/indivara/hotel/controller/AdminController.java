package id.co.indivara.hotel.controller;

import id.co.indivara.hotel.model.entity.Customer;
import id.co.indivara.hotel.model.entity.Room;
import id.co.indivara.hotel.services.AdminService;
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

    @PostMapping("/login")
    public String login(){
        return "hello world";
    }
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
    @PutMapping("/remove-room/{roomId}")
    public ResponseEntity<String> removeRoom(@PathVariable Long roomId) {
        adminService.removeRoom(roomId);
        return ResponseEntity.ok("remove success");
    }

    @PutMapping("/update-room/{roomId}")
    public ResponseEntity<?> updateRoom(@PathVariable Long roomId, @RequestBody Room room) {
        return ResponseEntity.ok(adminService.updateRoom(roomId,room)) ;
    }

    @GetMapping("/get-customer/{customerId}")
    public ResponseEntity<?> getCustomer(@PathVariable Long customerId) {
        return ResponseEntity.ok(adminService.getCustomer(customerId));
    }
    @GetMapping("/get-customers")
    public ResponseEntity<List<Customer>> getAllCustomer() {
        return ResponseEntity.ok(adminService.getAllCustomer());
    }
    @PutMapping("/remove-customer/{customerId}")
    public ResponseEntity<?> removeCustomer(@PathVariable Long customerId) {
        adminService.removeCustomer(customerId);
        return ResponseEntity.ok("remove success");
    }
}
