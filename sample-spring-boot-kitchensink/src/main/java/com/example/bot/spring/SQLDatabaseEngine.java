package com.example.bot.spring;

import lombok.extern.slf4j.Slf4j;
import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.*;
import java.net.URISyntaxException;
import java.net.URI;

@Slf4j
public class SQLDatabaseEngine extends DatabaseEngine {
	private final String TABLE_NAME = "auto_reply_list";
	private final String RESPONSE = "response";
	private final String KEYWORD = "keyword";
	private final String preparedStatement = "select "
			.concat(RESPONSE)
			.concat(" from ")
			.concat(TABLE_NAME)
			.concat(" where ") 
			.concat(KEYWORD)
			.concat(" like concat('%', ?, '%') limit 1");
	@Override
	String search(String text) throws Exception {
		//Write your code here

		Connection connection = null;
		ResultSet rs = null;
		PreparedStatement stmt = null;
		String result = null;
		try {
			connection = getConnection();
			stmt = connection.prepareStatement(preparedStatement);
			stmt.setString(1, text);
		
			rs = stmt.executeQuery();	
			
			if(rs.next())
				result = rs.getString(1);
			else
				System.out.println("result not found");
			
		}catch(Exception e) {
			System.out.println("search exception");
			System.out.println(e);
		}finally{
			rs.close();
			stmt.close();
			connection.close();	
		}
		
		if(result != null)
			return result;
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
//		connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/chatbotDB", "programmer", "iamaprogrammer");
		return connection;
	}

}
