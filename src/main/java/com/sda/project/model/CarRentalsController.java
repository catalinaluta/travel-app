package com.sda.project.model;
@Entity
@Table
public class CarRentalsController {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;

    private String brand;

    private String transmision;
}
