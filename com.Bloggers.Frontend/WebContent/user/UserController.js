myApp.controller("userController",function($scope,$http,$window,$location
		,$cookieStore,$rootScope){
	
$scope.user={email:'',password:'',firstName:'',lastName:'',contactNumber:'',role:'',onlineStatus:''};
	
	$scope.registerUser=function(){
	$http.post("http://localhost:7838/BloggersMiddleware/register",$scope.user).
	then(function(response){
				alert("User Registered Succesfully");
			},
			function(response){
				alert("User Registered Succesfully");
			})
	
	
	};

	$scope.loginFunc=function(){
		console.log('login function : '+$scope.user.email+' '+$scope.user.password);
		$http.post("http://localhost:7838/BloggersMiddleware/login",$scope.user).
		then(function(response){
					console.log('Valid User');
					$scope.user=response.data;
					$rootScope.currentUser=response.data;
					$cookieStore.put('userDetails',response.data);
					console.log($rootScope.currentUser.role);
					
					$location.path('homePage');
					
				},
				function(response){
					alert("Email or Password is incorrect");
					$location.path("/login");
				})
		
		
	};
		
});