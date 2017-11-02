package com.example.bot.spring;

public class FAQ {
	private int questionNo; 
	private String question ; 
	private String answer ; 
	
	public FAQ ( String question , String answer ) { 
		this.question = question ; 
		this.answer = answer ; 
	}
	public void setQuestionNum(int i) {this.questionNo = i;}
	public String getQuestion(){return this.question;}
	public String getAnswer(){return this.question;}
}
