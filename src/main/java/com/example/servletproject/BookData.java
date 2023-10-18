package com.example.servletproject;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author saxDev
 * studentnumber 20188141
 **/

@WebServlet(name = "BookData", value = "/BookData")
public class BookData extends HttpServlet{
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws IOException, ServletException {

        LinkedList<Book> bookList = new LinkedList<Book>();
        LinkedList<Author> authorList = new LinkedList<>();
        try {
            Connection conn = DatabaseConnection.initDatabase();
            Statement statement = conn.createStatement();
            String sqlQuery = "SELECT * from titles";
            ResultSet resultSet = statement.executeQuery(sqlQuery);

            while (resultSet.next()) {
                bookList.add(new Book(resultSet.getString(1), resultSet.getString(2), resultSet.getInt(3), resultSet.getString(4)));
            }
            ResultSet authorsResultSet = statement.executeQuery("SELECT * FROM authors");
            while (authorsResultSet.next()) {
                Author author = new Author(authorsResultSet.getInt("authorid"), authorsResultSet.getString("firstName"),
                        authorsResultSet.getString("lastName"));
                authorList.add(author);
            }
            String createAuthorList = "SELECT a.authorID, a.firstName, a.lastName " +
                    "FROM authors a JOIN authorISBN i ON(a.authorID = i.authorID)" +
                    "JOIN titles t using(isbn)" +
                    "WHERE i.isbn = ?";
            for (Book book : bookList) {
                PreparedStatement pstmt = conn.prepareStatement(createAuthorList);
                pstmt.setString(1, book.getISBN());
                ResultSet results = pstmt.executeQuery();
                List<Author> bookAuthors = new ArrayList<>();
                while (results.next()) {
                    for (Author author : authorList) {
                        if (author.getId() == results.getInt("authorID")) {
                            bookAuthors.add(author);
                        }
                    }
                }
                book.setAuthorList(bookAuthors);
            }
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("books_display.jsp");
            request.setAttribute("bookList", bookList);
            requestDispatcher.forward(request, response);
        }
        catch( SQLException ex) {
            PrintWriter out = response.getWriter();
            out.println("<html><body>");
            out.println("<h1>" + ex.toString() + "</h1>");
            out.println("</body></html>");
        }
    }

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
