package com.example.servletproject;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(name = "BikeCollection", value = "/BikeCollection")
public class BicycleCollectionServlet extends HttpServlet {

    public void doGet(HttpServletRequest request,
                       HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        try {
            Connection conn = DatabaseConnection.getBicyclesDatabaseConnection();
            Statement statement = conn.createStatement();
            String sqlQuery = "SELECT * from bicycles";
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            out.println("<html><body>");
            while( resultSet.next()) {
                out.println("<h2> Bike: " + resultSet.getString(2) + "</h2>");
                out.println("<h3> Speeds: " + resultSet.getInt(3) + "</h3>");
                out.println("<br>");
            }
            out.println("</body></html>");
        }
        catch( SQLException ex) {
            ex.printStackTrace();
            out.println("<html><body>");
            out.println("<h1>" + ex.toString() + "</h1>");
            out.println("</body></html>");
        }
    }
}