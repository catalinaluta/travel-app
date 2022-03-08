package com.sda.project.config;

import com.sda.project.controller.exception.ResourceAlreadyExistsException;
import com.sda.project.model.Facility;
import com.sda.project.model.FacilityType;
import com.sda.project.model.Location;
import com.sda.project.model.Privilege;
import com.sda.project.model.PrivilegeType;
import com.sda.project.model.Reservation;
import com.sda.project.model.Role;
import com.sda.project.model.RoleType;
import com.sda.project.model.Room;
import com.sda.project.model.RoomType;
import com.sda.project.model.User;
import com.sda.project.repository.FacilityRepository;
import com.sda.project.repository.LocationRepository;
import com.sda.project.repository.PrivilegeRepository;
import com.sda.project.repository.ReservationRepository;
import com.sda.project.repository.RoleRepository;
import com.sda.project.repository.RoomRepository;
import com.sda.project.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Configuration
public class DbInit {

    private static final Logger log = LoggerFactory.getLogger(DbInit.class);

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PrivilegeRepository privilegeRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FacilityRepository facilityRepository;
    @Autowired
    private LocationRepository locationRepository;
    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private ReservationRepository reservationRepository;

    @Bean
    public CommandLineRunner initialData() {
        return args -> {
            log.info("setup initial data");

            // create privileges
            Privilege readPrivilege = createPrivilegeIfNotFound(PrivilegeType.READ_PRIVILEGE);
            Privilege writePrivilege = createPrivilegeIfNotFound(PrivilegeType.WRITE_PRIVILEGE);

            // create roles
            createRoleIfNotFound(RoleType.ADMIN, Set.of(readPrivilege, writePrivilege));
            createRoleIfNotFound(RoleType.USER, Set.of(readPrivilege, writePrivilege));

            createAdmin();
            createUser();

//            addData();
        };
    }

    @Transactional
    private void addData() {
        Facility wifi = new Facility(FacilityType.WIFI);
        Facility restaurant = new Facility(FacilityType.RESTAURANT);
//        facilityRepository.save(wifi);
//        facilityRepository.save(restaurant);

        Location location = new Location("Hotel Cerbul", "Sinaia");
        location.addFacility(wifi);
        location.addFacility(restaurant);
        Room room1 = new Room("301", RoomType.DOUBLE);
        Room room2 = new Room("201", RoomType.SINGLE);
        roomRepository.save(room1);
        roomRepository.save(room2);

        location.addRoom(room1);
        location.addRoom(room2);
        locationRepository.save(location);

        room1.setLocation(location);
        User user = userRepository.findByEmail("user@gmail.com").get();

        List<Room> roomsByLocationId = roomRepository.getRoomsByLocationId(location.getId());

        Reservation reservation = new Reservation(
                LocalDate.of(2021, 11, 20),
                LocalDate.of(2021, 11, 20),
                user,
                room1);

        reservationRepository.save(reservation);
    }

    private User createAdmin() {
        User admin = new User(
                "admin@gmail.com",
                "{bcrypt}$2y$12$92ZkDrGVS3W5ZJI.beRlEuyRCPrIRlkEHz6T.7MVmH38l4/VAHhyi",
                "bill",
                "clinton");
        Role adminRole = roleRepository.findByType(RoleType.ADMIN).orElseThrow();
        admin.addRole(adminRole);
        userRepository.save(admin);
        return admin;
    }

    private User createUser() {
        User user = new User(
                "user@gmail.com",
                "{bcrypt}$2y$12$92ZkDrGVS3W5ZJI.beRlEuyRCPrIRlkEHz6T.7MVmH38l4/VAHhyi",
                "alex",
                "vasile");
        Role userRole = roleRepository.findByType(RoleType.USER).orElseThrow();
        user.addRole(userRole);
        return userRepository.save(user);
    }

    @Transactional
    private Role createRoleIfNotFound(RoleType type, Set<Privilege> privileges) {
        return (Role) roleRepository.findByType(type)
                .map(existingPrivilege -> {
                    throw new ResourceAlreadyExistsException("role already exists");
                })
                .orElseGet(() -> {
                    Role role = new Role(type);
                    role.setPrivileges(privileges);
                    return roleRepository.save(role);
                });
    }

    @Transactional
    private Privilege createPrivilegeIfNotFound(PrivilegeType name) {
        return (Privilege) privilegeRepository.findByType(name)
                .map(existingPrivilege -> {
                    throw new ResourceAlreadyExistsException("privilege already exists");
                })
                .orElseGet(() -> privilegeRepository.save(new Privilege(name)));
    }
}
