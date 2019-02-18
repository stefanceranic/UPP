var naucnaCentrala= angular.module('naucnaCentrala',['ui.router'])

naucnaCentrala.config(['$urlRouterProvider','$stateProvider',function($urlRouterProvider,$stateProvider) {
    $urlRouterProvider.otherwise('/login');
    $stateProvider   
        .state('register',{
            url:'/register',
            controller: 'registerCtrl',
            templateUrl: 'pageDelovi/register.html'
        })
        .state('login',{
            url:'/login',
            controller: 'loginCtrl',
            templateUrl: 'pageDelovi/login.html'
        })
        .state('biranjeCasopisa',{
            url:'/biranjeCasopisa',
            controller: 'biranjeCasopisaCtrl',
            templateUrl: 'pageDelovi/biranjeCasopisa.html'
        })
        .state('prijavaRada',{
            url:'/prijavaRada',
            controller: 'prijavaRada',
            templateUrl: 'pageDelovi/prijavaRada.html'
        })

}]);