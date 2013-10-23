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
	private String title;
	
	public long getQuestionId() {
		return questionId;
	}

	public void setQuestionId(long questionId) {
		this.questionId = questionId;
	}

	public long getAnswerId() {
		return answerId;
	}

	public void setAnswerId(long answerId) {
		this.answerId = answerId;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public boolean isAccepted() {
		return isAccepted;
	}

	public void setAccepted(boolean isAccepted) {
		this.isAccepted = isAccepted;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public static Answer fromJSONObject(JSONObject answerJson) {
		//Date date = new Date(Long.parseLong(jsonDate.replaceAll(".*?(\\d+).*", "$1")));
		
		long questionId = Utils.getJsonLongSafe(answerJson, "question_id");
		long answerId = Utils.getJsonLongSafe(answerJson, "answer_id");
		Date creationDate = null;
		int score = 0;
		boolean isAccepted = false;
		String title = Utils.getJsonStringSafe(answerJson, "title");
		
		Answer a = new Answer();
		a.setQuestionId(questionId);
		a.setAnswerId(answerId);
		a.setCreationDate(creationDate);
		a.setScore(score);
		a.setAccepted(isAccepted);
		a.setTitle(title);
		return a;
	}
}
