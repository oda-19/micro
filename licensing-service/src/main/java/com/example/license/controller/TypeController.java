package com.example.license.controller;

import com.example.license.model.Type;
import com.example.license.service.TypeService;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/types")
public class TypeController {
    @Autowired
    private TypeService typeService;

    //@PreAuthorize("hasRole('ADMIN') OR hasRole('USER')")
//    @RequestMapping(value="/{typeId}",method = RequestMethod.GET)
//    public ResponseEntity<Type> getType(@PathVariable("typeId") int typeId){
//        Type type = typeService.getType(typeId);
//        return ResponseEntity.ok(type);
//    }

    //@PreAuthorize("hasRole('ADMIN') OR hasRole('USER')")
//    @GetMapping
//    public ResponseEntity<Iterable<Type>> getAllTypes() {
//        Iterable<Type> types = typeService.findAllTypes();
//        return ResponseEntity.ok(types);
//    }

//    @PreAuthorize("hasRole('ADMIN') OR hasRole('USER')")
    @GetMapping
    public String getAllTypes(Model model) {
        Iterable<Type> types = typeService.findAllTypes();
        model.addAttribute("types", types);
        return "types"; // Имя HTML view, которое должен отрендерить Thymeleaf
    }

    //@PreAuthorize("hasRole('ADMIN')")
//    @PostMapping
//    public ResponseEntity<String> createType(@RequestBody Type request){
//        typeService.createType(request);
//        return ResponseEntity.ok("Добавление типа лицензии прошло успешно");
//
//    }

    //@PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value="/create")
    public String showCreateForm(Model model) {
        model.addAttribute("type", new Type()); // Добавление нового экземпляра Type в модель
        return "create_type";
    }

    //@PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = "/create")
    public String createType(@ModelAttribute Type request, Model model) {
        typeService.createType(request);
        model.addAttribute("message", "Добавление типа лицензии прошло успешно");
        return "redirect:/types"; // Перенаправление обратно к списку типов
    }

    //@PreAuthorize("hasRole('ADMIN')")
//    @PutMapping(value="/update/{typeId}")
//    public ResponseEntity<String> updateType(@PathVariable("typeId") int typeId, @RequestBody Type request){
//        typeService.updateType(typeId, request);
//        return ResponseEntity.ok("Редактирование типа лицензии прошло успешно");
//
//    }

    //@PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value="/update/{typeId}/")
    public String showUpdateForm(@PathVariable("typeId") int typeId, Model model) {
        Type type = typeService.getType(typeId);
        if (type != null) {
            model.addAttribute("type", type);
            return "update_type"; // Имя HTML view с формой для редактирования
        } else {
            model.addAttribute("message", "Тип лицензии с таким ID не найден.");
            return "redirect:/types"; // Перенаправление обратно к списку типов, если тип не найден
        }
    }

    //@PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value="/update/{typeId}/")
    public String updateType(@PathVariable("typeId") int typeId, @ModelAttribute Type request, Model model) {
        boolean updated = typeService.updateType(typeId, request);
        if (updated) {
            model.addAttribute("message", "Редактирование типа лицензии прошло успешно");
        } else {
            model.addAttribute("message", "Ошибка при редактировании типа лицензии");
        }
        return "redirect:/types"; // Перенаправление обратно к списку типов
    }

    //@PreAuthorize("hasRole('ADMIN')")
//    @DeleteMapping(value="/delete/{typeId}")
//    public ResponseEntity<String> deleteType(@PathVariable("typeId") int typeId){
//        typeService.deleteType(typeId);
//        return ResponseEntity.ok("Удаление типа лицензии прошло успешно");
//    }

    //@PreAuthorize("hasRole('ADMIN')")
    @GetMapping(value="/delete/{typeId}")
    public String deleteTypeConfirmation(@PathVariable("typeId") int typeId, ModelMap model) {
        Type type = typeService.getType(typeId);
        model.addAttribute("type", type);
        return "delete_type :: delete-type";
    }

    //@PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value="/delete/{typeId}")
    public ModelAndView deleteType(@PathVariable("typeId") int typeId, ModelMap model) {
        Type type = typeService.getType(typeId);
        typeService.deleteType(type);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/types");
        modelAndView.addObject("types", typeService.findAllTypes());
        return modelAndView;
    }
}
