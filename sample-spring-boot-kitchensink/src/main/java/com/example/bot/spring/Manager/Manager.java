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

		//String username = dbUri.getUserInfo().split(":")[0];
		String username = "zifqroemwxifsc";
		//String password = dbUri.getUserInfo().split(":")[1];
		String password = "ca4388ee9d116c4c5b4343e9f9934237c231fd02faa62fedb3ad375adf8aa376";
		//String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath() +  "?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";
		String dbUrl = "jdbc:postgresql://ec2-54-227-252-202.compute-1.amazonaws.com:5432/d1jr6upsvah14o?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";
		log.info("Username: {} Password: {}", username, password);
		log.info ("dbUrl: {}", dbUrl);
		
		connection = DriverManager.getConnection(dbUrl, username, password);
		return connection;
	}
	
	public ResultSet SelectionQuery(String sqlStatement ) throws Exception { 
		
		ResultSet result = null ; 
		PreparedStatement stmt = null; 
		Connection connection = null ;
		
		try {
			connection = getConnection() ; 
		} catch (SQLException e) {
			log.info("SQLException while connecting to sql server: {}", e.toString());
		}
		
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
