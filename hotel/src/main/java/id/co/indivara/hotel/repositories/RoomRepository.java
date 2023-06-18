package id.co.indivara.hotel.repositories;

import id.co.indivara.hotel.model.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
    Room findByRoomNumber(Long roomNumber);
}
