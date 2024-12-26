package spring.apps.contactsApp.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import spring.apps.contactsApp.model.Contact;
import spring.apps.contactsApp.service.ContactService;

@Controller
@RequestMapping("/contacts")
@Slf4j
@RequiredArgsConstructor
public class ContactController {
    private final ContactService contactService;

    @GetMapping
    public String listContacts(Model model){
        log.debug("Calling ContactsController -> listContacts");
        model.addAttribute("contacts", contactService.findAll());
        return "contacts/list";
    }

    @GetMapping("/new")
    public String newContact(Model model){
        model.addAttribute("contact", new Contact());
        return "contacts/form";
    }
    @GetMapping("/edit/{id}")
    public String editContact(@PathVariable Long id, Model model){
        Contact contact = contactService.findById(id);
        model.addAttribute("contact", contact);
        return "contacts/form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Contact contact){
        if (contact.getId() != null){
            contactService.save(contact);
        }
        contactService.update(contact);
        return "redirect:/contacts";
    }
}
