package com.example.contact_adder;

import com.example.contact_adder.repository.HistoryContact;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ContactAdderApplication implements CommandLineRunner {

    private final HistoryContact historyContact;

    public ContactAdderApplication(HistoryContact historyContact) {
        this.historyContact = historyContact;
    }

    public static void main(String[] args) {
        SpringApplication.run(ContactAdderApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        historyContact.createTriggerFunction();
    }
}
