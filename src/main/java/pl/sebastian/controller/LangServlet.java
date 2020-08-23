package pl.sebastian.controller;

import pl.sebastian.util.LangProvider;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

@WebServlet(name = "LanguageServlet", urlPatterns = {"/languageServlet"})
public class LangServlet extends HttpServlet {
    private final static String LANG = "lang";
    private final static String PL_LANG = "pl";

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        String langParameter = request.getParameter(LANG);

        if (PL_LANG.equals(langParameter)) {
            LangProvider.INSTANCE.setLanguage(Locale.forLanguageTag(PL_LANG));
        } else {
            LangProvider.INSTANCE.setLanguage(Locale.forLanguageTag("eng"));
        }
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
}