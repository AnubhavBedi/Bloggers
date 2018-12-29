package com.Backend;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatTypes;

@Entity
@Table(name="Blog_Tab")
@SequenceGenerator(name="bseq",sequenceName="blogseq")
public class Blog {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="bseq")
	private int BlogId;
	private String BlogName;
	private String BlogContext;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING,pattern="dd-mm-yyyy")
	private Date createDate;
	private String email;
	private String status;
	private int Likes;
 	
	public Blog(){
	}

	public int getBlogId() {
		return BlogId;
	}

	public void setBlogId(int blogId) {
		BlogId = blogId;
	}

	public String getBlogName() {
		return BlogName;
	}

	public void setBlogName(String blogName) {
		BlogName = blogName;
	}

	public String getBlogContext() {
		return BlogContext;
	}

	public void setBlogContext(String blogContext) {
		BlogContext = blogContext;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getLikes() {
		return Likes;
	}

	public void setLikes(int likes) {
		Likes = likes;
	}

	@Override
	public String toString() {
		return "Blog [BlogId=" + BlogId + ", BlogName=" + BlogName
				+ ", BlogContext=" + BlogContext + ", createDate=" + createDate
				+ ", email=" + email + ", status=" + status + ", Likes="
				+ Likes + "]";
	}
	

	
}
