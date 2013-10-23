package com.sambuo.stackoverlook;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import com.sambuo.stackoverlook.entities.User;
import com.sambuo.stackoverlook.repositories.StackOverflowRepository;
import com.sambuo.stackoverlook.utilities.Utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class UsersActivity extends Activity
{
	public final static String EXTRA_USER_ID = "com.sambuo.stackoverlook.USER_ID";
	private final StackOverflowRepository repository = StackOverflowRepository.getInstance();
	
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.users);
        
        //TODO: DO NOT USE. FOR DEVELOPMENT TESTING ONLY
        if (android.os.Build.VERSION.SDK_INT > 9) {
        	StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        Iterable<User> users = this.repository.getAppSpecificUsers();
        final ListView listView = (ListView) findViewById(R.id.listView);
        final UserArrayAdapter adapter = new UserArrayAdapter(this, Utils.toArrayList(users));
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

          @Override
          public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
        	  Intent showAnswersIntent = new Intent(view.getContext(), AnswersActivity.class);
        	  showAnswersIntent.putExtra(UsersActivity.EXTRA_USER_ID, id);
        	  UsersActivity.this.startActivity(showAnswersIntent);
          }

        });
    }
    
	private class UserArrayAdapter extends BaseAdapter {
		private List<User> users;
		private Context context;

		public UserArrayAdapter(Context context, List<User> users) {
        	this.users = users;
        	this.context = context;
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View row = convertView;
			if (row == null) {
				LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				row = inflater.inflate(R.layout.user_row, parent, false);
			}
			
			ImageView gravatar = (ImageView) row.findViewById(R.id.gravatar);
			TextView name = (TextView) row.findViewById(R.id.name);
			TextView desc = (TextView) row.findViewById(R.id.desc);
			
			User user = this.users.get(position);
			
			//TODO: Use async loading
			//TODO: Cache images
			try {
				URL newurl = new URL(user.getProfileImage());
				Bitmap img = BitmapFactory.decodeStream(newurl.openConnection() .getInputStream());
				gravatar.setImageBitmap(img);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			name.setText(user.getDisplayName());
			desc.setText(user.getAboutMe());
			
			return row;
		}

		@Override
		public int getCount() {
			return this.users.size();
		}

		@Override
		public Object getItem(int i) {
			return this.users.get(i);
		}

		@Override
		public long getItemId(int i) {
			return this.users.get(i).getUserId();
		}

	}
}
