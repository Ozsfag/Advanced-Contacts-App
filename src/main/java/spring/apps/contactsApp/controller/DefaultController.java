package spring.apps.contactsApp.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import spring.apps.contactsApp.service.ContactService;

@Controller
@RequestMapping("/")
@Slf4j
@RequiredArgsConstructor
public class DefaultController {
  private final ContactService contactService;

  @GetMapping("/")
  public String index(Model model) {
    log.debug("Calling DefaultController -> listContacts");
    model.addAttribute("contacts", contactService.findAll());
    return "index";
  }
}
