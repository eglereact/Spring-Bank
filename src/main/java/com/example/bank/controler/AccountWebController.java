package com.example.bank.controler;

import com.example.bank.dto.AccountDto;
import com.example.bank.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AccountWebController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/accounts")
    public String viewAccounts(Model model) {
        List<AccountDto> accounts = accountService.getAllAccounts();
        model.addAttribute("accounts", accounts);
        return "accounts";
    }

    @GetMapping("/accounts/create")
    public String showCreateAccountForm(Model model) {
        model.addAttribute("accountDto", new AccountDto());
        return "create"; // The name of the Thymeleaf template (createAccount.html)
    }

    @PostMapping("/accounts/create")
    public String createAccount(@ModelAttribute AccountDto accountDto) {
        accountService.createAccount(accountDto); // Call the shared service logic
        return "redirect:/accounts";
    }

    @GetMapping("/accounts/delete/{id}")
    public String deleteAccount(@PathVariable Long id) {
        accountService.deleteAccount(id); // Call the service method to delete the account
        return "redirect:/accounts"; // Redirect back to the accounts page
    }

}