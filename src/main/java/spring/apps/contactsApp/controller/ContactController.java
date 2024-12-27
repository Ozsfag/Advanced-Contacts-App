package spring.apps.contactsApp.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import spring.apps.contactsApp.model.Contact;
import spring.apps.contactsApp.service.ContactService;

@Controller
@RequestMapping("/contact")
@Slf4j
@RequiredArgsConstructor
public class ContactController {
  private final ContactService contactService;

  @GetMapping("/")
  public String index(Model model) {
    log.debug("Calling ContactsController -> listContacts");
    model.addAttribute("contacts", contactService.findAll());
    return "index";
  }

  @GetMapping("/create")
  public String showCreateForm(Model model) {
    model.addAttribute("contact", new Contact());
    return "create";
  }

  @PostMapping("/create")
  public String createContact(@ModelAttribute Contact contact) {
    contactService.save(contact);
    return "redirect:/";
  }

  @GetMapping("/edit/{id}")
  public String showEditForm(@PathVariable Long id, Model model) {
    Contact task = contactService.findById(id);
    if (task != null) {
      model.addAttribute("task", task);
      return "edit";
    }
    return "redirect:/";
  }

  @PostMapping("/edit")
  public String editTask(@ModelAttribute Contact contact) {
    Contact existedContact = contactService.findById(contact.getId());
    if (existedContact != null) {
      existedContact.setFirstName(contact.getFirstName());
      existedContact.setLastName(contact.getLastName());
      existedContact.setEmail(contact.getEmail());
      existedContact.setPhone(contact.getPhone());
      contactService.update(existedContact);
    }
    return "redirect:/";
  }
}
