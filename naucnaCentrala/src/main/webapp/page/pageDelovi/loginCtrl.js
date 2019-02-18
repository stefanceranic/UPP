naucnaCentrala.controller('loginCtrl', ['$scope','$state','$http', function ($scope,$state,$http) {

    $scope.properties=[];
    $scope.model={};
    $scope.gde={};
    $scope.toRegister = function() {
        $http.get("http://localhost:8080/welcome/register/"+$scope.properties.taskId)
            .then(function (response) { $state.go("register"); }
            , function (response) {

                });
    };

    $scope.pokreniProccess = function() {
        $http.get("http://localhost:8080/welcome/get")
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
        $http.post("http://localhost:8080/welcome/postLogin/"+$scope.properties.taskId, o)
            .then(function (response) {
                    $http.get("http://localhost:8080/welcome/isLoggedIn/"+$scope.properties.taskId)
                        .then(function(response) {
                            $scope.gde=response.data;
                            if(!$scope.gde)
                            {
                                alert("Pogresan username ili password");
                                $state.go("login");
                            }
                            else {
                                alert("Uspesno ste ulogovani");
                                $state.go("biranjeCasopisa");
                            }
                        }, function(response) {
                        });
                }
                , function (response) {

                });

    }
}]);