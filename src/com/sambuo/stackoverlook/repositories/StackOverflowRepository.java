package com.sambuo.stackoverlook.repositories;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.sambuo.stackoverlook.entities.Answer;
import com.sambuo.stackoverlook.entities.Question;
import com.sambuo.stackoverlook.entities.User;
import com.sambuo.stackoverlook.utilities.Reducer;
import com.sambuo.stackoverlook.utilities.Utils;

/**
 * Class that manages data from Stackoverflow.com 
 * 
 * @author Jimmy Sambuo
 *
 */
public class StackOverflowRepository {
	
	private StackOverflowRepository() { }
	
	/**
	 * Eager initialized singleton instance of the repository.
	 */
	private static final StackOverflowRepository INSTANCE = new StackOverflowRepository();
	
	private Iterable<User> appSpecificUsersCache;
	
	/**
	 * Gets a singleton instance of the repository.
	 * 
	 * @return The singleton instance
	 */
	public static StackOverflowRepository getInstance() {
		return StackOverflowRepository.INSTANCE;
	}
	
	/**
	 * Gets a set of users as predefined by the developer.
	 * 
	 * @return
	 */
	public Iterable<User> getAppSpecificUsers() {
		if (this.appSpecificUsersCache == null)
		{
			String[] userIds = { "298575", "236398", "822", "115145", "246461", "244296", "501696", "1174526", "2820983" };
			this.appSpecificUsersCache = this.getUsersFromIds(Arrays.asList(userIds));
		}
		return this.appSpecificUsersCache;
	}
	
	public Iterable<Answer> getLatestAnswersFromUserId(String userId) {
		return null;
	}
	
	public Question getQuestionFromQuestionId(String questionId) {
		return Question.fromJSON(null);
	}
	
	private Iterable<User> getUsersFromIds(Iterable<String> userIds) {
		
		//create URL from userIds
		String ids = Utils.reduce(new Reducer<String, String>() {
			@Override
			public String foldIn(String accum, String next) {
				return accum + "%3B" + next;
			}
		}, userIds);
		
		String url = "https://api.stackexchange.com/2.1/users/" + ids + "?order=desc&sort=name&site=stackoverflow&filter=!23IYX9QhL197kxGBIakjC";
		
		//get json
		String jsonString = Utils.getJSONfromStackOverflowURL(url);
		
		//loop through items to get users
		List<User> users = new ArrayList<User>();
		
		try {
			JSONObject json = new JSONObject(jsonString);
			JSONArray itemsArray = json.getJSONArray("items");
			for (int i = 0; i < itemsArray.length(); i++) {
				JSONObject userJson = itemsArray.getJSONObject(i); 
				User u = User.fromJSONObject(userJson);
				users.add(u);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return users;
	}
}
