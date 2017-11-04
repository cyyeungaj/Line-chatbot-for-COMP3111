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
		super() ; 
	}
	
	public String getAnswerByQuestionNo ( int questionNo ) {
		String sqlStatement = "SELECT * FROM "  + TABLE_NAME +
							  " WHERE question_no = " +  questionNo ; 
		ResultSet rs = null ;
		StringBuilder builder = new StringBuilder() ; 
		try {
			rs = SelectionQuery(sqlStatement);
		} catch ( Exception e) {
			log.info("Exception occur in statement rs = SelectionQuery(query) on getQuestionString()") ; 
		}
		try {
			if(rs.next()) {
			int questionNum = rs.getInt(COL_QUESTION_NUM);
			String question = rs.getString(COL_QUESTION);
			String answer = rs.getString(COL_ANSWER);
			builder.append( questionNum + ". " + question + "\n" + answer ) ; }
		} catch ( Exception e) {
			log.info("Exception occur in if(rs.next()) on getAnswerByQuestionNo ");
		}
		
		return builder.toString(); 
	}
	
	public String getQuestionString() {
		String sqlStatement = "SELECT " +  COL_QUESTION_NUM +
							  ", " + COL_QUESTION +
							  " FROM " + 
							  TABLE_NAME ; 
		ResultSet rs = null ;
		//String result = "FAQ question list \n Those are the possible question you may ask:\n" ; 
		StringBuilder builder = new StringBuilder() ; 
		try {
			rs = SelectionQuery(sqlStatement);
		} catch ( Exception e) {
			log.info("Exception occur in statement rs = SelectionQuery(query) on getQuestionString()") ; 
		}
		
		try {
			while(rs.next()) {
				int questionNum = rs.getInt(COL_QUESTION_NUM);
				String question = rs.getString(COL_QUESTION);
				builder.append(questionNum + ". ") ; 
				builder.append(question + "\n") ; 
			}
		} catch (Exception e ) {
			log.info("Exception occur in statement while(rs.next()) on getQuestionString()") ; 
		}
		builder.append("\n") ; 
		return builder.toString() ; 
		
	}
	
	public int getNumOfFAQ() {
		if(results == null)
			return 0;
		return results.size();
	}
	
	public ArrayList<FAQ> getAllFAQ(){
		if(results == null) {
			results = new ArrayList<FAQ>();
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
			try{
				insertDeleteQuery(sqlStatement) ; 
			} catch(Exception e) {
				
			}
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
