package com.Backend.Dao;

import java.util.List;

import com.Backend.Friend;
import com.Backend.User;

public interface FriendDao {
	
	List<User> suggestedUsers(String email);

	void addFriend(Friend friend);
	
	List<Friend> pendingRequests(String toIdEmail);

	void acceptRequest(Friend request);

	void deleteRequest(Friend request);
	
	List<Friend> listOfFriends(String email);


}
