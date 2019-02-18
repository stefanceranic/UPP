naucnaCentrala.controller('prijavaRada', ['$scope','$state','$http', function ($scope,$state,$http){
    
	$scope.model = {};
	$scope.properties = [];
	$scope.pokreniProccess = function() {
	        $http.get("http://localhost:8080/welcome/getFF")
	            .then(function(response) {
	                $scope.properties=response.data;
                    
	            }, function(response) {
	            });
	    }
	    $scope.pokreniProccess();

	    
	    $scope.submit=function(){
	        var o = [];
	        for (var m in $scope.model) {
	            console.log(m + "  -  " + $scope.model[m]);
	            o.push({ "fieldId": m, "fieldValue": $scope.model[m] });

	        }
	        console.log(o);
	        $http.post("http://localhost:8080/welcome/postRad/"+$scope.properties.taskId, o)
	            .then(function (response) {
	                    
	                }
	                , function (response) {

	                });

	    }   
}]);