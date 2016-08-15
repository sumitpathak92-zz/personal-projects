var phonecatApp = angular.module('phonecatApp', []);
phonecatApp.controller('PhoneListCtrl',['$scope', '$http', function($scope, $http) {
$http.get('file:///home/sumit/Desktop/Angular_Tutorial/app/phones/phones.json').success(function(data) {
$scope.phones = data;
console.log("Phones = " +JSON.stringify(data));
});
$scope.orderProp = 'age';
}]);
