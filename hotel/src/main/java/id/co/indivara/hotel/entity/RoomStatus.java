package id.co.indivara.hotel.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "rooms_status")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoomStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
    private Room room;
    private Boolean roomStatus;
}
