package com.example.bot.spring;

import lombok.extern.slf4j.Slf4j;
import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.*;
import java.net.URISyntaxException;
import java.io.IOException;
import java.net.URI;
import java.util.*;

public class Manager {
	private Connection connection ;
	
	
	public void setConnection ( Connection connection ) {
		this.connection = connection ; 
	} 
	
	public ResultSet SelectionQuery(String sqlStatement ) { 
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

	} ;
	private void insertDeleteQuery(String sqlStatement) {
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
