package com.Backend;

import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;



import com.Backend.Dao.ForumDao;

public class ForumTestCase {
	
	static ForumDao forumDao;
	
	@BeforeClass
	public static void init(){
		AnnotationConfigApplicationContext app=new AnnotationConfigApplicationContext();
		app.scan("com.Backend");
		app.refresh();
		forumDao=app.getBean("forumDao",ForumDao.class);
	
}	
	@Test
	@Ignore
	public void addForum(){
		Forum forum=new Forum();
		forum.setForumName("forumName1");
		forum.setForumContent("forumContent1");
		forum.setEmail("forumContent@gmail.com");
		Date d=new Date();
		forum.setCreateDate(d);
		assertTrue("Blog Not Added",forumDao.addForum(forum));
		System.out.println("Heloo added ");
		}
	
	@Test
	@Ignore
	public void deleteForum(){
		Forum forum=forumDao.getForum(50);
		
	}
	}
