package com.example.servletproject;
import java.sql.*;
import java.io.*;
import java.sql.SQLException;
import java.util.LinkedList;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet(name = "AddBook", value = "/AddBook")
public class AddBookServlet extends HttpServlet {

    public void doPost(HttpServletRequest request,
                      HttpServletResponse response) throws IOException {

        String isbn = request.getParameter("isbn");
        String title = request.getParameter("title");
        String edition = request.getParameter("edition");
        String copyright = request.getParameter("copyright");
        String authorFullName = request.getParameter("author");
        String[] fullName = authorFullName.split(",");
        // TODO: Make author names title case.
        String authorFirstName = fullName[1].trim();
        String authorLastName = fullName[0].trim();

        PrintWriter out = response.getWriter();

        try (Connection conn = DatabaseConnection.initDatabase()){
            PreparedStatement authorCheckStatement = conn.prepareStatement("SELECT * from authors " +
                                                                                "WHERE ? = firstName AND " +
                                                                                "? = lastName");
            authorCheckStatement.setString(1, authorFirstName);
            authorCheckStatement.setString(2, authorLastName);
            ResultSet authorResults = authorCheckStatement.executeQuery();
            Author author = null;
            while (authorResults.next()){
                author = new Author(authorResults.getInt(1), authorResults.getString(2), authorResults.getString(3));
                break;
            }
            if (author != null) {
                PreparedStatement bookStatement = conn.prepareStatement("INSERT INTO titles" +
                                                                            "(isbn, title, editionNumber, copyright)" +
                                                                            "VALUES" +
                                                                            "(?, ?, ?, ?);");

                bookStatement.setString(1, isbn);
                bookStatement.setString(2, title);
                bookStatement.setInt(3, Integer.parseInt(edition));
                bookStatement.setInt(4, Integer.parseInt(copyright));

                bookStatement.executeQuery();

                PreparedStatement authorISBNStatement = conn.prepareStatement("INSERT INTO authorISBN VALUES (?, ?)");
                authorISBNStatement.setInt(1, author.getId());
                authorISBNStatement.setString(2, isbn);

                authorISBNStatement.executeQuery();

                out.println("<html><body>");
                out.println("<h1>New Book Added!</h1>");
                out.println("<h2>" + title + "</h2>");
                out.println("<h3> Author: " + authorFullName  + "</h3>");
                out.println("<h3> ISBN: " + isbn + "</h3>");
                out.println("<h3> Edition: " + edition + "</h3>");
                out.println("<h3> Copyright: " + copyright + "</h3>");
                out.println("</body></html>");
            }
            else{
                out.println("<html><body>");
                out.println("<h1>Author not found</h1>");
                out.println("<h1> Follow this link to add an author</h1>");
                out.println("</body></html>");
            }

        }
        catch( SQLException ex) {
            ex.printStackTrace();
            out.println("<html><body>");
            out.println("<h1>" + ex.toString() + "</h1>");
            out.println("</body></html>");
        }

    }
}
