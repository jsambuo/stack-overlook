package com.sambuo.stackoverlook.entities;

import org.json.JSONObject;

import com.sambuo.stackoverlook.utilities.Utils;

public class User {
	private long userId;
	private String displayName;
	private String profileImage;
	private String aboutMe;
	
	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}
	
	public String getDisplayName() {
		return this.displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getProfileImage() {
		return this.profileImage;
	}

	public void setProfileImage(String profileImage) {
		this.profileImage = profileImage;
	}

	public String getAboutMe() {
		return this.aboutMe;
	}

	public void setAboutMe(String aboutMe) {
		this.aboutMe = aboutMe;
	}
	
	public static User fromJSONObject(JSONObject userJson) {
		long userId = Utils.getJsonLongSafe(userJson, "user_id", 0);
		String displayName = Utils.getJsonStringSafe(userJson, "display_name");
		String profileImage = Utils.getJsonStringSafe(userJson, "profile_image");
		String aboutMe = Utils.getJsonStringSafe(userJson, "about_me");
		
		User u = new User();
		u.setUserId(userId);
		u.setDisplayName(displayName);
		u.setAboutMe(aboutMe);
		u.setProfileImage(profileImage);
		return u;
	}
}
