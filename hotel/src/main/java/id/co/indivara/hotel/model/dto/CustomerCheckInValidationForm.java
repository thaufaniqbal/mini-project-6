package id.co.indivara.hotel.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerCheckInValidationForm {
    private Long customerId;
    private Long roomNumber;
    private Integer roomToken;
    private Boolean isCheckIn;
}
