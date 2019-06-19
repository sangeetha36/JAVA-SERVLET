package com.servelt.assesment.practice;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class Dmanager {
	
	boolean active;
	
	public void register(String username,String password,String email,String phno,String gender) {
		int count=0;
		Connection conn=null;
		ResultSet rs=null;
	   PreparedStatement stmt=null;
	   try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			String url="jdbc:oracle:thin:@localhost:1521:XE";
			String user="hr";
			String pass="hr";
			conn=DriverManager.getConnection(url, username, password);
			String str="insert into register values(?,?,?,?,?); '";
			  stmt=conn.prepareStatement(str);
			  stmt.setString(1,username);
			  stmt.setString(2,password );
			  stmt.setString(3, email);
			  stmt.setString(4,phno);
			  stmt.setString(5,gender);
			  
			}
			  
				
			
			catch(ClassNotFoundException e) {
				System.out.println("JDBC driver not found");
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
	}
}
			





