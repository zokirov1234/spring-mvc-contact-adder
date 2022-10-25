package com.example.contact_adder.repository;

import com.example.contact_adder.model.dto.CategoryReceiveDto;
import com.example.contact_adder.model.entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository {

    int save(CategoryReceiveDto categoryReceiveDto);

    Optional<Category> findById(int categoryId);

    int deleteById(int categoryId);

    int updateById(String newName, int categoryId);

    List<Category> findByUserId(int userId);

    Optional<Category> findByName(CategoryReceiveDto categoryReceiveDto);

}
