package com.example.servletproject;
import java.sql.*;
import java.io.*;
import java.sql.SQLException;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet(name = "AddBike", value = "/AddBike")
public class AddBikeServlet extends HttpServlet {


    public void doPost(HttpServletRequest request,
                      HttpServletResponse response) throws IOException {

        String name = request.getParameter("name");
        String speeds = request.getParameter("speeds");
        PrintWriter out = response.getWriter();
        try {
            Connection conn = getBicyclesDatabaseConnection();
            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO bicycles(name, speeds) VALUES (?, ?)");

            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, Integer.parseInt(speeds));

            preparedStatement.executeQuery();

            out.println("<html><body>");
            out.println("<h1>" + name + "</h1>");
            out.println("<h2>" + speeds + "</h2>");
            out.println("</body></html>");

        }
        catch( SQLException ex) {
            ex.printStackTrace();
            out.println("<html><body>");
            out.println("<h1>" + ex.toString() + "</h1>");
            out.println("</body></html>");
        }

    }

    public Connection getBicyclesDatabaseConnection() throws SQLException {
        try {
            Class.forName("org.mariadb.jdbc.Driver");

        }
        catch( ClassNotFoundException ex) {
            ex.printStackTrace();
        }

        return DriverManager.getConnection("jdbc:mariadb://localhost:3306/bike_collection", "root", "root");

    }
}
