package jpaprojects.creditscoresystem.controller;

import jpaprojects.creditscoresystem.entity.Customer;
import jpaprojects.creditscoresystem.service.CreditScoreService;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/credit")
public class CreditScoreController {

    private final CreditScoreService creditScoreService;

    public CreditScoreController(CreditScoreService creditScoreService) {
        this.creditScoreService = creditScoreService;
    }
    @PostMapping("/add")
    public Customer addCustomer(@RequestBody Customer customer) {
        return creditScoreService.createCustomer(customer);
    }

    @PostMapping("/apply/{customerId}")
    public CompletableFuture<String> applyForCredit(@PathVariable Long customerId) {
        return creditScoreService.calculateCreditScore(customerId);
    }
    @GetMapping("/getCustomer/{customerId}")
    public Double getCreditScoreById(@PathVariable Long customerId) {
        return creditScoreService.getCustomerById(customerId);
    }
}
