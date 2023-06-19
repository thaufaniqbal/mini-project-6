package id.co.indivara.hotel.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetAllAvailableForm {
    LocalDate checkInDate;
    LocalDate checkOutDate;
}
