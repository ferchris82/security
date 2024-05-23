package chrisferdev.security.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import chrisferdev.security.persistence.entities.UserEntity;
import chrisferdev.security.services.IUserService;

@RestController
@RequestMapping("/user")
public class UserControllers {

    @Autowired
    IUserService userService;

    @GetMapping("/find-all")
    private ResponseEntity<List<UserEntity>> getAllUsers(){
        return new ResponseEntity<>(userService.findAllUsers(), HttpStatus.OK);
    } 
}
