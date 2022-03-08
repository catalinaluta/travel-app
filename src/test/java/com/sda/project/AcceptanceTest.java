package com.sda.project;

import com.sda.project.model.Facility;
import com.sda.project.model.FacilityType;
import com.sda.project.model.Location;
import com.sda.project.model.Reservation;
import com.sda.project.model.Room;
import com.sda.project.model.RoomType;
import com.sda.project.model.User;
import com.sda.project.service.FacilityService;
import com.sda.project.service.LocationService;
import com.sda.project.service.ReservationService;
import com.sda.project.service.RoomService;
import com.sda.project.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class AcceptanceTest {

    @Autowired
    private FacilityService facilityService;

    @Autowired
    private RoomService roomService;

    @Autowired
    private UserService userService;

    @Autowired
    private LocationService locationService;

    @Autowired
    private ReservationService reservationService;

    @Transactional
    @Test
    void acceptanceTest() {
        // given
        Facility newWifi = new Facility(FacilityType.WIFI);
        Facility newRestaurant = new Facility(FacilityType.RESTAURANT);
        Facility wifi = facilityService.save(newWifi);
        Facility restaurant = facilityService.save(newRestaurant);

        Room newRoom1 = new Room("301", RoomType.DOUBLE);
        Room newRoom2 = new Room("201", RoomType.SINGLE);
        roomService.save(newRoom1);
        roomService.save(newRoom2);

        Location location = new Location("Hotel Cerbul", "Sinaia");
        location.addFacility(wifi);
        location.addFacility(restaurant);
        location.addRoom(newRoom1);
        location.addRoom(newRoom2);
        locationService.save(location);

        List<Location> locations = locationService.findAll();
        Long locationId = locations.get(0).getId();
        Location locationForBooking = locationService.findById(locationId);

        User user = userService.findByEmail("user@gmail.com");

        Reservation reservation = new Reservation(
                LocalDate.of(2021, 11, 20),
                LocalDate.of(2021, 11, 23),
                user,
                newRoom1);

        // when
        reservationService.save(reservation);

        // then
        assertThat(locationForBooking.getId()).isNotNull();
        assertThat(reservation.getUser()).isNotNull();
        assertThat(reservation.getRoom().getId()).isNotNull();
    }
}