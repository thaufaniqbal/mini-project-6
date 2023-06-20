package id.co.indivara.hotel.model;

import lombok.Data;

@Data
public class CustomerCheckInValidationForm {
    private Long customerId;
    private Long roomNumber;
    private Integer roomToken;
    private Boolean isCheckIn;
}
