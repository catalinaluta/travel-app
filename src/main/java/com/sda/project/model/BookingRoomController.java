package com.sda.project.model;
@Entity
@Table
public class BookingRoomController {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;

    private String roomType;

    private String propertyType;

    private date checkIn;

    private date checkOut;

    private String place;

    private String facilities;

}
