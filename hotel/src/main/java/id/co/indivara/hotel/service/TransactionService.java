package id.co.indivara.hotel.service;

import id.co.indivara.hotel.model.CustomerValidationCheckIn;
import id.co.indivara.hotel.model.ReserveRoomForm;
import id.co.indivara.hotel.model.ReserveRoomReceipt;
import org.springframework.http.ResponseEntity;

public interface TransactionService {

    CustomerValidationCheckIn checkIn (Long roomNumber, Integer roomToken);
    Boolean customerValidationCheckIn (Long customerId, Long roomNumber, Integer roomToken, Boolean isCheckIn);
    void checkOut(Long roomId);
    ReserveRoomReceipt reserveRoom (ReserveRoomForm reserveRoomForm);
}
