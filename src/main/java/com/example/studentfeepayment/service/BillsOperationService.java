package com.example.studentfeepayment.service;

import com.example.studentfeepayment.bean.Bills;
import com.example.studentfeepayment.bean.StudentPayment;
import com.example.studentfeepayment.bean.Students;
import com.example.studentfeepayment.dao.BillPaymentDAO;
import com.example.studentfeepayment.dao.StudentOperationsDAO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BillsOperationService {

    public List<Bills> getBills(Students student) {
        StudentOperationsDAO sopDAO = new StudentOperationsDAO();
        Students response = sopDAO.validateAndRetrieveStudent(student, false);
        List<Bills> bills = response.getBills();
        for (int i = 0; i < bills.size(); i++) {
            if (bills.get(i).getRemainingAmount().equals(0)) {
                bills.remove(i--);
            }
        }
        return bills;
    }

    public List<StudentPayment> paidBills(Students student) {
        StudentOperationsDAO sopDAO = new StudentOperationsDAO();
        Students response = sopDAO.validateAndRetrieveStudent(student, false);
        List<StudentPayment> bills = response.getStudentPaymentList();
      /*  for (int i = 0; i < bills.size(); i++) {
            if (bills.get(i).getStudent().equals(0)) {
                bills.remove(i--);
            }
        }*/
        return bills;
    }

    /**
     * @param request -- {"queryString":"userName=MT2020001&name=Vinayak&Registration Fees=500&Library Fees=1000"}
     * @return
     */
    public boolean payBills(String request) {
        String[] keyValuePair = request.substring(16, request.length() - 2).split("&");
        List<StudentPayment> paymentList;

        Students studentInQuery = new Students();
        studentInQuery.setUserName(keyValuePair[0].split("=")[1]);
        StudentOperationsDAO sopDAO = new StudentOperationsDAO();
        Students response = sopDAO.validateAndRetrieveStudent(studentInQuery, false);
        Map<String, Integer> typeToId = new HashMap<>();
        for (Bills bill : response.getBills()) {
            typeToId.put(bill.getDescription(), bill.getId());
        }

        Map<Integer, Integer> billToPay = new HashMap<>();
        for (int i = 2; i < keyValuePair.length; i++) {
            String billType = keyValuePair[i].split("=")[0];
            Integer amount = Integer.parseInt(keyValuePair[i].split("=")[1]);
            billToPay.put(typeToId.get(billType), amount);
            createAndSaveStudentPayment(response.getStudentId(), billType, typeToId.get(billType), amount);
        }

        BillPaymentDAO billPaymentDAO = new BillPaymentDAO();
        return billPaymentDAO.payBill(billToPay);
    }

    private boolean createAndSaveStudentPayment(Integer studentId, String billType, Integer billId, Integer amount) {

        StudentPayment studentPayment = new StudentPayment(billType, amount, LocalDateTime.now());
        BillPaymentDAO billPaymentDAO = new BillPaymentDAO();
        return billPaymentDAO.saveStudentPayment(studentPayment, studentId, billId);
    }
}
