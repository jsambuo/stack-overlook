package com.sambuo.stackoverlook.entities;

import org.json.JSONObject;

import com.sambuo.stackoverlook.utilities.Utils;

public class Question {
	private long questionId;
	private int score;
	private String body;
	private String title;
	
	public long getQuestionId() {
		return this.questionId;
	}

	public void setQuestionId(long questionId) {
		this.questionId = questionId;
	}

	public int getScore() {
		return this.score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getBody() {
		return this.body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public static Question fromJSONObject(JSONObject questionJson) {
		long questionId = Utils.getJsonLongSafe(questionJson, "question_id", 0);
		int score = Utils.getJsonIntSafe(questionJson, "score", 0);
		String body = Utils.getJsonStringSafe(questionJson, "body");
		String title = Utils.getJsonStringSafe(questionJson, "title");
		
		Question q = new Question();
		q.setQuestionId(questionId);
		q.setScore(score);
		q.setBody(body);
		q.setTitle(title);
		return q;
	}
}
