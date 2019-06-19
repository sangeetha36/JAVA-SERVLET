package com.servelet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		String phno = request.getParameter("phno");
		String gender = request.getParameter("gender");
		
		Connection conn=null; 
		PreparedStatement stmt=null;
		try
		{
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","hr","hr");
			
			Statement st = conn.createStatement();
			String qry = "insert into user1 values (username = ? and password = ? and email = ? and phno = ? and gender = ?) ";
			//String qry="insert into user1 values ('"+username+"','"+password+"','"+email+"','"+phno+"','"+gender+"')";
			stmt = conn.prepareStatement(qry);
			
			stmt.setString(1, username);
			stmt.setString(2, password);
			stmt.setString(3, email);
			stmt.setString(4, phno);
			stmt.setString(4, gender);
			
			
			
			System.out.println(qry);
			int i=st.executeUpdate(qry);
			if(i>0)
			{
				System.out.println("Success");
			}
			else
			{
				System.out.println("Try Again");
			}
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
		
		
	
