package com.example.contact_adder.repository;

import com.example.contact_adder.model.dto.ContactReceiveDto;
import com.example.contact_adder.model.entity.Contact;
import com.example.contact_adder.model.entity.History;

import java.util.List;
import java.util.Optional;

public interface ContactRepository {

    List<Contact> findByUserId(int userId);

    Optional<Contact> findById(int contactId);

    int save(ContactReceiveDto contactReceiveDto);

    int deleteById(int id);

    List<Contact> findByNameOrNumber(String search, int userId);

    Optional<Contact> findByPhoneNumber(int userId, String phNumber);

    int updateDeletedContact(int contactId, String name, int categoryId);

    int updateById(ContactReceiveDto contactReceiveDto, int id);

    List<Contact> findByCategoryName(int userId, int categoryId);

    int updateByCategoryId(int categoryId);

    int updateByCategoryName(int oldId,int newId);

}
