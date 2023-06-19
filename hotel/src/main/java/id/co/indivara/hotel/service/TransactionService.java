package id.co.indivara.hotel.service;

import id.co.indivara.hotel.model.CustomerValidationCheckIn;
import id.co.indivara.hotel.model.ReserveRoomForm;
import id.co.indivara.hotel.model.ReserveRoomReceipt;
import id.co.indivara.hotel.model.Validation;
import org.springframework.http.ResponseEntity;

public interface TransactionService {

    CustomerValidationCheckIn checkIn (Long roomNumber, Integer roomToken);
    Boolean customerValidationCheckIn (Validation validation);
    Boolean checkOut(Integer roomToken);
    ReserveRoomReceipt reserveRoom (ReserveRoomForm reserveRoomForm);
}
