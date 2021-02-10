package ru.geekbrains.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.geekbrains.pojo.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@WebServlet(name = "ProductServlet", urlPatterns = "/products")
public class ProductServlet extends HttpServlet {

    private static Logger logger = LoggerFactory.getLogger(BasicServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.debug("Start");
        Stream<Integer> infiniteStream = Stream.iterate(0, i -> ++i);
        List<Product> products = infiniteStream
                .limit(10)
                .map(x -> new Product(x, Integer.toString(x), x))
                .collect(Collectors.toList());
        resp.getWriter().println("<p> " + products.get(0) + "</p>");
        resp.getWriter().close();
    }
}
