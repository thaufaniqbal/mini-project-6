package id.co.indivara.hotel.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReserveRoomReceipt {
    Integer roomNumber;
    Integer roomToken;
    String systemMessage;

}
