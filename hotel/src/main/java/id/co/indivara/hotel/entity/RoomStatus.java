package id.co.indivara.hotel.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "trx_rooms_statuses")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoomStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    @JsonIgnore
    private Room room;

    private Boolean roomStatus;
}
