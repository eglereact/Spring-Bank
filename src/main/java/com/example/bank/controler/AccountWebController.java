package com.example.bank.controler;

import com.example.bank.dto.AccountDto;
import com.example.bank.dto.TransferRequestDto;
import com.example.bank.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @PostMapping("/deposit")
    public String handleDeposit(@RequestParam("accountId") Long accountId,
                                @RequestParam("amount") double amount,
                                RedirectAttributes redirectAttributes) {
        try {
            // Call the service to deposit money
            accountService.deposit(accountId, amount);

            // Add a success message to be shown on the redirected page
            redirectAttributes.addFlashAttribute("message", "Deposit successful!");
        } catch (IllegalArgumentException e) {
            // Handle the exception and show an error message
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }

        // Redirect to the accounts page, passing the message
        return "redirect:/accounts";
    }

    @PostMapping("/withdraw")
    public String handleWithdraw(@RequestParam("accountId") Long accountId,
                                @RequestParam("amount") double amount,
                                RedirectAttributes redirectAttributes) {
        try {
            // Call the service to deposit money
            accountService.withdraw(accountId, amount);

            // Add a success message to be shown on the redirected page
            redirectAttributes.addFlashAttribute("message", "Withdraw successful!");
        } catch (IllegalArgumentException e) {
            // Handle the exception and show an error message
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }

        // Redirect to the accounts page, passing the message
        return "redirect:/accounts";
    }

    @PostMapping("/transfer")
    public String handleTransfer(@RequestParam("fromAccountId") String fromAccountNumber,
                                 @RequestParam("toAccountId") String toAccountNumber,
                                 @RequestParam("amount") double amount,
                                 Model model) {
        try {
            // Create a TransferRequestDto and populate it with the data from the form
            TransferRequestDto transferRequestDto = new TransferRequestDto();
            transferRequestDto.setSenderAccountNumber(fromAccountNumber);
            transferRequestDto.setRecipientAccountNumber(toAccountNumber);
            transferRequestDto.setAmount(amount);

            // Call the service method to handle the transfer
            accountService.transferAmount(transferRequestDto);

            // Add a success message to the model
            model.addAttribute("message", "Transfer successful!");
        } catch (IllegalArgumentException e) {
            // Add an error message to the model in case of validation issues
            model.addAttribute("errorMessage", e.getMessage());
        } catch (Exception e) {
            // Handle unexpected exceptions
            model.addAttribute("errorMessage", "An unexpected error occurred. Please try again.");
        }

        // Return to the accounts page
        return "redirect:/accounts";
    }

    @GetMapping("/accounts/transfers")
    public String showTransfers(Model model) {
        List<TransferRequestDto> transfers = accountService.getAllTransfers();
        model.addAttribute("transfers", transfers);
        return "transfers";
    }

}