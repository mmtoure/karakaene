package com.mmt.karakaene.controller;

import com.mmt.karakaene.dto.AuthenticationDTO;
import com.mmt.karakaene.model.User;
import com.mmt.karakaene.security.JwtService;
import com.mmt.karakaene.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @PostMapping(path = "register")
    public ResponseEntity<User> register(@RequestBody User user){
        log.info("Register message");
        User newUser =this.userService.createUser(user);
        return new ResponseEntity<>(newUser,HttpStatus.CREATED);
    }

    @PostMapping(path = "activation")
    public void activation(@RequestBody Map<String, String> activation){
        this.userService.confirmationCode(activation);
    }

    @PostMapping(path = "login")
    public Map<String,String> login(@RequestBody AuthenticationDTO authenticationDTO){
        final Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationDTO.username(),authenticationDTO.password()));
        if (authentication.isAuthenticated()){
            return jwtService.generate(authenticationDTO.username());
        }
        return null;
    }

    @PostMapping("api/users")
    public ResponseEntity<User> create(@RequestBody User user){
        userService.createUser(user);
        return new ResponseEntity<>(user,HttpStatus.CREATED);
    }

    @GetMapping(path = "users")
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);

    }

    @GetMapping(path = "users/{id}")
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
