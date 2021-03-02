package com.geekbrains.hiber;

import com.geekbrains.hiber.entities.Customer;
import com.geekbrains.hiber.entities.Product;
import com.geekbrains.hiber.entities.ProductCustomerID;
import com.geekbrains.hiber.repositories.CustomerRepository;
import com.geekbrains.hiber.repositories.ProductRepository;
import com.geekbrains.hiber.services.CustomerService;
import com.geekbrains.hiber.services.ProductService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class HiberApplication {

	private static CustomerRepository customerRepository;

	private static ProductRepository productRepository;

	private static ProductService productService;

	private static CustomerService customerService;

	private static ConfigurableApplicationContext context;


	public static void main(String[] args) {

		context = SpringApplication.run(HiberApplication.class, args);
		customerRepository = (CustomerRepository) context.getBean("customerRepository");
		productRepository = (ProductRepository) context.getBean("productRepository");
		productService = (ProductService) context.getBean("productService");
		customerService = (CustomerService) context.getBean("customerService");
		List<Customer> customerList = new ArrayList<>();
		customerList.add(customerRepository.createCustomer("Sergey"));
		long id = productRepository.createExample(102, "Test Product",customerList);
		System.out.println(id);
		productRepository.readAndPrintExample(1);
		productRepository.readAndPrintExample( id);
		productRepository.deleteExample( id);
		customerRepository.deleteExample( customerList.get(0).getId());
		productService.getCustomers(2).forEach(System.out::println);
		customerService.getProducts(1).forEach(System.out::println);
		List<Product> products = customerService.getProducts(1);
		Product product = products.get(0);
		product.getProductCustomers().forEach(s -> System.out.println(s.getCost()));
	}

}
