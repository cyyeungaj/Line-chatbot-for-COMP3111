package com.example.bot.spring;

import lombok.extern.slf4j.Slf4j;
import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.*;
import java.net.URISyntaxException;
import java.io.IOException;
import java.net.URI;

@Slf4j
public class SQLDatabaseEngine extends DatabaseEngine {
	@Override
	String search(String text) throws Exception {
		//Write your code here
		String result = null ; 
		String SQLstatement = "SELECT reply FROM keyReply WHERE keyword like concat('%' , ? , '%')" ; 
		Connection connection = null; 
		PreparedStatement stmt = null; 
		
		try {
			connection = getConnection() ; 
		} catch (SQLException e) {
			log.info("SQLException while connecting to sql server: {}", e.toString());
		}
		
		
		try {
			stmt = connection.prepareStatement(SQLstatement); 
			stmt.setString(1,text) ; 
			ResultSet rs = stmt.executeQuery() ;
			if(rs.next()) result = rs.getString("reply") ; 
		} catch (SQLException e) {
			log.info("SQLException while loading the sql statement to sql server: {}", e.toString());
		} finally {
				if(connection != null) {
					try {
						connection.close() ;
					} catch (SQLException e ) {
						log.info("SQLException when connection was closed ") ;
					}
				}
				
				if(stmt != null) {
					try {
						stmt.close() ; 
					} catch (SQLException e ) {
						log.info("SQLException when sql statement was closed ") ; 
					}	
				}
		}
		if(result != null ) return result ; 
		throw new Exception("NOT FOUND");
	}
	
	
	private Connection getConnection() throws URISyntaxException, SQLException {
		Connection connection;
		URI dbUri = new URI(System.getenv("DATABASE_URL"));

		String username = dbUri.getUserInfo().split(":")[0];
		String password = dbUri.getUserInfo().split(":")[1];
		String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath() +  "?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";

		log.info("Username: {} Password: {}", username, password);
		log.info ("dbUrl: {}", dbUrl);
		
		connection = DriverManager.getConnection(dbUrl, username, password);

		return connection;
	}

}
