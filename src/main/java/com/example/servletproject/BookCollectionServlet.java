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

@WebServlet(name = "BookCollection", value = "/BookCollection")
public class BookCollectionServlet extends HttpServlet {

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
}