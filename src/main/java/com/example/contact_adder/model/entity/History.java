package com.example.contact_adder.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "history_contact")
public class History {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "updated_field")
    private String updatedField;

    @Column(name = "old_value")
    private String oldValue;

    @Column(name = "new_value")
    private String newValue;

    @Column(name = "changed_on")
    private Timestamp changedOn;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "contact_id")
    private int contactId;

    @Column(name = "category_id")
    private int categoryId;

    @Column(name = "user_id")
    private int userId;

}
