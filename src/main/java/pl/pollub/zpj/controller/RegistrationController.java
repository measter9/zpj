package pl.pollub.zpj.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.pollub.zpj.DTOs.RegistrationRequest;
import pl.pollub.zpj.models.User;
import pl.pollub.zpj.repository.UserHibernateRepository;

@RestController
public class RegistrationController{
    @Autowired
    private UserHibernateRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/api/signup")
    public User createUser(@RequestBody RegistrationRequest user){

        return userRepository.save(new User(user.getUsername(),user.getEmail(),passwordEncoder.encode(user.getPassword())));
    }
}
