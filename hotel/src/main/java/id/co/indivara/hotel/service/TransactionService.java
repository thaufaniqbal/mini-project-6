package id.co.indivara.hotel.service;

import id.co.indivara.hotel.model.ReserveRoomForm;
import org.springframework.http.ResponseEntity;

public interface TransactionService {
    Boolean checkIn (Long roomNumber, Integer roomToken);
    void checkOut(Long roomId);
    Boolean reserveRoom (ReserveRoomForm reserveRoomForm);
}
