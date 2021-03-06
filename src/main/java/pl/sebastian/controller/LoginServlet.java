package pl.sebastian.controller;

import pl.sebastian.model.Privilege;
import pl.sebastian.util.Constans;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    public static final String ADMIN_NICKNAME = "admin";
    public static final String ADMIN_PASSWORD = "admin";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        if(isAdmin(req)){
            setPrivilege(req, Privilege.ADMIN);
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
        } else if (isCustomer(req)){
            req.getSession().setAttribute("nickname", "customer");
            setPrivilege(req, Privilege.CUSTOMER);
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
        }else {
            resp.setContentType(Constans.CONTENT_TYPE);
            resp.setCharacterEncoding(Constans.ENCODING);
            resp.getWriter()
                    .println("<h1 style=\"background-color:red;\">BLAD LOGOWANIA SPROBUJ PONOWNIE</h1>");
            req.getRequestDispatcher("/login.jsp").include(req, resp);
        }
    }

    private boolean isCustomer(HttpServletRequest req) {
        //polaczenie do bazy danych
        Optional<String> nickname = Optional.ofNullable(req.getParameter(Constans.NICKNAME));
        Optional<String> password = Optional.ofNullable(req.getParameter(Constans.PASSWORD));

        Boolean correctNickname = nickname.map("customer"::equals).orElse(false);
        Boolean correctPassword = password.map("123"::equals).orElse(false);

        return correctNickname && correctPassword;
    }

    private void setPrivilege(HttpServletRequest req, Privilege privilege) {
        HttpSession session = req.getSession();
        session.setAttribute(Constans.PRIVILEGE, privilege);
    }

    private boolean isAdmin(HttpServletRequest req) {
        Optional<String> nickname = Optional.ofNullable(req.getParameter(Constans.NICKNAME));
        Optional<String> password = Optional.ofNullable(req.getParameter(Constans.PASSWORD));
        Boolean correctNickname = nickname.map(ADMIN_NICKNAME::equals).orElse(false);
        Boolean correctPassword = password.map(ADMIN_PASSWORD::equals).orElse(false);

        return correctNickname && correctPassword;
    }
}
