package id.co.indivara.hotel.controller;

import id.co.indivara.hotel.model.GetAllAvailableRoomForm;
import id.co.indivara.hotel.model.entity.Room;
import id.co.indivara.hotel.services.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/report")
public class ReportController {
    @Autowired
    ReportService reportService;

    @GetMapping("/available-rooms")
    public ResponseEntity<List<Room>> getAvailableRooms(@RequestBody GetAllAvailableRoomForm getAllAvailableRoomForm) {
        List<Room> availableRooms = reportService.getAvailableRooms(getAllAvailableRoomForm);
        return ResponseEntity.ok(availableRooms);
    }

    @GetMapping("/hotel-report")
    public ResponseEntity<Map<String, Integer>> getHotelReport() {
        Map<String, Integer> totalRoomsBooked = reportService.getHotelReport();
        return ResponseEntity.ok(totalRoomsBooked);
    }

}
