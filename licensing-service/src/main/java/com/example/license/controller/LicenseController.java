package com.example.license.controller;

import com.example.license.model.License;
import com.example.license.service.LicenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/licenses")
public class LicenseController {
    @Autowired
    private LicenseService licenseService;

    @RequestMapping(value="/{licenseId}",method = RequestMethod.GET)
    public ResponseEntity<License> getLicense(@PathVariable("licenseId") int licenseId){
        License license = licenseService.getLicense(licenseId);
        return ResponseEntity.ok(license);
    }

    @GetMapping
    public ResponseEntity<Iterable<License>> getAllLicenses() {
        Iterable<License> licenses = licenseService.findAllLicenses();
        return ResponseEntity.ok(licenses);
    }

    @PostMapping
    public ResponseEntity<String> createLicense(@RequestBody License request){
        licenseService.createLicense(request);
        return ResponseEntity.ok("Добавление лицензии прошло успешно");

    }

    @PutMapping(value="/{licenseId}")
    public ResponseEntity<String> updateLicense(@PathVariable("licenseId") int licenseId, @RequestBody License request){
        licenseService.updateLicense(licenseId, request);
        return ResponseEntity.ok("Редактирование лицензии прошло успешно");

    }

    @DeleteMapping(value="/{licenseId}")
    public ResponseEntity<String> deleteLicense(@PathVariable("licenseId") int licenseId){
        licenseService.deleteLicense(licenseId);
        return ResponseEntity.ok("Удаление лицензии прошло успешно");
    }
}
