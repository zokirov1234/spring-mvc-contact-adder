package com.example.contact_adder.repository.mapper;

import com.example.contact_adder.model.entity.Category;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryMapper implements RowMapper<Category> {
    @Override
    public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Category(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getInt("user_id")
        );
    }
}
