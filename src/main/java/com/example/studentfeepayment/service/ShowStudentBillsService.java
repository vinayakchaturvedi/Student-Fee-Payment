package com.example.studentfeepayment.service;

import com.example.studentfeepayment.bean.Bills;
import com.example.studentfeepayment.bean.Students;
import com.example.studentfeepayment.dao.BillPaymentDAO;
import com.example.studentfeepayment.dao.StudentOperationsDAO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShowStudentBillsService {

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

    /**
     * @param request -- {"queryString":"userName=MT2020001&name=Vinayak&Registration Fees=500&Library Fees=1000"}
     * @return
     */
    public boolean payBills(String request) {
        String[] keyValuePair = request.substring(16, request.length() - 2).split("&");
        //string.substring(16, string.length()-2).split("&")[0].split("=")[1]

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
        }

        BillPaymentDAO billPaymentDAO = new BillPaymentDAO();
        return billPaymentDAO.payBill(billToPay);
    }
}
