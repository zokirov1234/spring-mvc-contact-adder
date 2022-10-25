package com.example.contact_adder.repository;

import com.example.contact_adder.model.dto.HistoryReceiveDto;
import com.example.contact_adder.model.entity.History;
import com.example.contact_adder.repository.mapper.HistoryMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;



@Repository
public class HistoryContact {
    private static final String updateNameFunction = ""
            + " CREATE OR REPLACE FUNCTION log_name_changes() "
            + " RETURNS TRIGGER "
            + " LANGUAGE PLPGSQL AS $$ "
            + " BEGIN "
            + " IF NEW.name <> OLD.name THEN "
            + " INSERT INTO history_contact(updated_field, old_value , new_value, changed_on, contact_id, category_id, user_id, created_at) "
            + " VALUES ('name', OLD.name, NEW.name, now(), OLD.id, OLD.category_id, OLD.user_id, OlD.created_at); "
            + " END IF;"
            + " RETURN NEW;"
            + " END; "
            + " $$";

    private static final String triggerOnName = ""
            + " CREATE TRIGGER name_changes "
            + " BEFORE UPDATE ON contact "
            + " FOR EACH ROW "
            + " EXECUTE PROCEDURE log_name_changes();";

    private static final String updatePhoneFunction = ""
            + " CREATE OR REPLACE FUNCTION log_phone_changes() "
            + " RETURNS TRIGGER "
            + " LANGUAGE PLPGSQL AS $$ "
            + " BEGIN "
            + " IF NEW.phone_number <> OLD.phone_number THEN "
            + " INSERT INTO history_contact(updated_field, old_value , new_value, changed_on, contact_id, category_id, user_id, created_at) "
            + " VALUES ('phone_number', OLD.phone_number, NEW.phone_number, now(), OLD.id, OLD.category_id, OLD.user_id,OLD.created_at ); "
            + " END IF;"
            + " RETURN NEW;"
            + " END; "
            + " $$";

    private static final String triggerOnPhone = ""
            + " CREATE TRIGGER phone_changes "
            + " BEFORE UPDATE ON contact "
            + " FOR EACH ROW "
            + " EXECUTE PROCEDURE log_phone_changes();";


    private final JdbcTemplate jdbcTemplate;

    public HistoryContact(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void createTriggerFunction() {
        jdbcTemplate.update(updateNameFunction);
        jdbcTemplate.update(triggerOnName);
        jdbcTemplate.update(updatePhoneFunction);
        jdbcTemplate.update(triggerOnPhone);
    }

    public List<History> getHistory(int userId, int contactId) {
        return jdbcTemplate.query("select * from history_contact where user_id = ? and contact_id = ?",
                new HistoryMapper(), userId, contactId);
    }

    public List<History> getAllHistory(int userId){
        return jdbcTemplate.query("select * from history_contact where user_id = ?",
                new HistoryMapper(), userId);
    }

    public int save(HistoryReceiveDto historyReceiveDto){
        Timestamp now = new Timestamp(System.currentTimeMillis());
        return jdbcTemplate.update(
                "insert into history_contact (category_id, changed_on, contact_id, created_at, new_value, old_value, updated_field, user_id) VALUES (?,?,?,?,?,?,?,?)",
                historyReceiveDto.getCategoryId(), now, historyReceiveDto.getContactId(),
                historyReceiveDto.getCreatedAt(), historyReceiveDto.getNewValue(), historyReceiveDto.getOldValue(),
                historyReceiveDto.getUpdatedField(),historyReceiveDto.getUserId()
        );
    }
}
