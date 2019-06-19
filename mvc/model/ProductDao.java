package com.lti.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.lti.exception.DataAcessException;

public class ProductDao {

	public List<Product> fetchProducts(int from , int to)throws DataAcessException{

			Connection conn=null;	//manages the connection between app and database
			PreparedStatement stmt=null;	//prepare a statement
			ResultSet rs=null;
			try {
				
				Class.forName("oracle.jdbc.driver.OracleDriver");	//loading driver
				
				//Establish Connection with the database
				conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","hr","hr");
				String sql="select id,name,price,quantity"+
				        " from(" +
						" select" +
				        " t.*," +
						" row_number() over (order by ID) r" +
						" from" +
						" TB_PRODUCT t" +
						" )" +
						" where" +
						" r between ? and ?";
						
				stmt=conn.prepareStatement(sql);
				
		
		
		stmt.setInt(1,from);
		stmt.setInt(2, to);
		
		rs=stmt.executeQuery();				//executing statement and storing result in rs
		
		List<Product> products=new ArrayList<Product>();
		while(rs.next()) {
			Product p=new Product();
			p.setId(rs.getInt(1));
			p.setName(rs.getString(2));
			p.setPrice(rs.getDouble(3));
			p.setQuantity(rs.getInt(4));
			products.add(p);
			}
		return products;
	}
	catch(ClassNotFoundException e){
		
		throw new DataAcessException("unable to load the JDBC driver");
		
	}
	catch(SQLException e) {
		throw new DataAcessException("problem while fetching the product from db",e);
	}
	finally {
		
		try {
			conn.close();
		}
		catch(Exception e) {
			
		}
	}
}
}





































