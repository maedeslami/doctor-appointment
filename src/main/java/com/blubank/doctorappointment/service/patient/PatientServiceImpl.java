package com.blubank.doctorappointment.service.patient;

import com.blubank.doctorappointment.model.Patient;
import com.blubank.doctorappointment.repository.patient.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {
    @Autowired
    PatientRepository repository;

    @Override
    public void save(Patient patient) {
        repository.save(patient);
    }
}
