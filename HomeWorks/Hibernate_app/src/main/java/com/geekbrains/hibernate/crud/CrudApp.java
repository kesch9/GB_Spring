package com.geekbrains.hibernate.crud;

import com.geekbrains.hibernate.PrepareDataApp;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class CrudApp {
    private static SessionFactory factory;

    public static void init() {
        PrepareDataApp.forcePrepareData();
        factory = new Configuration()
                .configure("configs/crud/hibernate.cfg.xml")
                .buildSessionFactory();
    }

    public static void showManyItems() {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();

            List<ProductItem> items = session.createQuery("from ProductItem").getResultList();
            System.out.println(items + "\n");

            ProductItem si1 = session.createQuery("select s from ProductItem s where s.id = 3", ProductItem.class).getSingleResult();
            System.out.println(si1 + "\n");

            List<ProductItem> cheapItems = session.createQuery("select s from ProductItem s where s.cost < 80").getResultList();
            System.out.println(cheapItems + "\n");

            session.getTransaction().commit();
        }
    }

    public static void shutdown() {
        factory.close();
    }

    public static void createExample() {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            ProductItem dragonStatue = new ProductItem(4L, "Dragon Statue", 100);
            session.save(dragonStatue);
            session.getTransaction().commit();
        }
    }

    public static void readAndPrintExample() {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            ProductItem simpleItem = session.get(ProductItem.class, 1L);
            System.out.println(simpleItem);
            session.getTransaction().commit();
        }
    }

    public static void updateExample() {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            ProductItem simpleItem = session.get(ProductItem.class, 1L);
            simpleItem.setCost(10000);
            session.getTransaction().commit();
        }
    }

    public static void deleteExample() {
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            ProductItem simpleItem = session.get(ProductItem.class, 1L);
            session.delete(simpleItem);
            session.getTransaction().commit();
        }
    }

    public static void main(String[] args) {
        try {
            init();
            // createExample();
            // readAndPrintExample();
            // updateExample();
            // deleteExample();
            //showManyItems();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            shutdown();
        }
    }
}
