package id.co.indivara.hotel.services;

import id.co.indivara.hotel.model.dto.CustomerCheckInMessage;
import id.co.indivara.hotel.model.dto.ReserveRoomForm;
import id.co.indivara.hotel.model.dto.ReserveRoomReceipt;
import id.co.indivara.hotel.model.dto.CustomerCheckInValidationForm;

public interface CustomerService {
    CustomerCheckInMessage checkIn (Long roomNumber, Integer roomToken);
    Boolean customerCheckInValidation(CustomerCheckInValidationForm customerCheckInValidationForm);
    Boolean checkOut(Integer roomToken);
    ReserveRoomReceipt reserveRoom (ReserveRoomForm reserveRoomForm);

}
