package com.example.contact_adder.repository.mapper;

import com.example.contact_adder.model.entity.History;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class HistoryMapper implements RowMapper<History> {
    @Override
    public History mapRow(ResultSet rs, int rowNum) throws SQLException {
        History history = new History();
        history.setCategoryId(rs.getInt("category_id"));
        history.setChangedOn(rs.getTimestamp("changed_on"));
        history.setContactId(rs.getInt("contact_id"));
        history.setId(rs.getInt("id"));
        history.setNewValue(rs.getString("new_value"));
        history.setOldValue(rs.getString("old_value"));
        history.setUpdatedField(rs.getString("updated_field"));
        history.setUserId(rs.getInt("user_id"));
        history.setCreatedAt(rs.getTimestamp("created_at"));
        return history;
    }
}
