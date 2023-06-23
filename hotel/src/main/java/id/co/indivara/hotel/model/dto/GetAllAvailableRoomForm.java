package id.co.indivara.hotel.model.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class GetAllAvailableRoomForm {
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
}
