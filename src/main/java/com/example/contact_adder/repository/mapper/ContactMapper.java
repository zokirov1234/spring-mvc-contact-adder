package com.example.contact_adder.repository.mapper;

import com.example.contact_adder.model.entity.Contact;
import com.example.contact_adder.model.entity.UserEntity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ContactMapper implements RowMapper<Contact> {
    @Override
    public Contact mapRow(ResultSet rs, int rowNum) throws SQLException {
        Contact contact = new Contact();
        contact.setId(rs.getInt("id"));
        contact.setName(rs.getString("name"));
        contact.setPhoneNumber(rs.getString("phone_number"));
        contact.setActive(rs.getBoolean("is_active"));
        contact.setCreatedAt(rs.getTimestamp("created_at"));
        contact.setUserId(rs.getInt("user_id"));
        contact.setCategoryId(rs.getInt("category_id"));

        return contact;
    }
}
