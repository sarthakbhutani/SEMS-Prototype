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

  function sanatizeTime(timeStr) {
    var firstChar = timeStr.slice(0, 1);
    var firstTwoChar = timeStr.slice(0, 2);
    if (firstTwoChar == "11" || firstTwoChar == "24") {
      timeStr = timeStr + " AM";
    } else if (firstChar == "2" || (firstChar == "1" && firstTwoChar != "11")) {
      if ((firstTwoChar = "13")) {
        firstTwoChar = "1";
      } else if ((firstTwoChar = "13")) {
        firstTwoChar = "1";
      } else if ((firstTwoChar = "14")) {
        firstTwoChar = "2";
      } else if ((firstTwoChar = "15")) {
        firstTwoChar = "3";
      } else if ((firstTwoChar = "16")) {
        firstTwoChar = "4";
      } else if ((firstTwoChar = "17")) {
        firstTwoChar = "5";
      } else if ((firstTwoChar = "18")) {
        firstTwoChar = "6";
      } else if ((firstTwoChar = "19")) {
        firstTwoChar = "7";
      } else if ((firstTwoChar = "20")) {
        firstTwoChar = "8";
      } else if ((firstTwoChar = "21")) {
        firstTwoChar = "9";
      } else if ((firstTwoChar = "22")) {
        firstTwoChar = "10";
      } else if ((firstTwoChar = "23")) {
        firstTwoChar = "11";
      }
      timeStr = firstTwoChar + timeStr.slice(2) + " PM";
    } else if (firstChar === "0") {
      timeStr = " " + timeStr.slice(1) + " AM";
    }
    return timeStr;
  }

  //not working : show dateElement as undefined (removed from html now)
  $scope.sanatizeDate = function () {
    //runOnlyOneTime
    var str = $scope.dateElement;
    str = str + '<span class="dateDecoration">th</span>';
    $scope.dateElement = str;
  };

  let fillCategories = function () {
    var courseList = $scope.courseList;
    var categoryList = [];
    for (i of courseList) {
      for (j of i.category) {
        if (!categoryList.includes(j)) {
          categoryList.push(j);
        }
      }
    }
    $scope.categoryList = categoryList;
  };

  //ng-click trigger
  $scope.populateModal = function (courseId, courselist) {
    $http
      .get("/getCourseSession?courseId=" + courseId)
      .then(function (response) {
        if (response.data == null) {
          $scope.errorMsg = "couldn't load data";
        } else {
          //populate ui-grid
          $scope.errorMsg = "Loading data : takes time to fill the grid";
          $scope.successMsg = "Success";
          sessionDetails = response.data;
          //manipulating grid data
          $scope.sessionTableData = sanatiseGrid(sessionDetails);
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

  function sanatiseGrid(sessionDetails) {
    var sessionTableData = [];
    for (i of sessionDetails) {
      var obj = {};
      obj.session = i.sessionName;
      obj.sessionDate = i.sessionDate;
      obj.SessionTime =
        sanatizeTime(i.sessionStartTme) +
        " - " +
        sanatizeTime(i.sessionEndTime) +
        " (IST)";
      sessionTableData.push(obj);
    }
    //ui grid may taks time to render, that's why showing the result in console
    console.log(
      "ui grid may taks time to render, that's why showing the result in console"
    );
    console.log(sessionTableData);
    return sessionTableData;
  }
});
