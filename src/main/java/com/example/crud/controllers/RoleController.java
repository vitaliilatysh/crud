package com.example.crud.controllers;

import com.example.crud.models.Role;
import com.example.crud.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoleController extends BaseController {

    @Autowired
    private RoleRepository roleService;

    @GetMapping("/roles")
    public Iterable<Role> showAllRoles() {
        return roleService.findAll();
    }

}
