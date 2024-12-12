package mk.ukim.finki.wp_aud.web.filters;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp_aud.model.User;
import org.springframework.context.annotation.Profile;

import java.io.IOException;

@WebFilter
@Profile("servlet")
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        User user = (User) req.getSession().getAttribute("user");

        String path = req.getServletPath();

        if (user == null && !path.equals("/login") && !path.equals("/register")) {
            resp.sendRedirect("/login");
        } else {
            filterChain.doFilter(req, resp);
        }

    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
