var myApp = angular.module('app');

myApp.filter("trainerFilter",function(){
	return function(courseList,selectedInstructor){
//		console.log("courseList" + courseList[0].instructorName + " selectedInstructor" + selectedInstructor + "typeof" +typeof selectedInstructor);
		let output = [];
		if(selectedInstructor=="" || typeof selectedInstructor === "undefined" || selectedInstructor==undefined || selectedInstructor == null){
			output = courseList;
//			console.log('emptyLot');
		}
		else{
//			console.log("filledLot"+selectedInstructor);
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
	$scope.populateModal= function(courseId,courselist){ 
		
		console.log("in FE : populateModal() with courseId" + courseId);
		console.log(courseId);
		console.log(courselist);
		
		var courseName, longDescription, presentationStartDate, presentationEndDate, assesmentDate, coursePrice;
		for(i of courselist){
		    if(i.courseId === courseId){
		    	courseName = i.courseName;
		        longDescription= i.longDescription;
		        presentationStartDate = i.courseStartDate;
		        presentationEndDate = i.courseEndDate;
		        assesmentDate = i.assesmentDate;
		        coursePrice = i.coursePrice;
		    }
		};
		
		console.log("name"+courseName);
		
		$scope.modalSessionData={
		        courseId : courseId,
		        courseName : courseName,
		        longDescription: longDescription,
		        presentationStartDate : presentationStartDate,
		        presentationEndDate : presentationEndDate,
		        assesmentDate : assesmentDate,
		        coursePrice : coursePrice
		}
	
		
		$http.get("/getCourseSession?courseId="+courseId).then(function(response){
			 if (response.data == null) {
			      $scope.errorMsg = "couldn't load data";
			    } else {
			    	//populate ui-grid
			    	$scope.errorMsg = "LOADING data";
			      sessionDetails = response.data;
			      console.log(sessionDetails);
			      $scope.sessionTableData = sessionDetails;
//			     fill $scope.sessionTableData
			    }
		});
		
	}
	
//	ui-grid properties : remove table names 
	
});

