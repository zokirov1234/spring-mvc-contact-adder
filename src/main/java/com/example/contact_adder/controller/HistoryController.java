package com.example.contact_adder.controller;

import com.example.contact_adder.service.HisotryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.example.contact_adder.general.Usage.currentUser;

@Controller
@RequestMapping("/history")
public class HistoryController {

    private final HisotryService hisotryService;

    public HistoryController(HisotryService hisotryService) {
        this.hisotryService = hisotryService;
    }

    @GetMapping("/contact/{id}")
    public String contactHistory(
            @PathVariable("id") int contactId,
            Model model
    ) {
        model.addAttribute("historyList", hisotryService.getHistoryList(currentUser.getId(), contactId));
        return "history";
    }

    @GetMapping("/list")
    public String listHistory(
            Model model
    ) {
        model.addAttribute("historyList", hisotryService.getAllHistory(currentUser.getId()));
        return "history";
    }
}
