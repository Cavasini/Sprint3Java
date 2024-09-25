package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import connection.ConnectionProperties;

public class Test {
	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		
		String url = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL";
		
		try {
			conn = DriverManager.getConnection(url, ConnectionProperties.getConnection());
			
			stmt = conn.createStatement();
			
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}	
}
