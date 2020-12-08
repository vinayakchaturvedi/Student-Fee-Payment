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
    private LocalDateTime dateTime;
    @Column(nullable = false)
    private LocalDateTime deadline;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "bills")
    private List<Students> students;

    public Bills() {
    }

    public Bills(String description, LocalDateTime dateTime, LocalDateTime deadline) {
        this.description = description;
        this.dateTime = dateTime;
        this.deadline = deadline;
        this.students = new ArrayList<>();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
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
}
