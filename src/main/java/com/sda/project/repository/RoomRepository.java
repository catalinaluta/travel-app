package com.sda.project.repository;

import com.sda.project.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {

    @Query("FROM Room r WHERE r.location.id = :locationId ")
//    @Query("FROM Room r join r.location l WHERE l.id = :id")
    List<Room> getRoomsByLocationId(Long locationId);
}
