angular.module('market-front').controller('regController', function ($scope, $http) {
    const contextPath = 'http://localhost:5555/auth/reg/';

    $scope.tryToReg = function () {
        $http.post(contextPath, $scope.newUser)
            .then(function successCallback(response) {
                alert("User " + newUser.login + " registration was successful");
            }, function errorCallback(response) {
                alert("Error in registration!!!");
            });
    };

});