package mk.ukim.finki.wp_aud.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp_aud.model.User;
import mk.ukim.finki.wp_aud.service.AuthService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet(name = "servlet-login", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    private final AuthService service;
    private final SpringTemplateEngine engine;

    public LoginServlet(AuthService service, SpringTemplateEngine engine) {
        this.service = service;
        this.engine = engine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);
        WebContext context = new WebContext(webExchange);

        engine.process("login.html", context, resp.getWriter());

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IWebExchange webExchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);
        WebContext context = new WebContext(webExchange);

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        User user = null;

        try {
           user = service.login(username, password);
        } catch (RuntimeException exception) {
            context.setVariable("hasError", true);
            context.setVariable("error", exception.getMessage());

            engine.process("login.html", context, resp.getWriter());
        }

        if (user != null) {
            req.getSession().setAttribute("user", user);
            resp.sendRedirect("/servlet/category");
        }
    }
}
