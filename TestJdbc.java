package src.com.jdbc;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Enumeration;

public class TestJdbc {

	public static void main(String[] args) {
		
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:";
		String user ="SYSTEM";
		String password = "root";
		
		try {
			System.out.println("Connecting to DAtabase");
		//	Class.forName("oracle.jdbc.OracleDriver");
			Connection connection = DriverManager.getConnection(url, user, password);
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("Select 1 from dual");
			while (resultSet.next()) {
				System.out.println(resultSet.getString(1));				
			}
		      System.out.println("Connection Success");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
