package com.example.contact_adder.repository;

import com.example.contact_adder.model.dto.UserReceiveDto;
import com.example.contact_adder.model.entity.UserEntity;
import com.example.contact_adder.repository.mapper.UserMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository{

    private final JdbcTemplate jdbcTemplate;

    public UserRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(UserReceiveDto userReceiveDto) {
        return jdbcTemplate.update(
                "insert into userss (name, password, username) VALUES (?,?,?)",
                userReceiveDto.getName(), userReceiveDto.getPassword(), userReceiveDto.getUsername()
        );
    }

    @Override
    public Optional<UserEntity> findByUsername(String username) {
        return jdbcTemplate.query("select * from userss where username = ? ", new UserMapper(), username).stream().findFirst();
    }
}
