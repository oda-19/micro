package com.example.license.controller;

import com.example.license.model.License;
import com.example.license.model.Type;
import com.example.license.service.LicenseService;
import com.example.license.service.TypeService;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/licenses")
public class LicenseController {
    @Autowired
    private LicenseService licenseService;
    @Autowired
    private TypeService typeService;

    //@PreAuthorize("hasRole('ADMIN') OR hasRole('USER')")
//    @RequestMapping(value="/{licenseId}",method = RequestMethod.GET)
//    public ResponseEntity<License> getLicense(@PathVariable("licenseId") int licenseId){
//        License license = licenseService.getLicense(licenseId);
//        return ResponseEntity.ok(license);
//    }

//    //@PreAuthorize("hasRole('ADMIN') OR hasRole('USER')")
//    @GetMapping
//    public ResponseEntity<Iterable<License>> getAllLicenses() {
//        Iterable<License> licenses = licenseService.findAllLicenses();
//        return ResponseEntity.ok(licenses);
//    }

    //    @PreAuthorize("hasRole('ADMIN') OR hasRole('USER')")
    @GetMapping
    public String getAllLicenses(Model model, @Param("keyword") String keyword) {
        Iterable<License> licenses = licenseService.findAllLicenses(keyword);
        Iterable<Type> types = typeService.findAllTypes(keyword);
        model.addAttribute("licenses", licenses);
        model.addAttribute("types", types);
        model.addAttribute("keyword", keyword);
        return "licenses";
    }

//    //@PreAuthorize("hasRole('ADMIN')")
//    @PostMapping
//    public ResponseEntity<String> createLicense(@RequestBody License request){
//        licenseService.createLicense(request);
//        return ResponseEntity.ok("Добавление лицензии прошло успешно");
//
//    }

    //@PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value="/create")
    public String showCreateForm(Model model, @Param("keyword") String keyword) {
        model.addAttribute("license", new License());
        model.addAttribute("types", typeService.findAllTypes(keyword));
        model.addAttribute("keyword", keyword);
        return "create_license";
    }

    //@PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = "/create")
    public String createLicense(@ModelAttribute License request, Model model) {
        licenseService.createLicense(request);
        model.addAttribute("message", "Добавление лицензии прошло успешно");
        return "redirect:/licenses";
    }

//    //@PreAuthorize("hasRole('ADMIN')")
//    @PutMapping(value="/{licenseId}")
//    public ResponseEntity<String> updateLicense(@PathVariable("licenseId") int licenseId, @RequestBody License request){
//        licenseService.updateLicense(licenseId, request);
//        return ResponseEntity.ok("Редактирование лицензии прошло успешно");
//
//    }

    //@PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value="/update/{licenseId}/")
    public String showUpdateForm(@PathVariable("licenseId") int licenseId, Model model, @Param("keyword") String keyword) {
        License license = licenseService.getLicense(licenseId);
        //Iterable<Type> types = typeService.findAllTypes(keyword);
        if (license != null) {
            model.addAttribute("license", license);
            model.addAttribute("types", typeService.findAllTypes(keyword));
            model.addAttribute("keyword", keyword);
            return "update_license";
        } else {
            model.addAttribute("message", "Лицензия с таким ID не найдена");
            return "redirect:/licenses"; // Перенаправление обратно к списку типов, если тип не найден
        }
    }

    //@PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value="/update/{licenseId}/")
    public String updateLicense(@PathVariable("licenseId") int licenseId, @ModelAttribute License request, Model model) {
        boolean updated = licenseService.updateLicense(licenseId, request);
        if (updated) {
            model.addAttribute("message", "Редактирование лицензии прошло успешно");
        } else {
            model.addAttribute("message", "Ошибка при редактировании лицензии");
        }
        return "redirect:/licenses";
    }

//    //@PreAuthorize("hasRole('ADMIN')")
//    @DeleteMapping(value="/{licenseId}")
//    public ResponseEntity<String> deleteLicense(@PathVariable("licenseId") int licenseId){
//        licenseService.deleteLicense(licenseId);
//        return ResponseEntity.ok("Удаление лицензии прошло успешно");
//    }

    //@PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value="/delete/{licenseId}")
    public String deleteLicenseConfirmation(@PathVariable("licenseId") int licenseId, ModelMap model) {
        License license = licenseService.getLicense(licenseId);
        model.addAttribute("license", license);
        return "delete_license :: delete-license";
    }

    //@PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value="/delete/{licenseId}")
    public ModelAndView deleteLicense(@PathVariable("licenseId") int licenseId, ModelMap model, @Param("keyword") String keyword) {
        License license = licenseService.getLicense(licenseId);
        licenseService.deleteLicense(license);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/licenses");
        modelAndView.addObject("licenses", licenseService.findAllLicenses(keyword));
        return modelAndView;
    }
}
