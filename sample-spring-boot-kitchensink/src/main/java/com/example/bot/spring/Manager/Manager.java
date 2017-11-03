package com.example.bot.spring;

import lombok.extern.slf4j.Slf4j;
import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.*;
import java.net.URISyntaxException;
import java.io.IOException;
import java.net.URI;

@Slf4j
public class Manager {
	private Connection connection ;
	
	public Manager ( ) {
		try {
			this.setConnection () ;
		} catch( Exception e) {
			log.info("Exception occur in manager constructor on setConnection statement") ; 
		}
		
	}
	
	protected void setConnection () throws URISyntaxException, SQLException{
		Connection connection;
		URI dbUri = new URI(System.getenv("DATABASE_URL"));

		String username = dbUri.getUserInfo().split(":")[0];
		String password = dbUri.getUserInfo().split(":")[1];
		String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath() +  "?ssl=true&sslfactory=org.postgresql.ssl.NonValidatingFactory";

		log.info("Username: {} Password: {}", username, password);
		log.info ("dbUrl: {}", dbUrl);
		
		connection = DriverManager.getConnection(dbUrl, username, password);
		this.connection = connection ; 
	}
	
	
	public ResultSet SelectionQuery(String sqlStatement ) throws Exception { 
		
		ResultSet result = null ; 
		Connection connection = this.connection;
		PreparedStatement stmt = null; 
	
		try {
			stmt = connection.prepareStatement(sqlStatement);
			ResultSet rs = stmt.executeQuery() ; 
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
	
	protected void insertDeleteQuery(String sqlStatement) {
		Connection connection = this.connection;
		PreparedStatement stmt = null;

		try{
			stmt = connection.prepareStatement(sqlStatement);
			stmt.execute();
		}catch(SQLException e){
			log.info("SQLException while loading the sql statement to sql server: {}", e.toString());
		}finally {
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

	}
}
