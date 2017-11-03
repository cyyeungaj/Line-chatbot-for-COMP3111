package com.example.bot.spring;

import lombok.extern.slf4j.Slf4j;
import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.*;
import java.net.URISyntaxException;
import java.io.IOException ; 
import java.net.URI;

@Slf4j
public class Manager {
	
	public Manager ( ) {
		
	}
	
	protected void setConnection () throws URISyntaxException, SQLException{
		Connection connection;
		URI dbUri = new URI(System.getenv("DATABASE_URL"));

		String username = dbUri.getUserInfo().split(":")[0];
		String password = dbUri.getUserInfo().split(":")[1];
		String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath() +  "?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";

		log.info("In setConnection Username: {} Password: {}", username, password);
		log.info ("dbUrl: {}", dbUrl);
		
		connection = DriverManager.getConnection(dbUrl, username, password);
		//this.connection = connection ; 
	}
	
	protected Connection getConnection () throws URISyntaxException, SQLException{
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
	
	public ResultSet SelectionQuery(String sqlStatement ) throws Exception { 
		
		ResultSet result = null ; 
		PreparedStatement stmt = null; 
		Connection connection = getConnection() ; 
		try {
			stmt = connection.prepareStatement(sqlStatement);
			result = stmt.executeQuery() ; 
		} catch (SQLException e) {
			log.info("SQLException while loading the sql statement to sql server: {}", e.toString()); 
		} 
		
		
		if(result != null ) return result ; 
		throw new Exception("NOT FOUND");
}
	
	protected void insertDeleteQuery(String sqlStatement) throws URISyntaxException, SQLException {
		Connection connection = getConnection();
		PreparedStatement stmt = null;

		try{
			stmt = connection.prepareStatement(sqlStatement);
			stmt.execute();
		}catch(SQLException e){
			log.info("SQLException while loading the sql statement to sql server: {}", e.toString());
		}

	}
}
