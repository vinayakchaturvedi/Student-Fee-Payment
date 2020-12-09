package com.example.studentfeepayment.utils;

import com.example.studentfeepayment.dao.StudentOperationsDAO;
import com.example.studentfeepayment.service.StudentOperationsService;

public class GetInstances {
    private static StudentOperationsService studentOperationsService;
    private static StudentOperationsDAO studentOperationsDAO;

    public static StudentOperationsDAO getInstanceOfStudentOperationDAO() {
        if (studentOperationsDAO != null) return studentOperationsDAO;

        studentOperationsDAO = new StudentOperationsDAO();
        return studentOperationsDAO;
    }

    public static StudentOperationsService getInstanceOfStudentOperationService() {
        if (studentOperationsService != null) return studentOperationsService;

        studentOperationsService = new StudentOperationsService();
        return studentOperationsService;
    }
}
