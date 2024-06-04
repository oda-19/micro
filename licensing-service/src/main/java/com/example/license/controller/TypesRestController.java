package com.example.license.controller;

import com.example.license.model.Type;
import com.example.license.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/types")
public class TypesRestController {
    @Autowired
    private TypeService typeService;


}
