package com.sambuo.stackoverlook;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class AnswersActivity extends Activity {
	
	public final static String EXTRA_QUESTION_ID = "com.sambuo.stackoverlook.QUESTION_ID";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.answers);
		
		Intent intent = getIntent();
		String selectedUserId = intent.getStringExtra(UsersActivity.EXTRA_USER_ID);
		
		String[] answers = { "Answer1", "Answer2", "Answer3" };
		
		final AnswerArrayAdapter adapter = new AnswerArrayAdapter(this, R.layout.answer_row, answers);
		final ListView listView = (ListView) findViewById(R.id.answersListView);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

	          @Override
	          public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
	        	  
	        	  Intent showAnswersIntent = new Intent(view.getContext(), QuestionActivity.class);
	        	  showAnswersIntent.putExtra(AnswersActivity.EXTRA_QUESTION_ID, "questionId");
	        	  startActivity(showAnswersIntent);
	        	  //TextView clickedView = (TextView) view.findViewById(R.id.title);
	              //Toast.makeText(AnswersActivity.this, "Item with id ["+id+"] - Position ["+position+"] - Planet ["+clickedView.getText()+"]", Toast.LENGTH_SHORT).show();
	          }

	        });
	}
	
	private class AnswerArrayAdapter extends ArrayAdapter<String> {
		String[] answers;
		Context context;

		public AnswerArrayAdapter(Context context, int textViewResourceId, String[] answers) {
			super(context, textViewResourceId, R.id.title, answers);
        	this.context = context;
        	this.answers = answers;
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View row = convertView;
			if (row == null) {
				LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				row = inflater.inflate(R.layout.answer_row, parent, false);
			}
			
			TextView title = (TextView) row.findViewById(R.id.title);
			title.setText(answers[position]);
			
			return row;
		}

	}
}
