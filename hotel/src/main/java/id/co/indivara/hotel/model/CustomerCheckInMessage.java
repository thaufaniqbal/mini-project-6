package id.co.indivara.hotel.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomerCheckInMessage {
    private String customerName;
    private String message;
}
