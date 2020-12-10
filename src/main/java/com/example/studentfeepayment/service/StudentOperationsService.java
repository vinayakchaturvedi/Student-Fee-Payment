package com.example.studentfeepayment.service;

import com.example.studentfeepayment.bean.Bills;
import com.example.studentfeepayment.bean.Students;
import com.example.studentfeepayment.utils.Constants;
import com.example.studentfeepayment.utils.GetInstances;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class StudentOperationsService {

    public static Integer latestRollNumber;

    public StudentOperationsService() {
        latestRollNumber = GetInstances.getInstanceOfStudentOperationDAO().getMaxId();
    }

    public Students registerStudent(final Students student) {

        String rollNumber = String.valueOf(++latestRollNumber);
        if (rollNumber.length() == 1) rollNumber = "00" + rollNumber;
        else if (rollNumber.length() == 2) rollNumber = "0" + rollNumber;
        rollNumber = "MT2020" + rollNumber;

        student.setRollNumber(rollNumber);
        student.setUserName(rollNumber);
        student.setCgpa(4);
        student.setPhotographPath("");
        student.setTotalCredits(16);

        student.setBills(generateBills());
        GetInstances.getInstanceOfStudentOperationDAO().registerStudent(student);

        return student;
    }

    private List<Bills> generateBills() {

        List<Bills> bills = new ArrayList<>();
        LocalDateTime today = LocalDateTime.now();

        Bills registration = new Bills(Constants.REGISTRATION_FEE, Constants.REGISTRATION_FEE_VALUE,
                today, today.plusDays(4));

        Bills library = new Bills(Constants.LIBRARY_FEE, Constants.LIBRARY_FEE_VALUE,
                today, today.plusDays(4));

        Bills tuition = new Bills(Constants.TUITION_FEE, Constants.TUITION_FEE_VALUE,
                today, today.plusDays(4));

        Bills exam = new Bills(Constants.EXAM_FEE, Constants.EXAM_FEE_VALUE,
                today, today.plusDays(4));

        Bills hostel = new Bills(Constants.HOSTEL_FEE, Constants.HOSTEL_FEE_VALUE,
                today, today.plusDays(4));

        Bills mess = new Bills(Constants.MESS_FEE, Constants.MESS_FEE_VALUE,
                today, today.plusDays(4));

        bills.add(registration);
        bills.add(library);
        bills.add(tuition);
        bills.add(exam);
        bills.add(hostel);
        bills.add(mess);

        return bills;
    }

    public Students validateStudentLogin(final Students student) {
        if (student.getUserName().isEmpty() || student.getPassword().isEmpty()) return null;
        return GetInstances.getInstanceOfStudentOperationDAO().validateStudentLogin(student);
    }
}
