package com.example.contact_adder.service;

import com.example.contact_adder.model.dto.HistoryResDto;
import com.example.contact_adder.model.entity.History;
import com.example.contact_adder.repository.HistoryContact;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HisotryService {

    private final HistoryContact historyContact;

    public HisotryService(HistoryContact historyContact) {
        this.historyContact = historyContact;
    }

    public List<HistoryResDto> getHistoryList(int userId, int contactId) {
        List<History> history = historyContact.getHistory(userId, contactId);
        List<HistoryResDto> resDtoList = new ArrayList<>();
        for (History history1 : history) {
            resDtoList.add(new HistoryResDto(
                    history1.getUpdatedField(),
                    history1.getOldValue(),
                    history1.getNewValue(),
                    history1.getChangedOn(),
                    history1.getCreatedAt()
            ));
        }
        return resDtoList;
    }

    public List<HistoryResDto> getAllHistory(int userId) {
        List<History> history = historyContact.getAllHistory(userId);
        List<HistoryResDto> resDtoList = new ArrayList<>();
        for (History history1 : history) {
            resDtoList.add(new HistoryResDto(
                    history1.getUpdatedField(),
                    history1.getOldValue(),
                    history1.getNewValue(),
                    history1.getChangedOn(),
                    history1.getCreatedAt()
            ));
        }
        return resDtoList;
    }
}
