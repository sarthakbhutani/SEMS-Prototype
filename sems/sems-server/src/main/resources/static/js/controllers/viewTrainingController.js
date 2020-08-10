
var myApp = angular.module("app");

myApp.filter("trainerFilter", function () {
  return function (courseList, selectedInstructor) {
    let output = [];
    if (
      selectedInstructor == "" ||
      typeof selectedInstructor === "undefined" ||
      selectedInstructor == undefined ||
      selectedInstructor == null
    ) {
      output = courseList;
    } else {
      output = [];
      angular.forEach(courseList, function (value, key) {
        if (value.instructorName == selectedInstructor) {
          output.push(value);
        }
      });
    }
    return output;
  };
});

myApp.filter("categoryFilter", function () {
	return function (courseList, selectedCategory) {
	    let output = [];
	    if (
	    		selectedCategory == "" ||
	      typeof selectedCategory === "undefined" ||
	      selectedCategory == undefined ||
	      selectedCategory == null
	    ) {
	      output = courseList;
	    } else {
	      output = [];
	      angular.forEach(courseList, function (value, key) {
	        if (value.category.includes(selectedCategory)) {
	          output.push(value);
	        }
	      });
	    }
	    return output;
	  };
	});

myApp.controller("viewTrainingController", function ($scope, $http) {
  $http.get("/getAllCourses").then(function (response) {
    if (response.data == null) {
      $scope.courseList = [];
    } else {
      $scope.courseList = response.data;
      fillCategories();
    }
  });
  
  let fillCategories = function(){
	  console.log("filling categories");
	  var courseList =  $scope.courseList;
	  var categoryList = [];
	  for(i of courseList){
		  for(j of i.category){
			  if(!categoryList.includes(j)){
				  categoryList.push(j);
			  }
		  }
	  }
	  $scope.categoryList = categoryList;
	  console.log("filled categoryList");
	  console.log(categoryList);
  }
  

  //ng-click trigger
  $scope.populateModal = function (courseId, courselist) {
    console.log("in FE : populateModal() with courseId" + courseId);

    $http
      .get("/getCourseSession?courseId=" + courseId)
      .then(function (response) {
        if (response.data == null) {
          $scope.errorMsg = "couldn't load data";
        } else {
          //populate ui-grid
          $scope.errorMsg = "Loading data : takes time to fill the grid";
          sessionDetails = response.data;
          console.log(sessionDetails);

          //NEEDS TO BE CHANGED, another function to be called => to manipulate the grid items or in last like UploadDoc....
          $scope.sessionTableData = sessionDetails;
        }

        var courseName,
          longDescription,
          presentationStartDate,
          presentationEndDate,
          assesmentDate,
          coursePrice;
        for (i of courselist) {
          if (i.courseId === courseId) {
            courseName = i.courseName;
            longDescription = i.longDescription;
            presentationStartDate = i.courseStartDate;
            presentationEndDate = i.courseEndDate;
            assesmentDate = i.assesmentDate;
            coursePrice = i.coursePrice;
          }
        }

        console.log("name" + courseName);

        $scope.modalSessionData = {
          courseId: courseId,
          courseName: courseName,
          longDescription: longDescription,
          presentationStartDate: presentationStartDate,
          presentationEndDate: presentationEndDate,
          assesmentDate: assesmentDate,
          coursePrice: coursePrice,
        };
      });
  };


  //	ui-grid properties : remove table names
});
