package com.sambuo.stackoverlook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class QuestionActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.question);
		
		Intent intent = getIntent();
		String questionId = intent.getStringExtra(AnswersActivity.EXTRA_QUESTION_ID);
	}
}
