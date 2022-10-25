package com.example.contact_adder.repository;

import com.example.contact_adder.model.dto.UserReceiveDto;
import com.example.contact_adder.model.entity.UserEntity;

import java.util.Optional;

public interface UserRepository {

    int save(UserReceiveDto userReceiveDto);

    Optional<UserEntity> findByUsername(String username);
}
