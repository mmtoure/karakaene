package com.mmt.karakaene.controller;

import com.mmt.karakaene.model.User;
import com.mmt.karakaene.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("api/users")
    public ResponseEntity<User> create(@RequestBody User user){
        userService.createUser(user);
        return new ResponseEntity<>(user,HttpStatus.CREATED);
    }

    @GetMapping("api/users")
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);

    }

    @GetMapping("api/users/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) throws Exception {
        User user = userService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("api/users/{id}")
    public ResponseEntity<User> update(@RequestBody User user, @PathVariable Long id) throws Exception {
        User existingUser = userService.updateUser(user,id);
        return new ResponseEntity<>(existingUser, HttpStatus.OK);
    }

    @DeleteMapping("api/users/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) throws Exception {
        userService.deleteUser(id);
        return new ResponseEntity<>("User deleted successfully", HttpStatus.NO_CONTENT);
    }
}
