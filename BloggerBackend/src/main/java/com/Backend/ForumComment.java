package com.Backend;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table
@SequenceGenerator(name="forumcommentseq",sequenceName="forumcommentidseq")
public class ForumComment {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="forumcommentseq")
	private int commentId;
	private String commentText;
	private String email;
	private int forumId;
	private Date commentDate;
	public int getCommentId() {
		return commentId;
	}
	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}
	public String getCommentText() {
		return commentText;
	}
	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getForumId() {
		return forumId;
	}
	public void setForumId(int forumId) {
		this.forumId = forumId;
	}
	public Date getCommentDate() {
		return commentDate;
	}
	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
	}
	@Override
	public String toString() {
		return "ForumComment [commentId=" + commentId + ", commentText="
				+ commentText + ", email=" + email + ", forumId=" + forumId
				+ ", commentDate=" + commentDate + "]";
	}


}
