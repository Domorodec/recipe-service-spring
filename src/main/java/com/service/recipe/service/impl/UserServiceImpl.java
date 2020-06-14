package com.service.recipe.service.impl;

import com.service.recipe.kafka.KafkaProducer;
import com.service.recipe.model.User;
import com.service.recipe.repo.UserRepo;
import com.service.recipe.service.UserService;
import com.service.recipe.utility.EmailConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private EmailConstructor emailConstructor;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private KafkaProducer kafkaProducer;

    @Override
    public User saveUser(User user) {
        String encryptedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
        userRepo.save(user);
        kafkaProducer.sendMessage("User " + user.getName() + " successfully registered.");
//        mailSender.send(emailConstructor.constructNewUserEmail(user, password));
        return user;
    }

    @Override
    public User findByUserId(Integer id) {
        return userRepo.findUserById(id);
    }

    @Override
    public List<User> userList() {
        return userRepo.findAll();
    }

    @Override
    public void updateUser(User user) {
        String password = user.getPassword();
        String encryptedPassword = bCryptPasswordEncoder.encode(password);
        user.setPassword(encryptedPassword);
        userRepo.save(user);
//        mailSender.send(emailConstructor.constructUpdateUserProfileEmail(user));
    }

    @Override
    public void deleteUser(Integer id) {
        userRepo.deleteById(id);
    }

    @Override
    public User findByUserName(String username) {
        return userRepo.findUserByName(username);
    }

    @Override
    public User findByUserEmail(String email) {
        return userRepo.findUserByEmail(email);
    }

    @Override
    public User login(String username, String password) {
        this.kafkaProducer.sendMessage("User " + username + " with password " + password + "is trying to login. Checking if exists in DB.");
        User userObject = userRepo.findUserByName(username);
        String userFromDB = userObject.getName();
        String passwordFromDB = userObject.getPassword();
        if (userFromDB.equals(username)) {
            this.kafkaProducer.sendMessage("User " + username + " exists");
            if (bCryptPasswordEncoder.matches(password, passwordFromDB)) {
                this.kafkaProducer.sendMessage("Password is correct");
                userObject.setPassword(bCryptPasswordEncoder.encode(password));
                return userObject;
            } else {
                this.kafkaProducer.sendMessage("Wrong password");
                return null;
            }
        }
        return null;
    }
}
