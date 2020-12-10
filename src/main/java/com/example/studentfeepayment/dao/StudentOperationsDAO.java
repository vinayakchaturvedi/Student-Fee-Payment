package com.example.studentfeepayment.dao;

import com.example.studentfeepayment.bean.Bills;
import com.example.studentfeepayment.bean.Students;
import com.example.studentfeepayment.utils.SessionUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class StudentOperationsDAO {

    public boolean registerStudent(Students student) {
        Session session = SessionUtil.getSessionFactory().openSession();
        Transaction transaction;
        try {
            transaction = session.beginTransaction();
            List<Bills> bills = student.getBills();
            for (Bills bill : bills) {
                session.save(bill);
            }
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
        Session session = SessionUtil.getSessionFactory().openSession();
        try {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Students> criteriaQuery = criteriaBuilder.createQuery(Students.class);
            Root<Students> studentsRoot = criteriaQuery.from(Students.class);
            criteriaQuery.select(studentsRoot);
            Predicate userName = criteriaBuilder.like(studentsRoot.get("userName"), student.getUserName());
            Predicate password = criteriaBuilder.like(studentsRoot.get("password"), student.getPassword());

            criteriaQuery.where(criteriaBuilder.and(userName, password));

            Query<Students> query = session.createQuery(criteriaQuery);
            List<Students> students = query.getResultList();
            session.close();

            return students.isEmpty() ? null : students.get(0);

        } catch (Exception ex) {
            session.close();
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public Integer getMaxId() {
        Session session = SessionUtil.getSessionFactory().openSession();
        try {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Integer> criteriaQuery = criteriaBuilder.createQuery(Integer.class);
            Root<Students> studentsRoot = criteriaQuery.from(Students.class);
            criteriaQuery.select(criteriaBuilder.max(studentsRoot.get("studentId")));

            Query<Integer> query = session.createQuery(criteriaQuery);
            List<Integer> ids = query.getResultList();

            session.close();
            return ids == null || ids.isEmpty() || ids.get(0) == null ? 0 : ids.get(0);

        } catch (Exception ex) {
            session.close();
            System.out.println(ex.getMessage());
            return 0;
        }
    }
}
