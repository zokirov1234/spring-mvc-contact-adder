package com.example.contact_adder.repository;

import com.example.contact_adder.model.dto.ContactReceiveDto;
import com.example.contact_adder.model.entity.Contact;
import com.example.contact_adder.model.entity.History;
import com.example.contact_adder.repository.mapper.ContactMapper;
import com.example.contact_adder.repository.mapper.HistoryMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ContactRepositoryImpl implements ContactRepository {

    private final JdbcTemplate jdbcTemplate;


    public ContactRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Contact> findByUserId(int userId) {
        return jdbcTemplate.query("select * from contact where user_id = ? and is_active = true", new ContactMapper(), userId);
    }

    @Override
    public int save(ContactReceiveDto contactReceiveDto) {
        return jdbcTemplate.update(
                "insert into contact (name, phone_number,category_id,user_id) VALUES (?,?,?,?)",
                contactReceiveDto.getName(), contactReceiveDto.getPhoneNumber(), contactReceiveDto.getCategoryId(), contactReceiveDto.getUserId()
        );
    }

    @Override
    public Optional<Contact> findById(int contactId) {
        return jdbcTemplate.query("select * from contact where id = ?", new ContactMapper(), contactId).stream().findFirst();
    }

    @Override
    public int deleteById(int id) {
        return jdbcTemplate.update("update contact set is_active = false where id = ?", id);
    }

    @Override
    public int updateById(ContactReceiveDto contactReceiveDto, int id) {
        return jdbcTemplate.update("update contact set name = ? , phone_number = ?, category_id = ? where id = ?",
                contactReceiveDto.getName(), contactReceiveDto.getPhoneNumber(),
                contactReceiveDto.getCategoryId(), id);
    }

    @Override
    public Optional<Contact> findByPhoneNumber(int userId, String phNumber) {
        return jdbcTemplate.query("select * from contact where user_id = ? and phone_number = ?", new ContactMapper(),
                userId, phNumber).stream().findFirst();
    }

    @Override
    public List<Contact> findByNameOrNumber(String search, int userId) {
        String searched = "%" + search + "%";
        return jdbcTemplate.query("select * from contact where user_id = ? and (name like ? or phone_number like ?) and is_active= true", new ContactMapper(),
                userId, searched, searched);
    }

    @Override
    public List<Contact> findByCategoryName(int userId, int categoryId) {
        return jdbcTemplate.query("select * from contact where user_id = ? and is_active = true and category_id = ?", new ContactMapper(), userId, categoryId);
    }

    @Override
    public int updateDeletedContact(int contactId, String name, int categoryId) {
        return jdbcTemplate.update("update contact set name = ? , category_id = ?, is_active = true where id = ?",
                name, categoryId,contactId);
    }

    @Override
    public int updateByCategoryId(int categoryId) {
        return jdbcTemplate.update("update contact set category_id = 1 where category_id = ?", categoryId);
    }

    @Override
    public int updateByCategoryName(int oldId, int newId) {
        return jdbcTemplate.update("update contact set category_id = ? where category_id = ?", newId, oldId);
    }
}
