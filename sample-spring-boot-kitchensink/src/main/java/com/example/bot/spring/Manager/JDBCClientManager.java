package com.example.bot.spring;

import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.*;
import java.net.URISyntaxException;
import java.io.IOException;
import java.net.URI;
import java.util.*;

@Slf4j
public class JDBCClientManager extends clientManager {
	public Client getClientByLineUserId(String lineId) { 
		String SQLStatement = "SELECT * from customer WHERE customer_id=" + lineId + ";";
		Client result = null;
		try {
			ResultSet rs = SelectionQuery(SQLStatement);
			result = new Client(rs.getString("customer_id") , rs.getString("name"), rs.getInt("phone_no"), rs.getInt("age"));
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;

	}
}
