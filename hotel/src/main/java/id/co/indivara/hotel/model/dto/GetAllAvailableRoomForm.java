package id.co.indivara.hotel.model.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class GetAllAvailableRoomForm {
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
}
