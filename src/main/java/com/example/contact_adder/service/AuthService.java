package com.example.contact_adder.service;

import com.example.contact_adder.model.entity.UserEntity;
import com.example.contact_adder.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService implements UserDetailsService {

    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> byUsername = userRepository.findByUsername(username);

        if(byUsername.isEmpty()) {
            throw new UsernameNotFoundException(username + " not found");
        }

        return byUsername.get();
    }
}
