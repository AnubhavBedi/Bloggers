package com.Backend.Dao;

import com.Backend.ProfilePicture;

public interface ProfilePictureDao {
	
	void uploadProfilePicture(ProfilePicture profilePicture);

	ProfilePicture getImage(String email);

}
