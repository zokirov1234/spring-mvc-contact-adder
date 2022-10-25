package com.example.contact_adder.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactReceiveDto {

    private String name;
    private String phoneNumber;
    private int userId;
    private int categoryId;
}
