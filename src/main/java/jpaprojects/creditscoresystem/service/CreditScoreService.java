package jpaprojects.creditscoresystem.service;

import jpaprojects.creditscoresystem.entity.Customer;
import jpaprojects.creditscoresystem.repository.CustomerRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class CreditScoreService {

    private final CustomerRepository customerRepository;

    public CreditScoreService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
    public Customer createCustomer(Customer customer) {
        customer.setCreditScore(0.0);
        return customerRepository.save(customer);
    }
    @Async
    public CompletableFuture<String> calculateCreditScore(Long customerId) {
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        if (optionalCustomer.isEmpty()) {
            return CompletableFuture.completedFuture("Müştəri tapılmadı!");
        }

        Customer customer = optionalCustomer.get();

        double incomeWeight = 0.3;
        double ageWeight = 0.1;
        double paymentDisciplineWeight = 0.3;
        double creditHistoryWeight = 0.3;

        double score = (customer.getIncome() * incomeWeight)
                + (customer.getAge() * ageWeight)
                + getPaymentDisciplineScore(customer.getLastPaymentDate(), customer.getPaymentRatio()) * paymentDisciplineWeight
                + getCreditHistoryScore(customer.getTotalLoans(), customer.getRepaidLoans(), customer.getActiveDebt()) * creditHistoryWeight;

        customer.setCreditScore(score);
        customerRepository.save(customer);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String status = score >= 700 ? "Kredit Təsdiq Olundu " : "Kredit Rədd Edildi ";
        return CompletableFuture.completedFuture("Müştəri ID: " + customerId + ", Skor: " + score + ", Status: " + status);
    }

    private double getPaymentDisciplineScore(LocalDate lastPaymentDate, double paymentRatio) {
        if (lastPaymentDate == null) return 0.0;

        long daysLate = ChronoUnit.DAYS.between(lastPaymentDate, LocalDate.now());

        if (daysLate <= 30 && paymentRatio >= 90) return 1.0;
        if (daysLate <= 90 && paymentRatio >= 75) return 0.75;
        if (daysLate <= 180 && paymentRatio >= 50) return 0.5;
        return 0.25;
    }

    private double getCreditHistoryScore(int totalLoans, int repaidLoans, double activeDebt) {
        if (totalLoans == 0) return 0.5;

        double repaymentRate = (double) repaidLoans / totalLoans * 100;

        if (repaymentRate >= 90 && activeDebt == 0) return 1.0;
        if (repaymentRate >= 75 && activeDebt < 5000) return 0.75;
        if (repaymentRate >= 50 && activeDebt < 10000) return 0.5;
        return 0.25;
    }
    public Double getCustomerById(Long customerId) {
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        if (optionalCustomer.isEmpty()) {
            return null;
        }

        return optionalCustomer.get().getCreditScore();
    }
}
