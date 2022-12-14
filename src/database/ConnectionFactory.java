package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	
	public static Connection getConnection
							(
								final String enderecoIP,
								final String porta,
								final String nomeBanco,
								final String usuario,
								final String senha
							) throws SQLException {
		
		return DriverManager.getConnection
				(
					"jdbc:postgresql://"+enderecoIP+":"+porta+"/"+nomeBanco,
					usuario,
					senha
				);
		
	}
}










