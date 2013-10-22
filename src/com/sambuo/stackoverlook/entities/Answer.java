package com.sambuo.stackoverlook.entities;

import java.util.Date;

public class Answer {
	private String questionId;
	private String answerId;
	private Date creationDate;
	private int score;
	private boolean isAccepted;
	private String title;
	
	public static Answer fromJSON(String answerJson) {
		//Date date = new Date(Long.parseLong(jsonDate.replaceAll(".*?(\\d+).*", "$1")));
		return null;
	}
}
