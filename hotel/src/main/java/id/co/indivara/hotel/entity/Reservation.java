package id.co.indivara.hotel.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "trx_reservations")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reservation {
}
