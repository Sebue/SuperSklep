package pl.sebastian.controller;

import pl.sebastian.util.Constans;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LogoutServlet", urlPatterns = {"/logout"})
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().invalidate();
        resp.setContentType(Constans.CONTENT_TYPE);
        resp.setCharacterEncoding(Constans.ENCODING);
        resp.getWriter().println("<h1 style=\"background-color:green;\">Wylogowano poprawnie</h1>");
        req.getRequestDispatcher("/index.jsp").include(req, resp);
    }
}
