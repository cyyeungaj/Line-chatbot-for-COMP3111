package com.example.bot.spring;

import java.util.*;
import lombok.extern.slf4j.Slf4j;
import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.*;
import java.net.URISyntaxException;
import java.io.IOException;
import java.net.URI;

public abstract class clientManager extends Manager {
	public abstract Client getClientByLineUserId(String lineId);	
}
