package spring.apps.contactsApp.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import spring.apps.contactsApp.repository.ContactRepository;
import spring.apps.contactsApp.service.ContactService;

@Controller
@RequestMapping("/contacts")
@Slf4j
public class ContactController {
    @Autowired  private ContactService contactService;

    @GetMapping
    public String listContacts(Model model){
        log.debug("Calling ContactsController -> listContacts");
        model.addAttribute("contacts", )
    }
}
