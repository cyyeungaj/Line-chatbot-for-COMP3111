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


import java.io.FileInputStream;
import java.io.IOException;
//import java.io.InputStream;
import java.io.ByteArrayInputStream;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
 import java.nio.charset.StandardCharsets;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;



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
	public void testforFAQInterfaceProcess () {
		
		log.info("Entry point of testforFAQInterfaceProcess\n") ;
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
	public void testforSearchingInterfaceProcessInSearchingTime () {
		log.info("Entry point of testforSearchingInterfaceProcess\n") ;
		chatbotController controller = new chatbotController() ;
		log.info(controller.getCurrentInterfaceMessage()) ;
		//assertThat(controller.getCurrentInterfaceMessage()).isEqualTo(new MenuInterface().)
		log.info("User type 2\n") ;
		controller.processInput("2" , null) ;
		log.info(controller.getCurrentInterfaceMessage()) ;
		
		
		log.info("User type 1\n") ;
		controller.processInput("1" , null) ;
		log.info("interfaceOutput:") ;
		log.info(controller.getCurrentInterfaceMessage()) ;

		//(YYYY-MM-DD)
		log.info("user type 2017-11-15") ; 
		controller.processInput("2017-11-15" , null) ;
		log.info("interfaceOutput:") ;
		log.info(controller.getCurrentInterfaceMessage()) ;

		log.info("user type 2017-11-25") ; 
		controller.processInput("2017-11-25" , null) ;
		log.info("interfaceOutput:") ;
		log.info(controller.getCurrentInterfaceMessage()) ;

		log.info("user type yes") ; 
		controller.processInput("yes" , null) ;
		log.info("interfaceOutput:") ;
		log.info(controller.getCurrentInterfaceMessage()) ;

	}
	
	
	@Test
	public void testforSearchingInterfaceProcessInSearchingPlace () {
		
		log.info("Entry point of testforSearchingInterfaceProcessInSearchingPlace\n") ;
		chatbotController controller = new chatbotController() ;
		log.info(controller.getCurrentInterfaceMessage()) ;
		//assertThat(controller.getCurrentInterfaceMessage()).isEqualTo(new MenuInterface().)
		log.info("User type 2\n") ;
		controller.processInput("2" , null) ;
		log.info(controller.getCurrentInterfaceMessage()) ;
		
		
		log.info("User type 2\n") ;
		controller.processInput("2" , null) ;
		log.info("interfaceOutput:") ;
		log.info(controller.getCurrentInterfaceMessage()) ;

		//(YYYY-MM-DD)
		log.info("user type China") ; 
		controller.processInput("China" , null) ;
		log.info("interfaceOutput:") ;
		log.info(controller.getCurrentInterfaceMessage()) ;

		log.info("user type 1") ; 
		controller.processInput("1" , null) ; 
		
		log.info("user type 2017-11-15") ; 
		controller.processInput("2017-11-15" , null) ;
		log.info("interfaceOutput:") ;
		log.info(controller.getCurrentInterfaceMessage()) ;

		log.info("user type 2017-11-25") ; 
		controller.processInput("2017-11-25" , null) ;
		log.info("interfaceOutput:") ;
		log.info(controller.getCurrentInterfaceMessage()) ;

		log.info("user type 3") ; 
		controller.processInput("3" , null) ; 
		log.info("interfaceOutput:") ;
		log.info(controller.getCurrentInterfaceMessage()) ;
		
		log.info("user type 250") ; 
		controller.processInput("250" , null) ; 
		log.info("interfaceOutput:") ;
		log.info(controller.getCurrentInterfaceMessage()) ;

		log.info("user type 600") ; 
		controller.processInput("600" , null) ; 
		log.info("interfaceOutput:") ;
		log.info(controller.getCurrentInterfaceMessage()) ;

		log.info("user type 6") ; 
		controller.processInput("6" , null) ; 
		log.info("interfaceOutput:") ;
		log.info(controller.getCurrentInterfaceMessage()) ;

		log.info("user type 5") ; 
		controller.processInput("5" , null) ; 
		log.info("interfaceOutput:") ;
		log.info(controller.getCurrentInterfaceMessage()) ;

		log.info("user type 4") ; 
		controller.processInput("4" , null) ; 
		log.info("interfaceOutput:") ;
		log.info(controller.getCurrentInterfaceMessage()) ;

		
		
		log.info("Leave point of testforSearchingInterfaceProcessInSearchingPlace\n") ;
	}
	
	/*
	@Test
	public void testforSearchingInterfaceProcessInSearchingTime () {
		log.info("Entry point of testforSearchingInterfaceProcess\n") ;
		chatbotController controller = new chatbotController() ;
		log.info(controller.getCurrentInterfaceMessage()) ;
		//assertThat(controller.getCurrentInterfaceMessage()).isEqualTo(new MenuInterface().)
		log.info("User type 2\n") ;
		controller.processInput("2" , null) ;
		log.info(controller.getCurrentInterfaceMessage()) ;
		
		
		log.info("User type 2\n") ;
		controller.processInput("2" , null) ;
		log.info("interfaceOutput:") ;
		log.info(controller.getCurrentInterfaceMessage()) ;

		log.info("user type 2017-11-15") ; 
		controller.processInput("2017-11-15" , null) ;
		log.info("interfaceOutput:") ;
		log.info(controller.getCurrentInterfaceMessage()) ;

		log.info("user type 2017-11-25") ; 
		controller.processInput("2017-11-25" , null) ;
		log.info("interfaceOutput:") ;
		log.info(controller.getCurrentInterfaceMessage()) ;

		log.info("user type yes") ; 
		controller.processInput("yes" , null) ;
		log.info("interfaceOutput:") ;
		log.info(controller.getCurrentInterfaceMessage()) ;

	}*/

	
	
	
	@Test
	public void testTourManager () throws Exception {
		//JDBCFaqManager manager = new JDBCFaqManager() ; 
		//ArrayList<FAQ> results ;
		//results = manager.getAllFAQ() ; 
		//assertThat(results.get(0).getQuestionNum()).isEqualTo(1) ; 
		//assertThat(results.get(0).getQuestion()).isEqualTo("How to apply?");
		//assertThat(results.get(1))
		JDBCTourManager manager = new JDBCTourManager();
		int country_id = manager.getCountryId("china");
		int region_id = manager.getRegionId("taipei");
		//assertThat(country_id).isEqualTo(1);
		//assertThat(region_id).isEqualTo(1);
		
		String where = " where departure_date between '2017-11-23' and '2017-12-02' and tour_fee < 500 and region_id = 1";
		ArrayList<Tour> tours = manager.getToursByFilter(where);
		for(int i = 0; i < tours.size(); ++i){
			log.info(tours.get(i).getDepartureDate());
			assertThat(tours.get(i).getCountryId()).isEqualTo(1);
		}
		SearchingTourInterface si = new SearchingTourInterface();
		
	}
	
	
	
	@Test
	public void testForBookingInterface () {
		
		log.info("Entry point of testForBookingInterface\n") ;
		chatbotController controller = new chatbotController() ;
		log.info(controller.getCurrentInterfaceMessage()) ;
		
		log.info("User type 1\n") ;
		controller.processInput("1" , null) ;
		
		log.info("interface Output:") ; 
		log.info(controller.getCurrentInterfaceMessage()) ;
		
		log.info("User type 1\n") ;
		controller.processInput("1" , null) ;
		log.info("interface Output:") ; 
		log.info(controller.getCurrentInterfaceMessage()) ;
		
		log.info("User type 1\n") ;
		controller.processInput("1" , null) ;
		log.info("interface Output:") ; 
		log.info(controller.getCurrentInterfaceMessage()) ;
		
		log.info("User type 1\n") ;
		controller.processInput("1" , null) ;
		log.info("interface Output:") ; 
		log.info(controller.getCurrentInterfaceMessage()) ;
		
		log.info("User type 1\n") ;
		controller.processInput("1" , null) ;
		log.info("interface Output:") ; 
		log.info(controller.getCurrentInterfaceMessage()) ;
		
		log.info("User type 1\n") ;
		controller.processInput("1" , null) ;
		log.info("interface Output:") ; 
		log.info(controller.getCurrentInterfaceMessage()) ;
		
		
		log.info("User type yes\n") ;
		controller.processInput("yes" , null) ;
		log.info("interface Output:") ; 
		log.info(controller.getCurrentInterfaceMessage()) ;
		
		log.info("User type A111111\n") ;
		/*
		controller.processInput("A111111" , null) ;
		log.info("interface Output:") ; 
		log.info(controller.getCurrentInterfaceMessage()) ;
		*/
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
	
	
		@Test
	public void testTourManager () throws Exception {
		//JDBCFaqManager manager = new JDBCFaqManager() ; 
		//ArrayList<FAQ> results ;
		//results = manager.getAllFAQ() ; 
		//assertThat(results.get(0).getQuestionNum()).isEqualTo(1) ; 
		//assertThat(results.get(0).getQuestion()).isEqualTo("How to apply?");
		//assertThat(results.get(1))
		JDBCTourManager manager = new JDBCTourManager();
		int country_id = manager.getCountryId("china");
		int region_id = manager.getRegionId("taipei");
		//assertThat(country_id).isEqualTo(1);
		//assertThat(region_id).isEqualTo(1);
		
		String where = " where departure_date between '2017-11-23' and '2017-12-02' and tour_fee < 500 and region_id = 1";
		ArrayList<Tour> tours = manager.getToursByFilter(where);
		for(int i = 0; i < tours.size(); ++i){
			log.info(tours.get(i).getDepartureDate());
			assertThat(tours.get(i).getCountryId()).isEqualTo(1);
		}
		SearchingTourInterface si = new SearchingTourInterface();
	}
	
	@Test
	public void testOpenNlp () throws Exception {
		String paragraph = "Hi. How are you? This is Mike.";
 
		// always start with a model, a model is learned from training data
		InputStream is = new FileInputStream("en-sent.bin");
		SentenceModel model = new SentenceModel(is);
		SentenceDetectorME sdetector = new SentenceDetectorME(model);
	 
		String sentences[] = sdetector.sentDetect(paragraph);
	 
		System.out.println(sentences[0]);
		System.out.println(sentences[1]);
		is.close();

        // print the sentences detected, to console
        for(int i=0;i<sentences.length;i++){
            log.info(sentences[i]);
        }
        
		assertThat("test nlp").isEqualTo("");
	}
	
		
		
	
	
	
	
}
