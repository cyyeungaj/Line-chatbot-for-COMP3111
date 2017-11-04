package com.example.bot.spring;

import lombok.extern.slf4j.Slf4j;
import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.*;
import java.net.URISyntaxException;
import java.io.IOException;
import java.net.URI;
import java.util.*;


public abstract class faqManager extends Manager{
	public abstract ArrayList<FAQ> getAllFAQ();
	public abstract boolean insertFAQ(FAQ faq);
	public abstract String getAnswerByQuestionNo ( int questionNo ) ; 
	public abstract String getQuestionString() ; 
}
