package com.example.studentfeepayment.service;

import com.example.studentfeepayment.bean.Students;
import com.example.studentfeepayment.utils.GetInstances;

public class StudentOperationsService {

    public static Integer latestRollNumber;

    public StudentOperationsService() {
        //TODO:  retrieve from db; while initialization of this class;
        latestRollNumber = 0;
    }

    public boolean registerStudent(final Students student) {
        student.setRollNumber(latestRollNumber++);
        student.generateUserName();
        student.setCgpa(4);
        student.setPhotographPath("");
        student.setTotalCredits(16);
        return GetInstances.getInstanceOfStudentOperationDAO().registerStudent(student);
    }

    public Students validateStudentLogin(final Students student) {
        if (student.getUserName().isEmpty() || student.getPassword().isEmpty()) return null;
        return GetInstances.getInstanceOfStudentOperationDAO().validateStudentLogin(student);
    }
}
