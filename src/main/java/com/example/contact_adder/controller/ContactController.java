package com.example.contact_adder.controller;

import com.example.contact_adder.model.dto.CategoryReceiveDto;
import com.example.contact_adder.model.dto.ContactRequestDto;
import com.example.contact_adder.model.entity.UserEntity;
import com.example.contact_adder.service.CategoryService;
import com.example.contact_adder.service.ContactService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import static com.example.contact_adder.general.Usage.currentUser;

@Controller
public class ContactController {
    private static boolean isAdded = true;

    private final ContactService contactService;
    private final CategoryService categoryService;

    public ContactController(ContactService contactService, CategoryService categoryService) {
        this.contactService = contactService;
        this.categoryService = categoryService;
    }

    @GetMapping("/")
    public String home(
            @AuthenticationPrincipal UserEntity userEntity,
            Model model
    ) {
        currentUser = userEntity;
        if (isAdded) {
            categoryService.addCategory(new CategoryReceiveDto(" ", currentUser.getId()));
            isAdded = false;
        }
        model.addAttribute("contactList", contactService.getContactList(userEntity.getId()));
        return "home";
    }

    @PostMapping("/contact/add")
    public String addContact(
            @ModelAttribute ContactRequestDto contactRequestDto,
            Model model
    ) {
        contactService.save(contactRequestDto, currentUser.getId());

        model.addAttribute("contactList", contactService.getContactList(currentUser.getId()));
        return "home";
    }

    @GetMapping("/contact/delete/{id}")
    public String deleteContact(
            @PathVariable("id") int id,
            Model model
    ) {
        contactService.delete(id);
        model.addAttribute("contactList", contactService.getContactList(currentUser.getId()));
        return "home";
    }

    @GetMapping("/contact/update/{id}")
    public String updateContact(
            @PathVariable("id") int id,
            @ModelAttribute ContactRequestDto contactRequestDto,
            Model model
    ) {
        contactService.update(contactRequestDto, currentUser.getId(), id);
        model.addAttribute("contactList", contactService.getContactList(currentUser.getId()));
        return "home";
    }

    @GetMapping("/search")
    public String getSearch(
            @ModelAttribute("search") String searchObject,
            Model model
    ) {
        model.addAttribute("contactList", contactService.searchContact(currentUser.getId(), searchObject));
        return "home";
    }

}
