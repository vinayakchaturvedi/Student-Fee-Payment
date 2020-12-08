package com.example.studentfeepayment.service;

import com.example.studentfeepayment.bean.Students;
import com.example.studentfeepayment.utils.GetInstances;

public class RegisterStudent {

    public static Integer latestRollNumber;

    public RegisterStudent() {
        //TODO:  retrieve from db; while initialization of this class;
        latestRollNumber = 0;
    }

    public boolean registerStudent(final Students student) {
        student.setRollNumber(latestRollNumber++);
        student.generateUserName();
        student.setCgpa(4);
        student.setPhotographPath("");
        student.setTotalCredits(16);
        System.out.println(student.getUserName() + " In service");
        return GetInstances.getInstanceOfRegisterStudentInDB().registerStudent(student);
    }
}
