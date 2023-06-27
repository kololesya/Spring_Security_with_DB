package com.olesya.psyCab.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name="specialists")
@Getter
@Setter
public class Specialists {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nameOfSpecialist")
    private String nameOfSpecialist;

    @Column(name = "lastNameOfSpecialist")
    private String lastNameOfSpecialist;

    @Column(name = "specialisation")
    private String specialisation;

    @Column(name = "dateOfVisit")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date dateOfVisit;
}
