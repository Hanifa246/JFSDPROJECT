package com.klef.jfsd.model;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class ClientDemo {
    public static void main(String[] args) {
        Configuration configuration = new Configuration().configure();
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();

        // Insert records
        insertDepartment(session, "Computer Science", "Building A", "Dr. Smith");
        insertDepartment(session, "Electrical", "Building B", "Dr. Johnson");

        // Delete record
        deleteDepartmentById(session, 1);

        sessionFactory.close();
    }

    public static void insertDepartment(Session session, String name, String location, String hodName) {
        Transaction transaction = session.beginTransaction();
        Department department = new Department();
        department.setName(name);
        department.setLocation(location);
        department.setHodName(hodName);
        session.save(department);
        transaction.commit();
        System.out.println("Department inserted successfully!");
    }

    public static void deleteDepartmentById(Session session, int id) {
        Transaction transaction = session.beginTransaction();
        String hql = "DELETE FROM Department WHERE departmentId = :id";
        int deletedRows = session.createQuery(hql).setParameter("id", id).executeUpdate();
        transaction.commit();
        System.out.println("Number of departments deleted: " + deletedRows);
    }
}
