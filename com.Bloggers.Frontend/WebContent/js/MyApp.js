var myApp = angular.module("myApp", ["ngRoute",,"ngCookies"]);
myApp.config(function($routeProvider) {
    $routeProvider
    .when("/", {
        templateUrl : "main.html"
    })
    .when("/AboutUs", {
        templateUrl : "AboutUs.html"
    })
    .when("/ContactUs", {
        templateUrl : "ContactUs.html"
    })
    .when("/signup", {
        templateUrl : "user//Signup.html"
    })
     .when("/login", {
        templateUrl : "user//Login.html"
    })
    .when("/homePage", {
        templateUrl : "HomePage.html"
    });
});
myApp.run(function($rootScope,$cookieStore){
	console.log('I m in run function');
	console.log($rootScope.currentUser);
	
	if($rootScope.currentUser==undefined){
		console.log('current User is undefined');
		$rootScope.currentUser=$cookieStore.get('userDetails');
	}
	else {
		console.log($rootScope.currentUser.email);
		console.log($rootScope.currentUser.role);
	}
	
});








