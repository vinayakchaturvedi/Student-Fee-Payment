package com.example.studentfeepayment.bean;

import javax.persistence.*;
import java.time.LocalDateTime;
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
    private LocalDateTime billDate;
    private LocalDateTime deadline;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "bills")
    private List<Students> students;

    public Bills() {
    }

    public Bills(String description, Integer amount, LocalDateTime billDate, LocalDateTime deadline) {
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

    public LocalDateTime getBillDate() {
        return billDate;
    }

    public void setBillDate(LocalDateTime dateTime) {
        this.billDate = dateTime;
    }

    public LocalDateTime getDeadline() {
        return deadline;
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
