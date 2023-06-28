package com.olesya.psyCab.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name="specialist")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Specialist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "specialization")
    private String specialization;

//    @Column(name = "dateOfVisit")
//    @DateTimeFormat(pattern = "dd-MM-yyyy")
//    private Date dateOfVisit;

    public Specialist(String firstName, String lastName, String specialization){
        this.firstName = firstName;
        this.lastName = lastName;
        this.specialization = specialization;
    }
}
