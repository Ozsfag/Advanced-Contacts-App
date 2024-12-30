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
    Contact contact = contactService.findById(id);
    if (contact != null) {
      model.addAttribute("contact", contact);
      return "edit";
    }
    return "redirect:/";
  }

  @PostMapping("/edit")
  public String editContact(@ModelAttribute Contact contact) {
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

  @GetMapping("/delete/{id}")
  public String deleteContact(@PathVariable Long id){
    log.debug("Calling ContactController -> delete. ID is {}", id);
    Contact contact = contactService.findById(id);
    if (contact != null){
      contactService.delete(contact);
    }
    return "redirect:/";
  }
}
