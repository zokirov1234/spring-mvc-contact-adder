package com.example.contact_adder;

import com.example.contact_adder.model.dto.CategoryReceiveDto;
import com.example.contact_adder.model.dto.ContactReceiveDto;
import com.example.contact_adder.model.dto.UserReceiveDto;
import com.example.contact_adder.repository.ContactRepository;
import com.example.contact_adder.repository.HistoryContact;
import com.example.contact_adder.repository.UserRepository;
import com.example.contact_adder.service.CategoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class ContactAdderApplication implements CommandLineRunner {

    private final UserRepository userRepository;
    private final CategoryService categoryService;
    private final ContactRepository contactRepository;
    private final PasswordEncoder passwordEncoder;
    private final HistoryContact historyContact;

    public ContactAdderApplication(UserRepository userRepository, CategoryService categoryService, ContactRepository contactRepository, PasswordEncoder passwordEncoder, HistoryContact historyContact) {
        this.userRepository = userRepository;
        this.categoryService = categoryService;
        this.contactRepository = contactRepository;
        this.passwordEncoder = passwordEncoder;
        this.historyContact = historyContact;
    }

    public static void main(String[] args) {
        SpringApplication.run(ContactAdderApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        UserReceiveDto userReceiveDto = new UserReceiveDto(
//                "alex",
//                "alex",
//                passwordEncoder.encode("123")
//        );
//        UserReceiveDto userReceiveDto1 = new UserReceiveDto(
//                "max",
//                "max",
//                passwordEncoder.encode("123")
//        );
//        userRepository.save(userReceiveDto);
//        userRepository.save(userReceiveDto1);
//        categoryService.addCategory(new CategoryReceiveDto("Friend",1));
//        contactRepository.save(new ContactReceiveDto("dad","905145187",1,1));
        historyContact.createTriggerFunction();
    }
}
