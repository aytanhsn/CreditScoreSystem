package jpaprojects.creditscoresystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Integer age;
    private Double income;
    private LocalDate lastPaymentDate;
    private Double paymentRatio;
    private Integer totalLoans;
    private Integer repaidLoans;
    private Double activeDebt;
    private Double creditScore;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Double getIncome() {
        return income;
    }

    public void setIncome(Double income) {
        this.income = income;
    }

    public LocalDate getLastPaymentDate() {
        return lastPaymentDate;
    }

    public void setLastPaymentDate(LocalDate lastPaymentDate) {
        this.lastPaymentDate = lastPaymentDate;
    }

    public Double getPaymentRatio() {
        return paymentRatio;
    }

    public void setPaymentRatio(Double paymentRatio) {
        this.paymentRatio = paymentRatio;
    }

    public Integer getTotalLoans() {
        return totalLoans;
    }

    public void setTotalLoans(Integer totalLoans) {
        this.totalLoans = totalLoans;
    }

    public Integer getRepaidLoans() {
        return repaidLoans;
    }

    public void setRepaidLoans(Integer repaidLoans) {
        this.repaidLoans = repaidLoans;
    }

    public Double getActiveDebt() {
        return activeDebt;
    }

    public void setActiveDebt(Double activeDebt) {
        this.activeDebt = activeDebt;
    }

    public Double getCreditScore() {
        return creditScore;
    }

    public void setCreditScore(Double creditScore) {
        this.creditScore = creditScore;
    }
}
