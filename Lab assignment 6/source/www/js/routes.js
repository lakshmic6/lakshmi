angular.module('app.routes', [])

.config(function($stateProvider, $urlRouterProvider) {

  // Ionic uses AngularUI Router which uses the concept of states
  // Learn more here: https://github.com/angular-ui/ui-router
  // Set up the various states which the app can be in.
  // Each state's controller can be found in controllers.js
  $stateProvider
    
  

      .state('login', {
    url: '/page1',
    templateUrl: 'templates/login.html',
    controller: 'loginCtrl'
  })

  .state('register', {
    url: '/page3',
    templateUrl: 'templates/register.html',
    controller: 'registerCtrl'
  })

  .state('detectBloodPressure', {
    url: '/page4',
    templateUrl: 'templates/detectBloodPressure.html',
    controller: 'detectBloodPressureCtrl'
  })

  .state('login2', {
    url: '/page5',
    templateUrl: 'templates/login2.html',
    controller: 'login2Ctrl'
  })

$urlRouterProvider.otherwise('/page1')

  

});