package com.example.contact_adder.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HistoryResDto {

    private String updatedField;
    private String oldValue;
    private String newValue;
    private Timestamp changedOn;
    private Timestamp createdAt;
}
