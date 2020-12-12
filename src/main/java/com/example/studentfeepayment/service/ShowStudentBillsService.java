package com.example.studentfeepayment.service;

import com.example.studentfeepayment.bean.Bills;
import com.example.studentfeepayment.bean.Students;
import com.example.studentfeepayment.dao.StudentOperationsDAO;

import java.util.List;

public class ShowStudentBillsService {

    public List<Bills> getBills(Students student) {
        StudentOperationsDAO sopDAO = new StudentOperationsDAO();
        Students response = sopDAO.validateAndRetrieveStudent(student, false);
        List<Bills> bills = response.getBills();
        for (int i = 0; i < bills.size(); i++) {
            if (bills.get(0).getRemainingAmount() == 0) {
                bills.remove(i--);
            }
        }
        return bills;
    }
}
