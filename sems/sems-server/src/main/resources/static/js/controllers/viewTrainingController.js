//why sessionStartTme instead of sessionStartTime in CUSTOM COURSE REQUEST
//sessionGrid 

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
  
//  $scope.sessionGrid.data = [];
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
          console.log("session Details");
          console.log(sessionDetails);

          //NEEDS TO BE CHANGED, another function to be called => to manipulate the grid items or in last like UploadDoc....
          $scope.sessionTableData = sanatiseGrid(sessionDetails);

//          $scope.sessionGrid.data = sanatiseGrid(sessionDetails);
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
  
  function sanatiseGrid(sessionDetails){
	  var sessionTableData = [];
	  for(i of sessionDetails){
		  var obj={};
		  obj.session=i.sessionName;
		  obj.sessionDate = i.sessionDate;
		  console.log(i.sessionStartTme);
		  obj.SessionTime =i.sessionStartTme  + " - " + i.sessionEndTime + " (IST)";
		  sessionTableData.push(obj);
	  }
	  return sessionTableData;
	  console.log("in Sanatise Grid fn");
	  console.log(sessionTableData);
  }

//  FIELDS FOR GRID : NEEDS FILTERING
//	private String sessionName;
//	private String sessionStartTime;
//	private String sessionEndTime;
//	private String sessionDate;
  
  //

  //	ui-grid properties : remove table names
//  $scope.sessionGrid = {
//			enableGridMenus: false,
//			enableSorting: false,
//			enableFiltering: false,
//			enableCellEdit: false,
//			enableColumnMenus: false,
//			enableVerticalScrollbar: 0,
//		    columnDefs: [
//	             {
//           	 name: 'sessionName',  width: '35%', 
//           	 displayName: 'Session'
//            },
//            {
//           	 name: 'sessionDate', width: '30%', 
//           	 displayName: 'Session Date'
//            },
//            {
//           	 name: 'sessionStartTime', width: '35%', 
//           	 displayName: 'Session Time',
//           	 cellTemplate: '<img src="images/snb_logo.png">'
//            }
//            ]
//		    ,onRegisterApi: function( gridApi ) { 
//		      $scope.gridApi = gridApi;
//		      var cellTemplate = 'ui-grid/selectionRowHeader';   // you could use your own template here
//		      $scope.gridApi.core.addRowHeaderColumn( { name: 'rowHeaderCol', displayName: '', width: 30, cellTemplate: cellTemplate} );
//  }
  
});
