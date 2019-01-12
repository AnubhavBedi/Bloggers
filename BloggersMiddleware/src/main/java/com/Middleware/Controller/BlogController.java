package com.Middleware.Controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.Backend.Blog;
import com.Backend.BlogComment;
import com.Backend.User;
import com.Backend.Dao.BlogDao;
import com.Backend.Dao.UserDao;
@RestController
public class BlogController {
	@Autowired
	 BlogDao blogdao;

	@Autowired
	UserDao userDao;
	
	@Autowired
	HttpSession session;
	
	
	@PostMapping(value="/addBlog")
	public ResponseEntity<String> addBlog(@RequestBody Blog blog)
	{
		User user=(User)session.getAttribute("userObj");
		
		blog.setCreateDate(new java.util.Date());
		blog.setLikes(0);
		blog.setStatus("pending");
		blog.setEmail(user.getEmail());
		
		if(blogdao.addBlog(blog)){
			return new ResponseEntity<String>("Blog added succesfully",HttpStatus.OK);
		}
		else{
			return new ResponseEntity<String>("Error in adding blog",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@GetMapping(value="/deleteBlog/{blogId}")
	public ResponseEntity<String> deleteBlog(@PathVariable int blogId){
		
		
		System.out.println("Delete Blog in Rest Controller : "+blogId);
		Blog blog=blogdao.getBlog(blogId);
		
		if(blogdao.deleteBlog(blog)){
			return new ResponseEntity<String>("Blog Deleted Succesfully",HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>("Error in Deleting Blog",HttpStatus.OK);
		}
	}
	
	@PostMapping(value="/updateBlog")
	public ResponseEntity<String> updateBlog(@RequestBody Blog blog){
		System.out.println(blog);
		if(blogdao.updateBlog(blog)){
			return new ResponseEntity<String>("Blog Updated Succesfully",HttpStatus.OK);
		}
		else{
			return new ResponseEntity<String>("Error in updating blog",HttpStatus.NOT_FOUND);
		}
	}

	@GetMapping(value="/getBlog/{blogId}")
	public ResponseEntity<Blog> getBlog(@PathVariable int blogId){
		
		Blog blog=blogdao.getBlog(blogId);
		if(blog==null){
			System.out.println("Blog Not Found");
			return new ResponseEntity<Blog>(blog,HttpStatus.NOT_FOUND);
		}
		else{
			System.out.println("Blog Found "+blog.getBlogName());
			return new ResponseEntity<Blog>(blog,HttpStatus.OK);
		}
	}
	
	
	@GetMapping(value="/approveBlog/{blogId}")
	public ResponseEntity<String> approveBlog(@PathVariable int blogId){
		Blog blog=blogdao.getBlog(blogId);
		if(blogdao.approveBlog(blog)){
			return new ResponseEntity<String>("Blog Approved Succesfully",HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>("Error in updating blog",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value="/rejectBlog/{blogId}")
	public ResponseEntity<String> rejectBlog(@PathVariable int blogId){
		Blog blog=blogdao.getBlog(blogId);
		if(blogdao.rejectBlog(blog)){
			return new ResponseEntity<String>("Blog Rejected Succesfully",HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>("Error in rejecting blog",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping(value="/incrementLikes/{blogId}")
	public ResponseEntity<String> incrementLikes(@PathVariable int blogId){
		System.out.println("Increment Liked called : "+blogId); 
		Blog blog=blogdao.getBlog(blogId);
		blog.setLikes(blog.getLikes()+1);
		blogdao.incrementLikes(blog);
		return new ResponseEntity<String>("Likes Incremented Succesfully",HttpStatus.OK);
		
	}
	
	@GetMapping(value="/listBlogs")
	public ResponseEntity<List<Blog>> getListBlogs(HttpSession session){
		List<Blog> listBlogs=null;
		User user=(User)session.getAttribute("userObj");
		
		System.out.println("Email : "+user.getEmail());
		System.out.println("Role : "+user.getRole());
		listBlogs=blogdao.listBlogs(user.getEmail(),user.getRole());
		
		
			if(listBlogs.size()>0){
				return new ResponseEntity<List<Blog>>(listBlogs,HttpStatus.OK);
			}
			else {
				return new ResponseEntity<List<Blog>>(listBlogs,HttpStatus.NOT_FOUND);
			}
			
	}
			
			@GetMapping(value="/listApprovedBlogs")
			public ResponseEntity<List<Blog>> getApprovedListBlogs(){
				List<Blog> listBlogs=null;
				listBlogs=blogdao.listAllApprovedBlogs();
				
				
					if(listBlogs.size()>0){
						return new ResponseEntity<List<Blog>>(listBlogs,HttpStatus.OK);
					}
					else {
						return new ResponseEntity<List<Blog>>(listBlogs,HttpStatus.NOT_FOUND);
					}
				
			}

			/*@PostMapping(value="/addBlogComment")
			public ResponseEntity<String> addBlogComment(@RequestBody BlogComment blogComment,HttpSession session){
				blogComment.setCommentDate(new Date());
				
				User user=(User)session.getAttribute("userObj");
				if(user!=null)
				{
						blogComment.setEmail(user.getEmail());
						blogdao.addBlogComment(blogComment);
						return new ResponseEntity<String>("Blog Comment Added Succesfully",HttpStatus.OK);
					
				}
				else {
					System.out.println("User doesnt exist");
					System.out.println("I m in else 2");
					return new ResponseEntity<String>("User doesnt exist",HttpStatus.INTERNAL_SERVER_ERROR);
				}
				
				}*/
			
			@PostMapping(value="/addBlogComment")
			public ResponseEntity<?> addBlogComment(@RequestBody BlogComment blogComment,HttpSession session){
				blogComment.setCommentDate(new Date());
				
				User user=(User)session.getAttribute("userObj");
				if(user!=null)
				{
						blogComment.setEmail(user.getEmail());
						blogdao.addBlogComment(blogComment);
						return new ResponseEntity<BlogComment>(blogComment,HttpStatus.OK);
					
				}
				else {
					System.out.println("User doesnt exist");
					System.out.println("I m in else 2");
					return new ResponseEntity<String>("User doesnt exist",HttpStatus.INTERNAL_SERVER_ERROR);
				}
				
				}
			
			@GetMapping(value="/blogcomments/{blogId}")
			public ResponseEntity<?> getAllBlogComments(@PathVariable int blogId)
			{
				List<BlogComment> blogComments=blogdao.listBlogComments(blogId);
				return new ResponseEntity <List<BlogComment>>(blogComments,HttpStatus.OK);
				
			}


		}
