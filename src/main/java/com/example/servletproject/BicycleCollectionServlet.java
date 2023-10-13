package com.example.servletproject;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

@WebServlet(name = "BikeCollection", value = "/BikeCollection")
public class BicycleCollectionServlet extends HttpServlet {

    public void doGet(HttpServletRequest request,
                       HttpServletResponse response) throws IOException, ServletException {

        LinkedList<Bicycle> bicycleList = new LinkedList<Bicycle>();
        try {
            Connection conn = DatabaseConnection.getBicyclesDatabaseConnection();
            Statement statement = conn.createStatement();
            String sqlQuery = "SELECT * from bicycles";
            ResultSet resultSet = statement.executeQuery(sqlQuery);

            while( resultSet.next()) {
                bicycleList.add( new Bicycle(resultSet.getString(2), resultSet.getInt(3)));
            }
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("bicycles_display.jsp");
            request.setAttribute("bicycleList", bicycleList);
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