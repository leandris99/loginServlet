<%-- 
    Document   : jsp
    Created on : 8/11/2024, 2:28:12 p. m.
    Author     : acer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link
            rel="stylesheet"
            href="https://cdn.jsdelivr.net/npm/@picocss/pico@2/css/pico.min.css"
            >
        <title>Evidencia</title>
    </head>
    <body>
        <% 
            if (session.getAttribute("usuario") == null){
                response.sendRedirect("index.html");
                return;
            }
        %>
        <h1>Hello World!</h1>
    </body>
</html>
