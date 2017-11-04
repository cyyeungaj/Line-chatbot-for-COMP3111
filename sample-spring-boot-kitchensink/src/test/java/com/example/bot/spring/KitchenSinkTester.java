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
		
		log.info("User type no") ;
		controller.processInput("no" , null) ;
		log.info("interfaceOutput:") ;
		log.info(controller.getCurrentInterfaceMessage()) ;

		log.info("User type 5 to look for answer of question 5\n") ;
		controller.processInput("5" , null) ;
		log.info("interfaceOutput:") ;
		log.info(controller.getCurrentInterfaceMessage()) ;
		
		log.info("User type 1 \n") ;
		controller.processInput("1",null) ; 
		log.info("interfaceOutput:") ;
		log.info(controller.getCurrentInterfaceMessage()) ;
		
		log.info("leave point of testforInterfaceProcess()") ; 
	}
	
	@Test
	public void testForFAQString () {
		log.info("entry point of testForFAQString") ;
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
		
	
	}
	
	
		
		
	
	
	
	
	
}
