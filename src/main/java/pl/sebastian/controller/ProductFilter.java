package pl.sebastian.controller;

import pl.sebastian.model.Privilege;
import pl.sebastian.util.Constans;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

@WebFilter(filterName = "ProductFilter", servletNames = {"ProductServlet"})
public class ProductFilter implements Filter {
    private static final Logger LOGGER = Logger.getLogger(ProductFilter.class.getSimpleName());

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        if(hasPrivilege(servletRequest)){
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            LOGGER.warning("SOMEONE TRIED TO GET ACCESS TO SECRET PAGE");
            servletResponse.setContentType(Constans.CONTENT_TYPE);
            servletResponse.setCharacterEncoding(Constans.ENCODING);
            PrintWriter writer = servletResponse.getWriter();
            writer.println("<h1 style=\"background-color:red;\">BRAK UPRAWNIEN</h1>");
            servletRequest.getRequestDispatcher("/index.jsp").include(servletRequest, servletResponse);
        }

    }

    private boolean hasPrivilege(ServletRequest servletRequest) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpSession session = httpServletRequest.getSession();
        Object attribute = session.getAttribute(Constans.PRIVILEGE);

        return Privilege.ADMIN.equals(attribute);
    }
}
