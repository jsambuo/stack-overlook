package com.sambuo.stackoverlook.entities;

import java.util.Date;

import org.json.JSONObject;

import com.sambuo.stackoverlook.utilities.Utils;

public class Answer {
	private long questionId;
	private long answerId;
	private Date creationDate;
	private int score;
	private boolean isAccepted;
	private String body;
	private String title;
	
	public long getQuestionId() {
		return this.questionId;
	}

	public void setQuestionId(long questionId) {
		this.questionId = questionId;
	}

	public long getAnswerId() {
		return this.answerId;
	}

	public void setAnswerId(long answerId) {
		this.answerId = answerId;
	}

	public Date getCreationDate() {
		return this.creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public int getScore() {
		return this.score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public boolean isAccepted() {
		return this.isAccepted;
	}

	public void setAccepted(boolean isAccepted) {
		this.isAccepted = isAccepted;
	}
	
	public String getBody() {
		return this.body;
	}
	
	public void setBody(String body) {
		this.body = body;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public static Answer fromJSONObject(JSONObject answerJson) {
		long questionId = Utils.getJsonLongSafe(answerJson, "question_id", 0);
		long answerId = Utils.getJsonLongSafe(answerJson, "answer_id", 0);
		Date creationDate = null; //Date date = new Date(Long.parseLong(jsonDate.replaceAll(".*?(\\d+).*", "$1")));
		int score = Utils.getJsonIntSafe(answerJson, "score", 0);
		boolean isAccepted = Utils.getJsonBooleanSafe(answerJson, "is_accepted", false);
		String body = Utils.getJsonStringSafe(answerJson, "body");
		String title = Utils.getJsonStringSafe(answerJson, "title");
		
		Answer a = new Answer();
		a.setQuestionId(questionId);
		a.setAnswerId(answerId);
		a.setCreationDate(creationDate);
		a.setScore(score);
		a.setAccepted(isAccepted);
		a.setBody(body);
		a.setTitle(title);
		return a;
	}
}
