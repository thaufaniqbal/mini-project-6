package id.co.indivara.hotel.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReserveRoomReceipt {
    private Long roomNumber;
    private Integer roomToken;
    private String systemMessage;
}
