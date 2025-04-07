package jpaprojects.creditscoresystem.service;

import jpaprojects.creditscoresystem.entity.Customer;
import jpaprojects.creditscoresystem.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminService {

    private final CustomerRepository customerRepository;

    public AdminService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public String updateCreditScore(Long customerId, Double newScore) {
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        if (optionalCustomer.isEmpty()) {
            return "Müştəri tapılmadı!";
        }

        Customer customer = optionalCustomer.get();
        customer.setCreditScore(newScore);
        customerRepository.save(customer);
        return "Kredit skoru uğurla yeniləndi!";
    }

}
