package com.olesya.psyCab.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class RegistrationSpecialistRequest {
    private String firstName;

    private String lastName;

    private String specialization;
}
