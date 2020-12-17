package com.git.project.springunittesting.Controller;

import com.git.project.springunittesting.Model.User;
import com.git.project.springunittesting.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/")
    public ResponseEntity<String> home(){
        return new ResponseEntity<>("hello User", HttpStatus.OK);
    }

    @PostMapping(value = "/addUser", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> addUser(@RequestBody User user){
        if(user.getName().equals("") || user.getPosition().equals("") || user.getAddress().equals("")){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        userService.addUser(user);
        return new ResponseEntity<>("Created", HttpStatus.CREATED);
    }

    @GetMapping("getAll")
    public List<User> getAll(){
        return userService.getAll();
    }

    @GetMapping("/user/{id}")
    public Optional<User> findById(@PathVariable("id") int id){
        return userService.getUserByID(id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable("id") int id){
        userService.deleteById(id);
    }

    @DeleteMapping("/deleteAll")
    public void  deleteAll(){
        userService.deleteAll();
    }
}
