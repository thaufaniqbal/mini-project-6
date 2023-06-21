package id.co.indivara.hotel.services;

import id.co.indivara.hotel.model.CustomerCheckInMessage;
import id.co.indivara.hotel.model.ReserveRoomForm;
import id.co.indivara.hotel.model.ReserveRoomReceipt;
import id.co.indivara.hotel.model.CustomerCheckInValidationForm;

public interface CustomerService {
    CustomerCheckInMessage checkIn (Long roomNumber, Integer roomToken);
    Boolean customerCheckInValidation(CustomerCheckInValidationForm customerCheckInValidationForm);
    Boolean checkOut(Integer roomToken);
    ReserveRoomReceipt reserveRoom (ReserveRoomForm reserveRoomForm);
}
