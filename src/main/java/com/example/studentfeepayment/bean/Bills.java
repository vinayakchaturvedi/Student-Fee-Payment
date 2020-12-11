package com.example.studentfeepayment.bean;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Bills")
public class Bills implements Cloneable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;                                         //Primary Key
    private String description;
    @Column(nullable = false)
    private Integer totalAmount;
    @Column(nullable = false)
    private Integer paidAmount;
    @Column(nullable = false)
    private Integer remainingAmount;
    @Column(nullable = false)
    private LocalDateTime billDate;
    private LocalDateTime deadline;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "bills")
    private List<Students> students;

    public Bills() {
    }

    public Bills(String description, Integer totalAmount, Integer paidAmount, Integer remainingAmount, LocalDateTime billDate, LocalDateTime deadline) {
        this.description = description;
        this.totalAmount = totalAmount;
        this.paidAmount = paidAmount;
        this.remainingAmount = remainingAmount;
        this.billDate = billDate;
        this.deadline = deadline;
        this.students = new ArrayList<>();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBillDate() {
        return billDate.getDayOfMonth() + "-" + billDate.getMonth() + "-" + billDate.getYear();
    }

    public void setBillDate(LocalDateTime dateTime) {
        this.billDate = dateTime;
    }

    public String getDeadline() {
        return deadline.getDayOfMonth() + "-" + deadline.getMonth() + "-" + deadline.getYear();
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Students> getStudents() {
        return students;
    }

    public void setStudents(List<Students> students) {
        this.students = new ArrayList<>(students);
        ;
    }

    public Integer getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Integer amount) {
        this.totalAmount = amount;
    }

    public Integer getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(Integer paidAmount) {
        this.paidAmount = paidAmount;
    }

    public Integer getRemainingAmount() {
        return remainingAmount;
    }

    public void setRemainingAmount(Integer remainingAmount) {
        this.remainingAmount = remainingAmount;
    }

    @Override
    public String toString() {
        return "Bills{" +
                "id=" + id +
                ", description='" + description +
                ", totalAmount=" + totalAmount +
                ", paidAmount=" + paidAmount +
                ", remainingAmount=" + remainingAmount +
                ", billDate=" + billDate.getDayOfMonth() + "-" + billDate.getMonth() + "-" + billDate.getYear() +
                ", deadline=" + deadline.getDayOfMonth() + "-" + deadline.getMonth() + "-" + deadline.getYear() +
                '}';
    }

    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public Bills shallowCopy() throws CloneNotSupportedException {
        Bills clonedBill = (Bills) this.clone();
        clonedBill.students = new ArrayList<>();
        return clonedBill;
    }
}
