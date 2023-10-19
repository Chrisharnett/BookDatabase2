package com.example.servletproject;
import java.sql.*;
import java.io.*;
import java.sql.SQLException;
import java.util.LinkedList;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

//@WebServlet(name = "AddAuthor", value = "/AddAuthor")
public class AddAuthorServlet extends HttpServlet {
//public void doPost(HttpServletRequest request,
//                   HttpServletResponse response) throws IOException {
//    String lastName = request.getParameter("lastName");
//    String firstName = request.getParameter("firstName");
//
//    PrintWriter out = response.getWriter();
//
//    try (Connection conn = DatabaseConnection.initDatabase()){
//        PreparedStatement newAuthorStatement = conn.prepareStatement("INSERT INTO authors VALUES (default, ?, ?)");
//        newAuthorStatement.setString(1, lastName);
//        newAuthorStatement.setString(2, firstName);
//        newAuthorStatement.executeQuery();
//
//        out.println("<html><body>");
//        out.println("<h1>New Author Added!</h1>");
//        out.println("<h2>" + lastName + ", " + firstName + "</h2>");
//    }
//    catch( SQLException ex) {
//        ex.printStackTrace();
//        out.println("<html><body>");
//        out.println("<h1>" + ex.toString() + "</h1>");
//        out.println("</body></html>");
//    }
//}
}
