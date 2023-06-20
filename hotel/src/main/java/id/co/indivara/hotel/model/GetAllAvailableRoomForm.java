package id.co.indivara.hotel.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class GetAllAvailableRoomForm {
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
}
