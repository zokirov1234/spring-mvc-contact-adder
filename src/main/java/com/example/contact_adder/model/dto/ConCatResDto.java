package com.example.contact_adder.model.dto;

import com.example.contact_adder.model.entity.Category;
import com.example.contact_adder.model.entity.Contact;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConCatResDto {

    private List<Contact> contacts;
    private List<Category> categories;
}
