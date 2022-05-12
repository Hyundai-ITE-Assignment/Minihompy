package com.hyundai.minihompy.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class DataSourceTests {	
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}//end static
	
	@Test
	public void TestConnection() {
		try {
			Connection con = 
					DriverManager.getConnection(
							"jdbc:oracle:thin:@db202204041648_high?TNS_ADMIN=C:/dev/OracleWallet/Wallet_DB202204041648"
							,"HYUNDAI_MINI","Hyundai220512");
			System.out.println("con: " + con);
		} catch (SQLException e) {
			System.out.println(e.getMessage());			
		}//end try			
	}//end test
}//end class
