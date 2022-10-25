package com.example.contact_adder.service;

import com.example.contact_adder.model.dto.CategoryReceiveDto;
import com.example.contact_adder.model.entity.Category;
import com.example.contact_adder.repository.CategoryRepository;
import com.example.contact_adder.repository.ContactRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final ContactRepository contactRepository;


    public CategoryService(CategoryRepository categoryRepository, ContactRepository contactRepository) {
        this.categoryRepository = categoryRepository;

        this.contactRepository = contactRepository;
    }

    public List<Category> getListByUser(int userId) {
        return categoryRepository.findByUserId(userId);
    }

    public boolean addCategory(CategoryReceiveDto categoryReceiveDto) {

        if (categoryRepository.findByName(categoryReceiveDto).isPresent()) {
            System.out.println("There is category found");
            return false;
        }
        return categoryRepository.save(categoryReceiveDto) > 0;
    }

    public Optional<Category> findByName(CategoryReceiveDto categoryReceiveDto) {
        return categoryRepository.findByName(categoryReceiveDto);
    }

    public boolean deleteById(int categoryId) {

        return categoryRepository.deleteById(categoryId) > 0;
    }

    public boolean updateByName(int id, String name, int userId) {
        Optional<Category> category
                = categoryRepository.findByName(new CategoryReceiveDto(name, userId));

        if (category.isPresent()) {
            return category.get().getId() == id;
        }
        categoryRepository.save(new CategoryReceiveDto(name, userId));
        Optional<Category> categoryRepositoryByName
                = categoryRepository.findByName(new CategoryReceiveDto(name, userId));
        contactRepository.updateByCategoryName(id, categoryRepositoryByName.get().getId());
        categoryRepository.deleteById(id);
        return true;
    }
}
