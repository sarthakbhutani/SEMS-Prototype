var myApp = angular.module('app');

myApp.controller("viewTrainingController", function($scope,$http){
	$http.get("/getAllCourses").then(function (response) {
	    if (response.data == null) {
	      $scope.courseList = [];
	    } else {
	      $scope.courseList = response.data;
	    }
	})
	//then closed here
	
	//ng-click trigger
	function getCourseByIndex($index){ //		by $index FOR EASE
		$http.get("/getCourseById?courseId="+$index.courseId).then(function(response){
			// Populate Modal Box Details & Show it
			
		});
		
	}
	
	
	//ui-grid properties
//	$scope.details = {
//			enableGridMenus: false,
//			enableSorting: false,
//			enableFiltering: false,
//			enableCellEdit: false,
//			enableColumnMenus: false,
//			enableVerticalScrollbar: 0,
//			paginationPageSizes: [5, 10, 20, 30],
//			paginationPageSize: 10,
//			useExternalPagination: true,
//			columnDefs: [
//			             {
//			            	 name: 'batchId',  width: '11%', 
//			            	 displayName: 'Batch ID'
//			             },
//			             {
//			            	 name: 'dateUploaded', width: '12%', 
//			            	 displayName: 'Uploaded On'
//			             },
//			             {
//			            	 name: 'documentsUploaded', width: '65%', 
//			            	 displayName: 'Documents Uploaded'
//			             },
//			             {
//			            	 name: 'zipFileLink',  width: '12%', 
//			            	 displayName: 'Download Zip File', 
//			            	 cellTemplate: '<img src="images/rar_icon_noBackground.png" alt="Zip Icon" ng-click="grid.appScope.downloadZip()" class="pointer">'
//
//			             }
//			             ]
//
//	};
	
});
