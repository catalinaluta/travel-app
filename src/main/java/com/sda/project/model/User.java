package com.sda.project.model;


import java.lang.annotation.Repeatable;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;

    private String name;

    private String email;

    private String password;

}
