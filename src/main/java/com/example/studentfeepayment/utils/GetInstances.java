package com.example.studentfeepayment.utils;

import com.example.studentfeepayment.service.StudentOperationsService;

public class GetInstances {
    private static StudentOperationsService studentOperationsService;

    public static StudentOperationsService getInstanceOfStudentOperationService() {
        if (studentOperationsService != null) return studentOperationsService;

        studentOperationsService = new StudentOperationsService();
        return studentOperationsService;
    }
}
