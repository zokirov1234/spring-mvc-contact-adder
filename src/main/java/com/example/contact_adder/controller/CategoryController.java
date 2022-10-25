package com.example.contact_adder.controller;

import com.example.contact_adder.model.dto.CategoryReceiveDto;
import com.example.contact_adder.service.CategoryService;
import com.example.contact_adder.service.ContactService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import static com.example.contact_adder.general.Usage.currentUser;

@Controller
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;
    private final ContactService contactService;

    public CategoryController(CategoryService categoryService, ContactService contactService) {
        this.categoryService = categoryService;
        this.contactService = contactService;
    }

    @PostMapping("/add")
    public String addCategory(
            @ModelAttribute("categoryName") String categoryName,
            Model model
    ) {
        categoryService.addCategory(new CategoryReceiveDto(categoryName, currentUser.getId()));

        model.addAttribute("contactList", contactService.getContactList(currentUser.getId()));

        return "home";
    }

    @GetMapping("/filter/{id}")
    public String categoryFilter(
            @PathVariable("id") int categoryId,
            @ModelAttribute("categoryName") String name,
            Model model
    ) {
        model.addAttribute("contactList", contactService.filterList(currentUser.getId(), categoryId));
        return "home";
    }

    @GetMapping("/delete/{id}")
    public String deleteCategory(
            @PathVariable("id") int categoryId,
            Model model
    ){
        contactService.setByCategoryId(categoryId);
        categoryService.deleteById(categoryId);
        model.addAttribute("contactList", contactService.getContactList(currentUser.getId()));
        return "home";
    }

    @GetMapping("/update/{id}")
    public String updateCategory(
            @PathVariable("id") int categoryId,
            @ModelAttribute("categoryName") String name,
            Model model
    ){
        categoryService.updateByName(categoryId, name, currentUser.getId());
        model.addAttribute("contactList", contactService.getContactList(currentUser.getId()));

        return "home";
    }
}
