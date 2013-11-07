package com.sambuo.stackoverlook;

import java.util.ArrayList;
import java.util.List;

import com.sambuo.stackoverlook.entities.Answer;
import com.sambuo.stackoverlook.repositories.StackOverflowRepository;
import com.sambuo.stackoverlook.utilities.Utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class AnswersActivity extends Activity {
	
	public final static String EXTRA_QUESTION_ID = "com.sambuo.stackoverlook.QUESTION_ID";
	public final static String EXTRA_ANSWER_BODY = "com.sambuo.stackoverlook.ANSWER_BODY";
	public final static String EXTRA_ANSWER_SCORE = "com.sambuo.stackoverlook.ANSWER_SCORE";
	private final StackOverflowRepository repository = StackOverflowRepository.getInstance();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.answers);
		
		Intent intent = getIntent();
		if(!intent.hasExtra(UsersActivity.EXTRA_USER_ID)) {
			Log.e(AnswersActivity.class.toString(), "UserId was not given to AnswersActivity");
		}
		if(!intent.hasExtra(UsersActivity.EXTRA_USER_NAME)) {
			Log.e(AnswersActivity.class.toString(), "User name was not given to AnswersActivity");
		}
		if(!intent.hasExtra(UsersActivity.EXTRA_USER_ICON)) {
			Log.e(AnswersActivity.class.toString(), "User icon was not given to AnswersActivity");
		}
		
		String userName = intent.getStringExtra(UsersActivity.EXTRA_USER_NAME);
		this.setTitle(userName);
		
		String userIconPath = intent.getStringExtra(UsersActivity.EXTRA_USER_ICON);
		Drawable userIcon = Utils.getDrawableFromURL(userIconPath);
		this.getActionBar().setIcon(userIcon);
		
		long selectedUserId = intent.getLongExtra(UsersActivity.EXTRA_USER_ID, 0);
		Iterable<Answer> answers = this.repository.getLatestAnswersFromUserId(selectedUserId);
		final ArrayList<Answer> answerArrayList = Utils.toArrayList(answers);
		
		final AnswerArrayAdapter adapter = new AnswerArrayAdapter(this, answerArrayList);
		final ListView listView = (ListView) findViewById(R.id.answersListView);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
				Answer a = answerArrayList.get(position);
				
				Intent showAnswersIntent = new Intent(view.getContext(), QuestionActivity.class);
				showAnswersIntent.putExtra(AnswersActivity.EXTRA_QUESTION_ID, a.getQuestionId());
				showAnswersIntent.putExtra(AnswersActivity.EXTRA_ANSWER_BODY, a.getBody());
				showAnswersIntent.putExtra(AnswersActivity.EXTRA_ANSWER_SCORE, a.getScore());
				startActivity(showAnswersIntent);
			}
		});
	}
	
	private class AnswerArrayAdapter extends BaseAdapter {
		private List<Answer> answers;
		private LayoutInflater inflater;

		public AnswerArrayAdapter(Context context, List<Answer> answers) {
			this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        	this.answers = answers;
		}
		
		@Override
		public View getView(int position, View row, ViewGroup parent) {
			ViewHolder vh;
			if (row == null || row.getTag() == null) {
				row = inflater.inflate(R.layout.answer_row, parent, false);
				vh = new ViewHolder(row);
				row.setTag(vh);
			}
			else {
				vh = (ViewHolder) row.getTag();
			}
			
			Answer answer = this.answers.get(position);
			
			vh.title.setText(Html.fromHtml(answer.getTitle()));
			vh.score.setText(String.format("%d", answer.getScore()));
			if (answer.isAccepted()) {
				vh.accepted.setVisibility(View.VISIBLE);
			}
			
			return row;
		}
		
		@Override
		public int getCount() {
			return this.answers.size();
		}

		@Override
		public Object getItem(int i) {
			return this.answers.get(i);
		}

		@Override
		public long getItemId(int i) {
			return this.answers.get(i).getQuestionId();
		}
		
		private class ViewHolder {
		    protected final TextView title;
		    protected final TextView score;
		    protected final TextView accepted;
		    
		    public ViewHolder( final View root ) {
		    	this.title = (TextView) root.findViewById( R.id.title );
		        this.score = (TextView) root.findViewById( R.id.score );
		        this.accepted = (TextView) root.findViewById( R.id.accepted );
		    }
		}
	}
}
