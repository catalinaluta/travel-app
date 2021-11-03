package com.sda.project.service;

import com.sda.project.repository.ReservationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomService {

    private static final Logger log = LoggerFactory.getLogger(RoomService.class);

    @Autowired
    private final ReservationRepository reservationRepository;

    public RoomService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }
}
