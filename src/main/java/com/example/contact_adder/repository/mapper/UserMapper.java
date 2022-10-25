package com.example.contact_adder.repository.mapper;

import com.example.contact_adder.model.entity.UserEntity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<UserEntity> {
    @Override
    public UserEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(rs.getInt("id"));
        userEntity.setName(rs.getString("name"));
        userEntity.setUsername(rs.getString("username"));
        userEntity.setPassword(rs.getString("password"));
        return userEntity;
    }
}
