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


@RunWith(SpringRunner.class)
@SpringBootTest(classes = { KitchenSinkTester.class, DatabaseEngine.class })
public class KitchenSinkTester {
	@Autowired
	private DatabaseEngine databaseEngine;
	
	@Test
	public void testNotFound() throws Exception {
		boolean thrown = false;
		try {
			this.databaseEngine.search("no");
		} catch (Exception e) {
			thrown = true;
		}
		assertThat(thrown);
	}
	
	@Test
	public void testFound() throws Exception {
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
			assertThat(!thrownList[counter]) ; 
			assertThat(result.equals(value)) ;
			counter ++ ; 
		}
		
		
	}
	
	@Test
	public void Othertest() throws Exception {
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
	
		}
		
		
	
	
	
	
	
}
