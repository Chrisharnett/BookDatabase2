package com.example.servletproject;
import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet(name = "recipes", value = "/recipe-list")
public class MyServlet1 extends HttpServlet {
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response) {
        try{

            // create a database connection

            //query all the recipes

            //create a list of all recipes


            // take my recipe list and send it to a java server page





            response.setContentType("text/html");
            PrintWriter pwriter = response.getWriter();

            String name = request.getParameter("userName");
            String password = request.getParameter("userPassword");
            pwriter.print("Hello "+name);
            pwriter.print("Your Password is: "+password);

            //Creating two cookies
            Cookie c1=new Cookie("userName",name);
            Cookie c2=new Cookie("userPassword",password);

            //Adding the cookies to response header
            response.addCookie(c1);
            response.addCookie(c2);
            pwriter.print("<br><a href='welcome'>View Details</a>");
            pwriter.close();
        }catch(Exception exp){
            System.out.println(exp);
        }
    }
}