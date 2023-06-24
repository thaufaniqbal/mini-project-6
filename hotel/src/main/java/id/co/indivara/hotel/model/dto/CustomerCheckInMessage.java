package id.co.indivara.hotel.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class CustomerCheckInMessage {
    private String customerName;
    private String message;
}
