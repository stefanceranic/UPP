naucnaCentrala.controller('biranjeCasopisaCtrl', ['$scope','$state','$http', function ($scope,$state,$http) {

    $scope.properties={};

    $scope.getPolja = function() {
        $http.get("http://localhost:8080/welcome/getFF")
            .then(function(response) {
                $scope.properties=response.data;
                console.log($scope.properties);
            }, function(response) {
            });
    }
    $scope.getPolja();
    
    
    $scope.sumbitOdabrani = function(x) {
    	$http.post("http://localhost:8080/welcome/odabraniCasopis/"+$scope.properties.taskId, x).then(function(response){
    		$state.go("prijavaRada");
    	})
    }  
    
    
    
    
}])