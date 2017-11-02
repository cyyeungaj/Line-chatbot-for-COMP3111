package com.example.bot.spring;

import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.*;
import java.net.URISyntaxException;
import java.io.IOException;
import java.net.URI;
import java.util.*;


public class JDBCClientManager extends clientManager {
	public Client getClientByLineUserId(String lineId) { 
		String SQLStatement = "SELECT * from Customer WHERE ID=" + lineId + ";";
		Client result = null;
		try {
			ResultSet rs = SelectionQuery(SQLStatement);
			result = new Client(rs.getString("ID"), rs.getString("Name"), rs.getInt("Phone number"), rs.getInt("age"));
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;

	}
}
