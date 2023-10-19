package com.example.servletproject;
import java.sql.*;
import java.io.*;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

/**
 * @author saxDev
 * studentnumber 20188141
 **/

@WebServlet(name = "AuthorData", value = "/AuthorData")
public class AuthorData extends HttpServlet {

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) throws IOException, ServletException {
//        LinkedList<Book> bookList = new LinkedList<>();
        LinkedList<Author> authorList = new LinkedList<>();
        try (Connection conn = DatabaseConnection.initDatabase()){

            Statement statement = conn.createStatement();
            // Get all the authors
            ResultSet authorsResultSet = statement.executeQuery("SELECT * FROM authors");
            while (authorsResultSet.next()) {
                Author author = new Author(authorsResultSet.getInt("authorid"), authorsResultSet.getString("firstName"),
                        authorsResultSet.getString("lastName"));
                authorList.add(author);
            }
            String createTitleList = "SELECT t.title, t.isbn, t.editionNumber, t.copyright  " +
                                    "FROM authors a JOIN authorISBN i ON(a.authorID = i.authorID)" +
                                    "JOIN titles t using(isbn)" +
                                    "WHERE i.authorId = ?";

            // Loop through authors, get matching books and add them to the author.setBooklist
            for (Author author : authorList) {
                PreparedStatement pstmt = conn.prepareStatement(createTitleList);
                pstmt.setInt(1, author.getId());

                // Get book titles
                ResultSet titleResultSet = pstmt.executeQuery();
                List<Book> titlesByAuthor = new LinkedList<>();
                while (titleResultSet.next()) {
                    Book b = new Book(titleResultSet.getString("isbn"),
                                    titleResultSet.getString("title"),
                                    titleResultSet.getInt("editionNumber"),
                                    titleResultSet.getString("copyright"));
                    titlesByAuthor.add(b);
                }
                author.setBookList(titlesByAuthor);
            }
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("authors_display.jsp");
            request.setAttribute("authorList", authorList);
            requestDispatcher.forward(request, response);
        }
        catch( SQLException ex) {
            PrintWriter out = response.getWriter();
            out.println("<html><body>");
            out.println("<h1>" + ex + "</h1>");
            out.println("</body></html>");
        }
    }
    public void doPost(HttpServletRequest request,
                       HttpServletResponse response) throws IOException {
        String lastName = request.getParameter("lastName");
        String firstName = request.getParameter("firstName");

        PrintWriter out = response.getWriter();

        try (Connection conn = DatabaseConnection.initDatabase()){
            PreparedStatement newAuthorStatement = conn.prepareStatement("INSERT INTO authors VALUES (default, ?, ?)");
            newAuthorStatement.setString(1, firstName);
            newAuthorStatement.setString(2, lastName);
            newAuthorStatement.executeQuery();

            RequestDispatcher requestDispatcher = request.getRequestDispatcher("author_added.jsp");
            request.setAttribute("firstName", firstName);
            request.setAttribute("lastName", lastName);
            requestDispatcher.forward(request, response);

        }
        catch(SQLException | ServletException ex) {
            ex.printStackTrace();
            out.println("<html><body>");
            out.println("<h1>" + ex + "</h1>");
            out.println("</body></html>");
        }
    }
}
