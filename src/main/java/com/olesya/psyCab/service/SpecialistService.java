package com.olesya.psyCab.service;

import com.olesya.psyCab.entity.Specialist;
import com.olesya.psyCab.request.RegistrationSpecialistRequest;

import java.util.List;

public interface SpecialistService {

    List<Specialist> getAllSpecialists();

    Specialist registerSpecialist(RegistrationSpecialistRequest request);

    Specialist findBySpecialization(String specialization);
}
