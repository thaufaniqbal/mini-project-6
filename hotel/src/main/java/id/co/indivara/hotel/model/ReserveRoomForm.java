package id.co.indivara.hotel.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ReserveRoomForm {
    private Long customerId;
    private Long roomId;;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
}
