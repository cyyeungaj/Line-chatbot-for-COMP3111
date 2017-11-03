package com.example.bot.spring;

import lombok.extern.slf4j.Slf4j;
import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.*;
import java.net.URISyntaxException;
import java.io.IOException;
import java.net.URI;
import java.util.*;
import java.lang.StringBuilder;

@Slf4j
public class JDBCFaqManager extends faqManager {
	private final String TABLE_NAME = "faq";
	private final String COL_QUESTION_NUM = "question_no";
	private final String COL_QUESTION = "question";
	private final String COL_ANSWER = "answer";
	private ArrayList<FAQ> results = null;
	
	public JDBCFaqManager () {
		
	}
	
	public int getNumOfFAQ() {
		if(results == null)
			return 0;
		return results.size();
	}
	
	public ArrayList<FAQ> getAllFAQ(){
		if(results == null) {
			results = new ArrayList<>();
			String query = "SELECT * FROM " + TABLE_NAME;
			
			ResultSet rs = null ; 
			try {
				rs = SelectionQuery(query);

			} catch ( Exception e ) {
				log.info("Exception occur in statement rs=SelectQuery in getAllFAQ function") ; 
			}
			
			try {
				while(rs.next()) {
					int questionNum = rs.getInt(COL_QUESTION_NUM);
					String question = rs.getString(COL_QUESTION);
					String answer = rs.getString(COL_ANSWER);
					
					FAQ faq = new FAQ(question, answer);
					faq.setQuestionNum(questionNum);
					results.add(faq);
				}
			}catch(Exception e){
				//something go wrong
				return null;
			}
		}
		
		return results;
	}
	
	public boolean insertFAQ(FAQ faq) {
		String question = faq.getQuestion();
		String answer = faq.getAnswer();
		if(question != null & answer != null ) {
			String sqlStatement = "INSERT INTO " + TABLE_NAME 
					+ "(" 
					+ COL_QUESTION + ", " 
					+ COL_ANSWER + ")"
					+ " VALUES ('" 
					+ question 
					+ "', '"
					+ answer 
					+ "');";
			insertDeleteQuery(sqlStatement);
			return true;
		}else {
			return false;
		}
	}
	
	public String getAllFAQString() {
		ArrayList<FAQ> arrayList = results;
		if(arrayList == null)
			arrayList = getAllFAQ();
		if(arrayList == null)
			return null;
		
		StringBuilder faqBuilder = new StringBuilder();
		faqBuilder.append("FAQ\n\n");
		for(FAQ faq : arrayList) {
			faqBuilder.append(faq.getQuestion() + "\n");
			faqBuilder.append(faq.getAnswer() + "\n\n");
		}
		
		return faqBuilder.toString();
	}
	
}
