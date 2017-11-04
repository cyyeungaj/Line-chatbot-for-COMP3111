package com.example.bot.spring;


import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.ArrayList ; 
import java.util.Map ; 

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;

import com.google.common.io.ByteStreams;

import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.ReplyMessage;
import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.FollowEvent;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.message.MessageContent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.spring.boot.annotation.LineBotMessages;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import com.example.bot.spring.DatabaseEngine;


import lombok.extern.slf4j.Slf4j;
import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.*;
import java.net.URISyntaxException;
import java.io.IOException;
import java.net.URI;

import lombok.extern.slf4j.Slf4j;
import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.*;
import java.net.URISyntaxException;
import java.io.IOException;
import java.net.URI;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = { KitchenSinkTester.class, SQLDatabaseEngine.class , JDBCFaqManager.class})
@Slf4j
public class KitchenSinkTester {
	@Autowired
	private DatabaseEngine databaseEngine;
	
	@Test
	public void testNotFound() throws Exception {
		/*
		try {
			this.databaseEngine.search("no");
		} catch (Exception e) {
			thrown = true;
		}
		assertThat(thrown).isEqualTo(true);
		*/
	}
	
	@Test
	public void testforgetAllFAQ () throws Exception {
		JDBCFaqManager manager = new JDBCFaqManager() ; 
		ArrayList<FAQ> results ;
		results = manager.getAllFAQ() ; 
		assertThat(results.get(0).getQuestionNum()).isEqualTo(1) ; 
		assertThat(results.get(0).getQuestion()).isEqualTo("How to apply?");
		//assertThat(results.get(1))
	}
	
	@Test
	public void testforInterfaceProcess () {
		
		log.info("Entry point of testforInterfaceProcess\n") ;
		chatbotController controller = new chatbotController() ;
		log.info("User type 3\n") ;
		controller.processInput("3" , null) ;
		
		log.info("interface Output:\n") ; 
		log.info(controller.getCurrentInterfaceMessage()) ;
		
		log.info("User type no\n") ;
		controller.processInput("no" , null) ;
		log.info("interfaceOutput:\n") ;
		log.info(controller.getCurrentInterfaceMessage()) ;

		log.info("User type 5 to look for answer of question 5\n") ;
		controller.processInput("5" , null) ;
		log.info("interfaceOutput:\n") ;
		log.info(controller.getCurrentInterfaceMessage()) ;

	}
	
	@Test
	public void testForFAQString () {
		chatbotController controller = new chatbotController() ; 
		controller.processInput("3" , null) ; 
		log.info(controller.getCurrentInterfaceMessage()) ; 
		log.info("test done in testForFAQString ()") ; 
	}
	
	@Test
	public void testForGetQuestionString() {
		log.info("entry point of testForGetQuestionString") ; 
		log.info(new JDBCFaqManager().getQuestionString()) ; 
		log.info("end point of testForGetQuestionString") ; 
	}
	
	
	
	@Test
	public void testFound() throws Exception {
		
		
		
		/*
		int NoOfCase = 5 ; 
		boolean thrown = false;
		String result = null;
		boolean []thrownList =  new boolean[NoOfCase] ;
		
		HashMap<String, String> mp = new HashMap<String , String> () ; 
		for(int i = 0 ; i < NoOfCase ; i ++ ) {
			thrownList[i] = false  ;
		}
		
		mp.put("abc" , "def") ;
		mp.put("Hi" , "Hey, how things going?") ; 
		mp.put("I am fine" , "Great!") ;
		mp.put("Who is Prof Kim" , "Well, this is your instructor.") ; 
		mp.put("How is the grade of this course" , "This is absolute good grade for good student. And I am sure you are!") ;
		
		
		int counter = 0 ; 
		for(Map.Entry<String , String> m : mp.entrySet()) {
			try {
				String target = m.getKey() ; 
				result  = this.databaseEngine.search(target) ; 
			} catch ( Exception e) {
				thrownList[counter] = true ; 
			}
			String value = m.getValue() ; 
			assertThat(!thrownList[counter]).isEqualTo(true) ; 
			assertThat(result).isEqualTo(value) ;
			counter ++ ; 
		}
		*/
		
		/*
		 stmt = connection.prepareStatement(SQLstatement); 
			stmt.setString(1,text) ; 
			ResultSet rs = stmt.executeQuery() ;
			if(rs.next()) result = rs.getString("reply") ;
		*/
		Manager manager = new JDBCFaqManager() ; 
		ResultSet rs = null ;
		Connection connection = SQLDatabaseEngine.getConnection() ; 
		PreparedStatement stmt = connection.prepareStatement("SELECT * from faq;") ; 
		try{
			rs=manager.SelectionQuery("SELECT * from faq;"); 
		} catch( Exception e ) {
			log.info("Exception occur rs = manager.SelectionQuery(\"SELECT * from faq") ; 	
		}
		int currentNo = 0 ; 
		String q = null ; 
		String a = null ; 
		if(rs.next()) {
			currentNo = rs.getInt("question_no") ; 
			q = rs.getString("question") ;
			a = rs.getString("answer") ; 
		}
		/*
		String result = null ; 
		try {
			result = this.databaseEngine.faq() ; 
		} catch (Exception e) {
			log.info("Exception occur in result = this.databaseEngine.faq() ") ; 	
		}*/
		
		assertThat(q).isEqualTo("How to apply?") ;
		assertThat(currentNo).isEqualTo(1) ; 
		//assertThat(a).isEqualTo("Customers shall approach the company by phone or visit our store (in Clearwater bay) with the choosen tour code and departure date. If it is not full, customers will be advised by the staff to pay the tour fee. Tour fee is non refundable. Customer can pay their fee by ATM to 123-345-432-211 of ABC Bank or by cash in our store. Customer shall send their pay-in slip to us by email or LINE.");
	
	}
	
	
		
		
	
	
	
	
	
}
