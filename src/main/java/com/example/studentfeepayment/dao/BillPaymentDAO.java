package com.example.studentfeepayment.dao;

import com.example.studentfeepayment.bean.Bills;
import com.example.studentfeepayment.bean.StudentPayment;
import com.example.studentfeepayment.bean.Students;
import com.example.studentfeepayment.utils.SessionUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Map;

public class BillPaymentDAO {

    public boolean payBill(Map<Integer, Integer> billToPay) {
        Session session = SessionUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        for (Map.Entry<Integer, Integer> entry : billToPay.entrySet()) {
            int idKey = entry.getKey();
            int payingAmount = entry.getValue();
            String sqlQuery1 = "update Bills set paidAmount = paidAmount + :payingAmount where id = :idKey";
            Query query1 = session.createQuery(sqlQuery1);
            query1.setParameter("payingAmount", payingAmount);
            query1.setParameter("idKey", idKey);
            int result1 = query1.executeUpdate();

            String sqlQuery2 = "update Bills set remainingAmount = remainingAmount - :payingAmount where id = :idKey";
            Query query2 = session.createQuery(sqlQuery2);
            query2.setParameter("payingAmount", payingAmount);
            query2.setParameter("idKey", idKey);
            int result2 = query2.executeUpdate();

            System.out.println("Rows affected: " + result1 + ", " + result2);
        }
        transaction.commit();
        session.close();
        return true;
    }

    public boolean saveStudentPayment(StudentPayment studentPayment, Integer studentId, Integer billId) {
        Session session = SessionUtil.getSessionFactory().openSession();
        try {
            Transaction transaction = session.beginTransaction();

            String sqlQuery1 = "from Students where studentId = :studentId";
            Query query1 = session.createQuery(sqlQuery1);
            query1.setParameter("studentId", studentId);
            List studentResult = query1.list();

            String sqlQuery2 = "from Bills where id = :billId";
            Query query2 = session.createQuery(sqlQuery2);
            query2.setParameter("billId", billId);
            List billResult = query2.list();

            studentPayment.setStudent((Students) studentResult.get(0));
            studentPayment.setBill((Bills) billResult.get(0));

            session.save(studentPayment);
            transaction.commit();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            session.close();
            return false;
        } finally {
            session.close();
        }

        return true;
    }
}
