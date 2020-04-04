package com.service.recipe.resource;

import com.service.recipe.model.User;
import com.service.recipe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/users")
public class UserResource {

//    @Autowired
//    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public ResponseEntity<?> getUsersList() {
        List<User> users = userService.userList();
        if (users.isEmpty()) {
            return new ResponseEntity<>("No users found", HttpStatus.OK);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/findByUserId/{id}")
    public ResponseEntity<?> getUserInfo(@PathVariable("id") Integer id) {
        User user = this.userService.findByUserId(id);
        if (user == null) {
            return new ResponseEntity<>("User with id not found", HttpStatus.OK);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User requestCreateBody) {
        String username = requestCreateBody.getName();
        if (userService.findByUserName(username) != null) {
            return new ResponseEntity<>("Username already registered", HttpStatus.CONFLICT);
        }
        String email = requestCreateBody.getEmail();
        if (userService.findByUserEmail(email) != null) {
            return new ResponseEntity<>("Email already registered", HttpStatus.CONFLICT);
        }
        try {
            User newUser = userService.saveUser(requestCreateBody);
            return new ResponseEntity<>(newUser, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occured during saving", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/update")
    public ResponseEntity<?> update(@RequestBody User requestUpdateBody) {
        Integer id = requestUpdateBody.getId();
        User user = userService.findByUserId(id);
        if (user == null) {
            return new ResponseEntity<>("userNotFound", HttpStatus.NOT_FOUND);
        }
        try {
            userService.updateUser(user);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occured", HttpStatus.BAD_REQUEST);
        }
    }

//    @PostMapping("/changePassword")
//    public ResponseEntity<?> changePassword(@RequestBody HashMap<String, String> request) {
//        String username = request.get("username");
//        User user = userService.findByUserName(username);
//        if (user == null) {
//            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
//        }
//        String currentPassword = request.get("currentPassword");
//        String newPassword = request.get("newPassword");
//        String confirmPassword = request.get("confirmedPassword");
//        if (!newPassword.equals(confirmPassword)) {
//            return new ResponseEntity<>("Password not matched", HttpStatus.BAD_REQUEST);
//        }
//        String userPassword = user.getPassword();
//        try {
//            if (!newPassword.isEmpty() && !StringUtils.isEmpty(newPassword)) {
//                if (bCryptPasswordEncoder.matches(currentPassword, userPassword)) {
//                    /*userService.updateUserPassword - dopsat */
//                } else {
//                    return new ResponseEntity<>("Incorrect current password", HttpStatus.BAD_REQUEST);
//                }
//            }
//        } catch (Exception e) {
//            return new ResponseEntity<>("Error occured", HttpStatus.BAD_REQUEST);
//        }
//        return new ResponseEntity<>("Password changed successfully", HttpStatus.OK);
//    }
}
