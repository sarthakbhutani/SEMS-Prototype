var myApp = angular.module('app');

myApp.filter("trainerFilter",function(){
	return function(courseList,selectedInstructor){
		console.log("courseList" + courseList[0].instructorName + " selectedInstructor" + selectedInstructor + "typeof" +typeof selectedInstructor);
		let output = [];
		if(selectedInstructor=="" || typeof selectedInstructor === "undefined" || selectedInstructor==undefined || selectedInstructor == null){
			output = courseList;
			console.log('emptyLot');
		}
		else{
			console.log("filledLot"+selectedInstructor);
			output=[];
			angular.forEach(courseList, function(value,key){
				if(value.instructorName==selectedInstructor){
					output.push(value);
				}
			});
		}
		return output;
	}
});

myApp.controller("viewTrainingController", function($scope,$http){
	$http.get("/getAllCourses").then(function (response) {
	    if (response.data == null) {
	      $scope.courseList = [];
	    } else {
	      $scope.courseList = response.data;
	    }
	})
	console.log($scope.courseList);
	//then closed here
	
	//ng-click trigger
	function getCourseByIndex($index){ //		by $index FOR EASE
		$http.get("/getCourseById?courseId="+$index.courseId).then(function(response){
			// Populate Modal Box Details & Show it
			
		});
		
	}
	
//	ui-grid properties : remove table names
	
});

