package com.geekbrains.hiber.repositories;

import com.geekbrains.hiber.entities.Customer;
import com.geekbrains.hiber.entities.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManagerFactory;
import java.util.List;

@Component
public class CustomerRepository{

    private static SessionFactory sessionFactory;

    @Autowired
    public CustomerRepository(EntityManagerFactory factory) {
        if(factory.unwrap(SessionFactory.class) == null){
            throw new NullPointerException("factory is not a hibernate factory");
        }
        this.sessionFactory = factory.unwrap(SessionFactory.class);
    }

    public static void shutdown() {
        sessionFactory.close();
    }

    public  Customer createCustomer(String nameCustomer) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Customer customer = Customer.builder().name(nameCustomer).build();
            session.save(customer);
            session.getTransaction().commit();
            return customer;
        }
    }

    public  void readAndPrintExample(long id) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Customer simpleItem = session.get(Customer.class, id);
            System.out.println(simpleItem);
            session.getTransaction().commit();
        }
    }

    public  List<Product> readProducts(long id) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Customer simpleItem = session.get(Customer.class, id);
            List<Product> productList = simpleItem.getProducts();
            session.getTransaction().commit();
            return productList;
        }
    }

    public  void updateExample(long id, String name) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Customer simpleItem = session.get(Customer.class, id);
            simpleItem.setName(name);
            System.out.println(simpleItem);
            session.getTransaction().commit();
        }
    }

    public  void deleteExample(long id) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Customer simpleItem = session.get(Customer.class, id);
            session.delete(simpleItem);
            session.getTransaction().commit();
        }
    }
}
