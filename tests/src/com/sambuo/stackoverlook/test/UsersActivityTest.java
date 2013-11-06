package com.sambuo.stackoverlook.test;

import com.jayway.android.robotium.solo.Solo;
import com.sambuo.stackoverlook.AnswersActivity;
import com.sambuo.stackoverlook.QuestionActivity;
import com.sambuo.stackoverlook.UsersActivity;

import android.test.ActivityInstrumentationTestCase2;

public class UsersActivityTest extends
		ActivityInstrumentationTestCase2<UsersActivity> {

	private Solo solo;
	
	public UsersActivityTest() {
		super("com.sambuo.stackoverlook", UsersActivity.class);
	}

	protected void setUp() throws Exception {
		super.setUp();
		this.solo = new Solo(super.getInstrumentation(), super.getActivity());
	}
	
	public void testMonkey() {
		solo.assertCurrentActivity("Assert UsersActivity", UsersActivity.class);
		solo.clickInList(0);
		solo.assertCurrentActivity("Asserting AnswersActivity", AnswersActivity.class);
		solo.clickInList(0);
		solo.assertCurrentActivity("Asserting QuestionActivity", QuestionActivity.class);
		//solo.clickOnButton("Save");
	}

}
