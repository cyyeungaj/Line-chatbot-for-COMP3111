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
	public Client getClientByLineUserId(String lineId) { return null;} 	
}
