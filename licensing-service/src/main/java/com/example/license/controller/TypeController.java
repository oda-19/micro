package com.example.license.controller;

import com.example.license.model.Type;
import com.example.license.service.TypeService;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/types")
public class TypeController {
    @Autowired
    private TypeService typeService;

    @PreAuthorize("hasRole('ADMIN') OR hasRole('USER')")
    @RequestMapping(value="/{typeId}",method = RequestMethod.GET)
    public ResponseEntity<Type> getType(@PathVariable("typeId") int typeId){
        Type type = typeService.getType(typeId);
        return ResponseEntity.ok(type);
    }

    @PreAuthorize("hasRole('ADMIN') OR hasRole('USER')")
    @GetMapping
    public ResponseEntity<Iterable<Type>> getAllTypes() {
        Iterable<Type> types = typeService.findAllTypes();
        return ResponseEntity.ok(types);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<String> createType(@RequestBody Type request){
        typeService.createType(request);
        return ResponseEntity.ok("Добавление типа лицензии прошло успешно");

    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(value="/{typeId}")
    public ResponseEntity<String> updateType(@PathVariable("typeId") int typeId, @RequestBody Type request){
        typeService.updateType(typeId, request);
        return ResponseEntity.ok("Редактирование типа лицензии прошло успешно");

    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(value="/{typeId}")
    public ResponseEntity<String> deleteType(@PathVariable("typeId") int typeId){
        typeService.deleteType(typeId);
        return ResponseEntity.ok("Удаление типа лицензии прошло успешно");
    }
}
