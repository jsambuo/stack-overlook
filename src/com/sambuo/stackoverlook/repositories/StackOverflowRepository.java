package com.sambuo.stackoverlook.repositories;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.sambuo.stackoverlook.entities.Answer;
import com.sambuo.stackoverlook.entities.Question;
import com.sambuo.stackoverlook.entities.User;

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
		List<User> users = new ArrayList<User>();
		
		User hans = new User();
		hans.setUserId("1");
		hans.setDisplayName("Hans Passant");
		hans.setAboutMe("Formerly active in the MSDN forums as a contributor, moderator and MVP using the \"nobugz\" nick. It didn't take me long to decide to switch bases, the SO team knows how to put a Q+A site together. Great job.");
		hans.setProfileImage("https://www.gravatar.com/avatar/4235db1aa1b11bcddb720dbc70b34a0f?s=128&d=identicon&r=PG");
		users.add(hans);
		
		User von = new User();
		von.setUserId("2");
		von.setDisplayName("VonC");
		von.setAboutMe("System Configuration Management Administrator (ClearCase, SVN, Git), defining various merge workflows between branches");
		von.setProfileImage("https://www.gravatar.com/avatar/7aa22372b695ed2b26052c340f9097eb?s=128&d=identicon&r=PG");
		users.add(von);
		
		User john = new User();
		john.setUserId("3");
		john.setDisplayName("John Skeet");
		john.setAboutMe("Author of C# in Depth. Currently a software engineer at Google, London.");
		john.setProfileImage("https://www.gravatar.com/avatar/6d8ebb117e8d83d74ea95fbdd0f87e13?s=128&d=identicon&r=PG");
		users.add(john);
		
		//get json
		//loop through items to get users
		return users;
	}
}
