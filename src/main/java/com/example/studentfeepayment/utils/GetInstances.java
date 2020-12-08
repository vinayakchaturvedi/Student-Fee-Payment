package com.example.studentfeepayment.utils;

import com.example.studentfeepayment.dao.RegisterStudentInDB;
import com.example.studentfeepayment.service.RegisterStudent;

public class GetInstances {
    private static RegisterStudent registerStudent;
    private static RegisterStudentInDB registerStudentInDB;

    public static RegisterStudentInDB getInstanceOfRegisterStudentInDB() {
        if (registerStudentInDB != null) return registerStudentInDB;

        registerStudentInDB = new RegisterStudentInDB();
        return registerStudentInDB;
    }

    public static RegisterStudent getInstanceOfRegisterStudent() {
        if (registerStudent != null) return registerStudent;

        registerStudent = new RegisterStudent();
        return registerStudent;
    }
}
