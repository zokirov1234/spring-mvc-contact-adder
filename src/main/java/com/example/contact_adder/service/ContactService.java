package com.example.contact_adder.service;

import com.example.contact_adder.model.dto.*;
import com.example.contact_adder.model.entity.Category;
import com.example.contact_adder.model.entity.Contact;
import com.example.contact_adder.repository.ContactRepository;
import com.example.contact_adder.repository.HistoryContact;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactService {

    private final ContactRepository contactRepository;
    private final CategoryService categoryService;
    private final HistoryContact historyContact;

    public ContactService(ContactRepository contactRepository, CategoryService categoryService, HistoryContact historyContact) {
        this.contactRepository = contactRepository;
        this.categoryService = categoryService;
        this.historyContact = historyContact;
    }

    public ConCatResDto getContactList(int userId) {
        List<Contact> contacts = contactRepository.findByUserId(userId);
        List<Category> categories = categoryService.getListByUser(userId);

        return new ConCatResDto(
                contacts,
                categories
        );
    }

    public boolean save(ContactRequestDto contactRequestDto, int userId) {
        Optional<Category> checkCategory
                = categoryService.findByName(new CategoryReceiveDto(contactRequestDto.getCategoryName(), userId));

        Optional<Contact> number
                = contactRepository.findByPhoneNumber(userId, contactRequestDto.getPhoneNumber());

        if (number.isEmpty()) {
            contactRepository.save(new ContactReceiveDto(
                    contactRequestDto.getName(), contactRequestDto.getPhoneNumber(),
                    userId, checkCategory.get().getId()
            ));
            return true;
        }
        if (!number.get().isActive()) {
            contactRepository.updateDeletedContact(number.get().getId(), contactRequestDto.getName(), checkCategory.get().getId());
            return true;
        } else {
            System.out.println("User found exception");
            return false;
        }
    }

    public boolean delete(int id) {
        return contactRepository.deleteById(id) > 0;
    }

    public boolean update(ContactRequestDto contactRequestDto, int userId, int contactId) {
        Optional<Category> category
                = categoryService.findByName(new CategoryReceiveDto(contactRequestDto.getCategoryName(), userId));
        Optional<Contact> number
                = contactRepository.findByPhoneNumber(userId, contactRequestDto.getPhoneNumber());
        Optional<Contact> ph = contactRepository.findById(contactId);

        if (number.isPresent()) {
            if (number.get().getId() == ph.get().getId()) {
                contactRepository.updateById(new ContactReceiveDto(
                        contactRequestDto.getName(), contactRequestDto.getPhoneNumber(),
                        userId, category.get().getId()), contactId);
                return true;
            } else {
                if (!number.get().isActive()) {
                    saveHistory(ph, contactRequestDto.getPhoneNumber());
                    contactRepository.updateDeletedContact(
                            number.get().getId(), contactRequestDto.getName(), category.get().getId());
                    contactRepository.deleteById(contactId);
                    return true;
                } else {
                    System.out.println("Something went wrong");
                    return false;
                }
            }
        } else {
            saveHistory(ph, contactRequestDto.getPhoneNumber());
            contactRepository.deleteById(contactId);
            save(contactRequestDto, userId);
            return true;
        }

    }

    private void saveHistory(Optional<Contact> ph,String newPhoneNumber) {
        historyContact.save(
                new HistoryReceiveDto(
                        ph.get().getId(),ph.get().getCategoryId(),ph.get().getCreatedAt(), newPhoneNumber,
                        ph.get().getPhoneNumber(), "Phone number", ph.get().getUserId()
                )
        );
    }

    public ConCatResDto filterList(int userId, int categoryId) {
        List<Contact> contacts = contactRepository.findByCategoryName(userId, categoryId);
        List<Category> categories = categoryService.getListByUser(userId);
        return new ConCatResDto(
                contacts,
                categories
        );
    }

    public ConCatResDto searchContact(int userId, String searchObject) {
        List<Contact> contacts = contactRepository.findByNameOrNumber(searchObject, userId);
        List<Category> categories = categoryService.getListByUser(userId);
        return new ConCatResDto(
                contacts,
                categories
        );
    }

    public boolean setByCategoryId(int categoryId) {
        return contactRepository.updateByCategoryId(categoryId) >0;
    }
}
