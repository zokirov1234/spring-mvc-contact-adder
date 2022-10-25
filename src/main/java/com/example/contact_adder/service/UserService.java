package com.example.contact_adder.service;

import com.example.contact_adder.model.dto.UserReceiveDto;
import com.example.contact_adder.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean save(UserReceiveDto userReceiveDto) {
        try{
            userReceiveDto.setPassword(passwordEncoder.encode(userReceiveDto.getPassword()));
            return userRepository.save(userReceiveDto) >0;
        } catch (Exception e) {
            System.out.println("User found exception");
        }
        return false;
    }
}
