package id.co.indivara.hotel.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Validation {
    Long customerId;
    Long roomNumber;
    Integer roomToken;
    Boolean isCheckIn;
}
