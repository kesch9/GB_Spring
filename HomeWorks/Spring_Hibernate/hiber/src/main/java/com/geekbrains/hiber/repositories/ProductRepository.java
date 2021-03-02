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
public class ProductRepository {

    private static SessionFactory sessionFactory;

    @Autowired
    public ProductRepository(EntityManagerFactory factory) {
        if(factory.unwrap(SessionFactory.class) == null){
            throw new NullPointerException("factory is not a hibernate factory");
        }
        this.sessionFactory = factory.unwrap(SessionFactory.class);
    }

    public  void shutdown() {
        sessionFactory.close();
    }



    public  Long createExample(int cost, String title) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Product product = Product.builder().cost(cost).title(title).build();
            session.save(product);
            session.getTransaction().commit();
            return product.getId();
        }
    }

    public  Long createExample(int cost, String title, List<Customer> customers) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Product product = Product.builder().cost(cost).title(title).customers(customers).build();
            session.save(product);
            session.getTransaction().commit();
            return product.getId();
        }
    }

    public  void readAndPrintExample(long id) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Product simpleItem = session.get(Product.class, id);
            System.out.println(simpleItem);
            List<Customer> customers = simpleItem.getCustomers();
            customers.forEach(System.out::println);
            session.getTransaction().commit();
        }
    }

    public  List<Customer> readCustomers(long id) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Product simpleItem = session.get(Product.class, id);
            List<Customer> customerList = simpleItem.getCustomers();
            session.getTransaction().commit();
            return customerList;
        }
    }

    public  void updateExample(long id, int cost) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Product simpleItem = session.get(Product.class, id);
            simpleItem.setCost(cost);
            System.out.println(simpleItem);
            session.getTransaction().commit();
        }
    }

    public  void deleteExample(long id) {
        try (Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            Product simpleItem = session.get(Product.class, id);
            session.delete(simpleItem);
            session.getTransaction().commit();
        }
    }

}
