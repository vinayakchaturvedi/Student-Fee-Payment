package com.example.studentfeepayment.dao;

import com.example.studentfeepayment.bean.Students;
import com.example.studentfeepayment.utils.SessionUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class RegisterStudentInDB {

    public boolean registerStudent(Students student) {
        try {
            System.out.println(student.getUserName() + " In DAO");
            Session session = SessionUtil.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.save(student);
            transaction.commit();
            session.close();
            System.out.println(student.getUserName() + " In DAO -- Done!");
            return true;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return false;
        }
    }
}
