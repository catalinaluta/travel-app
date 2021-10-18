package model;

import java.time.LocalDate;
@Entity
@Getter
@Setter


public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer id;

    private String name;

    private String email;

    private LocalDate dateOfBirth;

    private String password;

}
