package com.example.studentfeepayment.bean;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Bills")
public class Bills {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;                                         //Primary Key
    private String description;
    @Column(nullable = false)
    private Integer amount;
    @Column(nullable = false)
    private LocalDate billDate;
    private LocalDate deadline;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "bills")
    private List<Students> students;

    public Bills() {
    }

    public Bills(String description, Integer amount, LocalDate billDate, LocalDate deadline) {
        this.description = description;
        this.amount = amount;
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

    public LocalDate getBillDate() {
        return billDate;
    }

    public void setBillDate(LocalDate dateTime) {
        this.billDate = dateTime;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
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
        this.students = students;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Bills{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                ", billDate=" + billDate +
                ", deadline=" + deadline +
                '}';
    }
}
