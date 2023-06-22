package id.co.indivara.hotel.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReserveRoomReceipt {
    private Long roomNumber;
    private Integer roomToken;
    private String systemMessage;
}
