package ru.geekbrains.serlvlets;

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

@WebServlet(name = "ProductServlet", urlPatterns = "/all")
public class ProductServlet extends HttpServlet {

    private static Logger logger = LoggerFactory.getLogger(ProductServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.debug("Start");
        int size = DEFAULT;
        try{
            size = Integer.parseInt(req.getParameter("size"));
        } catch (Exception e){
            logger.debug(e.getMessage());
        }
        Stream<Integer> infiniteStream = Stream.iterate(0, i -> ++i);
        List<Product> products = infiniteStream
                .limit(size)
                .map(x -> new Product(x, Integer.toString(x), x))
                .collect(Collectors.toList());
        for(Product product : products) {
            resp.getWriter().println("<p> " + product + "</p>");
        }
        resp.getWriter().close();
    }

    private static int DEFAULT = 10;
}
