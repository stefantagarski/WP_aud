package mk.ukim.finki.wp_aud.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp_aud.bootstrap.DataHolder;
import mk.ukim.finki.wp_aud.model.Category;
import mk.ukim.finki.wp_aud.service.CategoryService;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "category-servlet", urlPatterns = {"/servlet/category"})
public class CategoryServlet extends HttpServlet {

    private final CategoryService categoryService;

    public CategoryServlet(CategoryService categoryService) {
        this.categoryService = categoryService;
    } //constructor based dependency-injection

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();

        writer.write("<html><head><title>Categories</title></head><body>");

        writer.write("<h3>User Information</h3>");
        writer.format("<p>The IP-address who made this request is: %s </p></br>", req.getRemoteAddr());
        writer.format("<p>The client is: %s</p></br>", req.getHeader("User-Agent"));


        writer.write("<h3>Categories</h3>");
        writer.write("<ul>");
        categoryService.listCategories().forEach(category -> writer.format("<li>" + "%s" + " " + "(%s)" + "</li>",
                category.getName(), category.getDescription()));
        writer.write("</ul>");


        writer.write("<h3>Add Category</h3>");
        writer.write("<form method='post' action='/servlet/category'>");
        writer.write("<label for='ime'>Name:</label>");
        writer.write("<input type='text' id='ime' name='ime'></br>");
        writer.write("<label for='desc'>Description:</label>");
        writer.write("<input type='text' id='desc' name='desc'></br>");
        writer.write("<button type='submit'>Add</button>");
        writer.write("</form>");

        writer.write("</body></html>");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("ime"); //it gets it from the input field with name='ime'
        String desc = req.getParameter("desc");
        categoryService.create(name, desc);
        resp.sendRedirect("/servlet/category"); //redirect to the same servlet

    }

}
