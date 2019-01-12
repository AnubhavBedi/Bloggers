package com.Backend;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.Backend.Dao.BlogDao;




public class BlogTestCase {

	static BlogDao blogdao;
	
	@BeforeClass
	public static void init(){
		AnnotationConfigApplicationContext app=new AnnotationConfigApplicationContext();
		app.scan("com.Backend");
		app.refresh();
		blogdao=app.getBean("blogDao",BlogDao.class);
	}
	
	@Test
	@Ignore
	public void addBlog(){
		Blog blog=new Blog();
		blog.setBlogName("Abccvcc");
		blog.setBlogContext("Blog test 2");
		blog.setEmail("Blogtest2@gmail.com");
		Date d=new Date();
		blog.setCreateDate(d);
		blog.setLikes(0);
		blog.setStatus("Approved");
		assertTrue("Blog Not Added",blogdao.addBlog(blog));
		System.out.println("Heloo added ");
		}
	
	@Test
	@Ignore
	public void deleteBlog(){
		Blog blog=blogdao.getBlog(50);
		if(blog!=null){
			boolean r=blogdao.deleteBlog(blog);
			assertTrue("Problem in deleting Blog",r);
		}
		else {
         assertNotNull("Blog Not Found",blog);
		}
        }
         
         
	
        @Test
        @Ignore
        public void updateBlog(){
        	Blog blog=blogdao.getBlog(200);
            blog.setBlogName("Blog Test1 ");
            blog.setBlogContext("Test Desc1");
            blog.setEmail("Blogtest1@gmail.com");
            boolean r=blogdao.updateBlog(blog);
             
            assertTrue("Problem in updating Blog",r);
            System.out.println("Updating done..");
        }
    
        
        @Test
        @Ignore
        public void approveBlog(){
        	Blog blog=blogdao.getBlog(200);
        	blog.setStatus("Approved");
        	boolean r=blogdao.approveBlog(blog);
        	
        	assertTrue("cannot be approved",r);
        	System.out.println("approved working....");
        }
        @Test
        @Ignore
        public void rejectBlog(){
        	Blog blog=blogdao.getBlog(250);
        	blog.setStatus("Rejected");
        	boolean r=blogdao.approveBlog(blog);
        	
        	assertTrue("not found in database",r);
        	System.out.println("rejected blog working....");
        }
        
        @Test
        @Ignore
        public void incrementLikes(){
        	Blog blog=blogdao.getBlog(250);
        	blog.setLikes(blog.getLikes()+1);
        	boolean r=blogdao.incrementLikes(blog);
        	
        	assertTrue("blog not found",r);
        	System.out.println("Inrement done..");
        }
        
        
        
        @Test
        @Ignore
        public void getBlog(){
        	Blog blog=blogdao.getBlog(200);
        	if(blog !=null){
        	
				System.out.println(blog);
        	}
            assertNotNull("Blog Not Found", blog);
            System.out.println("blog found...");
        }
        
        @Test
        @Ignore
        public void getAllApprovedBlogs(){
        	List<Blog> blogs=blogdao.listAllApprovedBlogs();
        	for(Blog blog:blogs)
        	{
        		System.out.println(blog.getBlogContext()+ " " +blog.getStatus());
        	}
        	
        	assertTrue("Approved Blogs Not Found",blogs.size()>0);
        } 
        
        @Test
        @Ignore
        public void getAllPendingBlogs(){
        	List<Blog> blogs=blogdao.listPendingBlogs();
        	for(Blog blog:blogs)
        	{
        		System.out.println(blog.getBlogContext());
        	}
        	
        	assertTrue("Pending Blogs Not Found",blogs.size()>0);
        	System.out.println("pending working");
        } 
        
        @Test
    	@Ignore
    	public void addBlogComment(){
    		BlogComment blogComment=new BlogComment();
    		blogComment.setCommentText("Impressive");
    		blogComment.setEmail("rahul@gmail.com");
    		blogComment.setBlogId(200);
    		Date d=new Date();
    		blogComment.setCommentDate(d);
    		
    		assertTrue("Blog Comment Not Added",blogdao.addBlogComment(blogComment));
    		System.out.println("BVlog comment working");
    	}
        @Test
    	@Ignore
    	public void deleteBlogComment(){
    		BlogComment blogComment=blogdao.getBlogComment(50);
    		boolean r=blogdao.deleteBlogComment(blogComment);
             assertTrue("Problem in deleting Comment",r);
             System.out.println("delete comment working");
            }
        
        @Test
        @Ignore
        public void getBlogComment(){
        	BlogComment blogComment=blogdao.getBlogComment(100);
            assertNotNull("Blog Comment Not Found", blogComment);
        System.out.println("blog comment working");
        }
		
		@Test
		@Ignore
		public void listBlogComment(){
			List<BlogComment> commentList=blogdao.listBlogComments(200);
			for( BlogComment comment:commentList ){
			System.out.println(comment.getCommentText());
		}
		
		assertTrue("No comment found",commentList.size()>0);
			
		}
		
		@Test
		@Ignore
		public void listBlog ()
		{
			List<Blog> blogList=blogdao.listBlogs("Blogtest1@gmail.com","Role_User");
			for(Blog blog:blogList){
				System.out.println(blog.getBlogName()+" "+blog.getBlogContext());
			}
			assertTrue("Blogs Not Found", blogList.size()>0);
			System.out.println("list working....");
			
		}
}

