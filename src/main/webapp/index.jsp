<%@ page language="java" contentType="text/html"%>
<%@ page import="pl.sebastian.util.LangProvider" %>
<html>
<head>
<title>SuperSklep</title>
</head>
<body>
<h1>
<% Object nickname = session.getAttribute("nickname");
    if(nickname != null){
        out.println("Witaj uzytkowniku: " + nickname);
    }
%>
</h1>
<h2><%= LangProvider.INSTANCE.getMessage("greetings")%></h2>
<a href="/SuperSklep/company.jsp">O firmie</a><br>
<%
Object hasPrivilege = session.getAttribute("privilege");
    if(hasPrivilege != null){
        out.println("<a href=\"/SuperSklep/logout\">Wylogowanie</a><br>");
    } else{
    String login = LangProvider.INSTANCE.getMessage("login");
        out.println("<a href=\"/SuperSklep/login.jsp\">" + login + "</a><br>");
    }

%>
<div id="flag">
    <a href="/SuperSklep/languageServlet?lang=pl">
    <img class="language_flag" src="images/poland_flag.png" width="50" height="20"></a>
    <a href="/SuperSklep/languageServlet?lang=en">
    <img class="language_flag" src="images/uk_flag.jpg" width="50" height="20"></a>
</div>
</body>
</html>
