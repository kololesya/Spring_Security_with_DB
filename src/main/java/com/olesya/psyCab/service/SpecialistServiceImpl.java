package com.olesya.psyCab.service;

import com.olesya.psyCab.entity.Specialist;
import com.olesya.psyCab.repository.SpecialistRepository;
import com.olesya.psyCab.request.RegistrationSpecialistRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SpecialistServiceImpl implements SpecialistService{
    @Autowired
    private SpecialistRepository spRepo;

    @Override
    public List<Specialist> getAllSpecialists() {
        return spRepo.findAll();
    }

    @Override
    public Specialist registerSpecialist(RegistrationSpecialistRequest request) {
        Specialist spec = new Specialist(request.getFirstName(),
                request.getLastName(),
                request.getSpecialization());
                spRepo.save(spec);
        return spec;
    }

    @Override
    public Specialist findBySpecialization(String specialization) {
        return spRepo.findBySpecialization(specialization)
                .orElseThrow(() -> new UsernameNotFoundException("Specialist not found"));
    }
}
