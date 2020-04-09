package com.service.recipe.service.impl;

import com.service.recipe.model.User;
import com.service.recipe.repo.UserRepo;
import com.service.recipe.service.UserService;
import com.service.recipe.utility.EmailConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

//    @Autowired
//    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private EmailConstructor emailConstructor;

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public User saveUser(User user) {
//        String password = RandomStringUtils.randomAlphabetic(10);
//        String encryptedPassword = bCryptPasswordEncoder.encode(password);
//        user.setPassword(encryptedPassword);
        userRepo.save(user);
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
//        String password = user.getPassword();
//        String encryptedPassword = bCryptPasswordEncoder.encode(password);
//        user.setPassword(encryptedPassword);
//        userRepo.save(user);
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
}
