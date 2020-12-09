package com.example.studentfeepayment.dao;

import com.example.studentfeepayment.bean.Students;
import com.example.studentfeepayment.utils.SessionUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class StudentOperationsDAO {

    public boolean registerStudent(Students student) {
        Session session = SessionUtil.getSessionFactory().openSession();
        Transaction transaction;
        try {
            transaction = session.beginTransaction();
            System.out.println("Adding a new student in the DB: ");
            System.out.println(student.toString());
            session.save(student);
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

    public Students validateStudentLogin(final Students student) {
        try {
            Session session = SessionUtil.getSessionFactory().openSession();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Students> criteriaQuery = criteriaBuilder.createQuery(Students.class);
            Root<Students> studentsRoot = criteriaQuery.from(Students.class);
            criteriaQuery.select(studentsRoot);
            criteriaQuery.where(criteriaBuilder.like(studentsRoot.get("userName"), student.getUserName()));
            criteriaQuery.where(criteriaBuilder.like(studentsRoot.get("password"), student.getPassword()));

            Query<Students> query = session.createQuery(criteriaQuery);
            List<Students> students = query.getResultList();
            session.close();
            return students.isEmpty() ? null : students.get(0);

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
}
