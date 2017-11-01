package com.example.bot.spring;

import java.util.*;
import lombok.extern.slf4j.Slf4j;
import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.*;
import java.net.URISyntaxException;
import java.io.IOException;
import java.net.URI;

public class UserInterface {
	
	private String message ;
	private Manager manager ; 
	public String getMessage() { return message ; }
	public abstract void showMessage() {}
	private void processInput(String userReply) ; 
}
