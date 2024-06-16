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

    public Iterable<License> findAllLicenses(String keyword) {
        if (keyword != null) {
            return licenseRepository.searchByNamePoContainingIgnoreCase(keyword);
        }
        return licenseRepository.findAllByOrderByEndDateAscNamePoAsc();
    }

    public void createLicense(License license){
        if (license != null){
            licenseRepository.save(license);
        }
    }

    public boolean updateLicense(int licenseId, License license){
        License existingLicense = licenseRepository.findById(licenseId).orElse(null);
        if (existingLicense != null) {
            existingLicense.setNamePo(license.getNamePo());
            existingLicense.setIdType(license.getIdType());
            existingLicense.setStartDate(license.getStartDate());
            existingLicense.setEndDate(license.getEndDate());
            existingLicense.setCountStart(license.getCountStart());
            existingLicense.setCountNow(license.getCountNow());
            licenseRepository.save(existingLicense);
            return true;
        }
        return false;
    }

    public void deleteLicense(License license){
        licenseRepository.delete(license);
    }
}
