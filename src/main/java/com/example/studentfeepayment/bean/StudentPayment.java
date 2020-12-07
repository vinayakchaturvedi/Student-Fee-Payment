package com.example.studentfeepayment.bean;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "StudentPayment")
public class StudentPayment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;                                 //Primary Key
    private int studentId;                          //Foreign Key
    private int billId;                             //Foreign Key
    private String description;
    @Column(nullable = false)
    private long amount;
    @Column(nullable = false)
    private LocalDateTime paymentDate;

    public StudentPayment() {
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }
}
