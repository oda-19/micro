package com.example.license.service;

import com.example.license.model.License;
import com.example.license.repository.LicenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LicenseService {
    @Autowired
    private LicenseRepository licenseRepository;

    public License getLicense(int licenseId){
        return licenseRepository.findById(licenseId).orElse(null);
    }

    public Iterable<License> findAllLicenses() {
        return licenseRepository.findAll();
    }

    public void createLicense(License license){
        if (license != null){
            licenseRepository.save(license);
        }
    }

    public void updateLicense(int licenseId, License license){
        License existingLicense = licenseRepository.findById(licenseId).orElse(null);
        if (existingLicense != null) {
            license.setId(licenseId);
            licenseRepository.save(license);
        }
    }

    public void deleteLicense(int licenseId){
        licenseRepository.deleteById(licenseId);
    }
}
