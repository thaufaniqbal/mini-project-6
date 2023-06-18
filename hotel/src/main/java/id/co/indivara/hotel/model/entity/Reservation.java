package id.co.indivara.hotel.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "trx_reservations")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JsonIgnore
    private Room room;

    @ManyToOne
    @JsonIgnore
    private Customer customer;

    private LocalDateTime reservationDate;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private Boolean isCheckIn;
    private Boolean validationCheckIn;
    private Integer roomToken;
}
