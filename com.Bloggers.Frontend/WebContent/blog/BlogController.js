myApp.controller("blogController",function($scope,$http,$window,$location
		,$cookieStore,$rootScope){
	
	$scope.blog={blogId:'',blogName:'',blogContext:'',createDate:'',email:'',status:'',likes:''};
	$scope.comment={commentText:'',email:'',blogId:'',commentDate:''};	


	$scope.addBlog=function(){
		console.log('Add Blog function');
		console.log($scope.blog)
		$http.post("http://localhost:7838/BloggersMiddleware/addBlog",$scope.blog).
		then(function(response){
					console.log('Blog Not Added')
										
				},
				function(response){
					alert('Blog Added Succesfully');
					$window.location.reload();
				})
	};
	$scope.fetchAllBlogs=function(){
		$http.get("http://localhost:7838/BloggersMiddleware/listBlogs")
		.then(function(response)
				{
						console.log('Second');
						$scope.allBlogs=response.data;
						console.log($scope.allBlogs);
				},
				function(error){
					console.log("No Blogs found...");
					$scope.allBlogs=[];
					$scope.viewmessage="No Blogs Found!!!"
				});
		};
		
		$scope.fetchAllApprovedBlogs=function(){
			$http.get("http://localhost:7838/BloggersMiddleware/listApprovedBlogs")
			.then(function(response)
					{
							console.log('Second');
							$scope.allBlogs=response.data;
							console.log($scope.allBlogs);
					},
					function(error){
						console.log("No Blogs found...");
						$scope.allBlogs=[];
						$scope.viewmessage="No Blogs Found!!!"
					});
		};
			
		$scope.approveBlog=function(bid){
			console.log('approve Blog '+bid);
			$http.get("http://localhost:7838/BloggersMiddleware/approveBlog/"+bid)
			.then(function(response)
					{
					},
					function(error){
						alert('Blog Approved');
						$window.location.reload();
						$location.path('viewBlog');
			});
		};
		$scope.rejectBlog=function(bid){
			console.log('reject Blog '+bid);
			$http.get("http://localhost:7838/BloggersMiddleware/rejectBlog/"+bid)
			.then(function(response)
					{
					},
					function(error){
						alert('Blog Rejected');
						$window.location.reload();
						$location.path('viewBlog');
			});
		};
		
		$scope.incrementLikes=function(bid){
			console.log("increment likes called "+bid);
			$http.get("http://localhost:7838/BloggersMiddleware/incrementLikes/"+bid)
			.then(function(response)
					{
					},
					function(error){
						$window.location.reload();
						$location.path('viewApprovedBlogs');
			});
		};
		
		$scope.addComment=function(blog,commentText){
			console.log('Add Comment function : '+blog.blogId+' '+blog.blogName+' '+commentText);
			
			$scope.comment.commentText=commentText;
			$scope.comment.blogId=blog.blogId;
			
			$http.post("http://localhost:7838/BloggersMiddleware/addBlogComment",$scope.comment).
			then(function(response){
						console.log('Comment Added')
											
					},
					function(response){
						alert(' Login to do the comments');
						$location.path("/login");
						
					
		
		
	});
		};
		
		$scope.onShowComments=function(bid){
			$http.get("http://localhost:7838/BloggersMiddleware/blogcomments/"+bid).then(
					function(response){
						
						console.log('Blog Id : '+bid);
						$scope.comments=response.data;
						$rootScope.comments=response.data;
						$cookieStore.put('commentDetails',response.data);
						
						console.log($scope.comments);
						$location.path("/viewComments");
						
			},
					function(response){
					console.log(response);
			})
		}
		
		

		
	});