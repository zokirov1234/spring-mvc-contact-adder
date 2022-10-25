package com.example.contact_adder.repository;

import com.example.contact_adder.model.dto.CategoryReceiveDto;
import com.example.contact_adder.model.entity.Category;
import com.example.contact_adder.repository.mapper.CategoryMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CategoryRepositoryImpl implements CategoryRepository {

    private final JdbcTemplate jdbcTemplate;

    public CategoryRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(CategoryReceiveDto categoryReceiveDto) {
        return jdbcTemplate.update(
                "insert into category (name, user_id) values (?,?);",
                categoryReceiveDto.getName(), categoryReceiveDto.getUserId()
        );
    }

    @Override
    public Optional<Category> findById(int categoryId) {
        return jdbcTemplate.query("select * from category where id = ?;",
                new CategoryMapper(), categoryId).stream().findFirst();
    }

    @Override
    public List<Category> findByUserId(int userId) {
        return jdbcTemplate.query("select * from category where user_id = ?", new CategoryMapper(), userId);
    }

    @Override
    public Optional<Category> findByName(CategoryReceiveDto categoryReceiveDto) {
        return jdbcTemplate.query(
                        "select * from category where name = ? and user_id=?", new CategoryMapper(),
                        categoryReceiveDto.getName(), categoryReceiveDto.getUserId())
                .stream().findFirst();
    }

    @Override
    public int deleteById(int categoryId) {
        return jdbcTemplate.update("delete from category where id = ?", categoryId);
    }

    @Override
    public int updateById(String newName, int categoryId) {
        return jdbcTemplate.update("update category set name = ? where id = ?;",
                categoryId);
    }


}
