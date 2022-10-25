package com.example.contact_adder.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class HistoryReceiveDto {
    private int contactId;
    private int categoryId;
    private Timestamp createdAt;
    private String newValue;
    private String oldValue;
    private String updatedField;
    private int userId;
}
