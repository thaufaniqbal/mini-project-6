package id.co.indivara.hotel.controller;

import id.co.indivara.hotel.model.GetAllAvailableForm;
import id.co.indivara.hotel.model.entity.Room;
import id.co.indivara.hotel.service.ReportService;
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
    public ResponseEntity<List<Room>> getAvailableRooms(@RequestBody GetAllAvailableForm getAllAvailableForm) {
        List<Room> availableRooms = reportService.getAvailableRooms(getAllAvailableForm);
        return ResponseEntity.ok(availableRooms);
    }

    @GetMapping("/total-rooms-booked")
    public ResponseEntity<Map<String, Integer>> getTotalRoomsBooked() {
        Map<String, Integer> totalRoomsBooked = reportService.getTotalRoomsBooked();
        return ResponseEntity.ok(totalRoomsBooked);
    }
}
