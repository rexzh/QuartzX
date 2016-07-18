app.controller('AuthCtrl', function ($rootScope, $scope, $location, AuthResource, $L, resetMenu) {
    $scope.home = $L("Home");
    $scope.auth = $L("Login");
    $scope.ok = $L("OK");
    $scope.usernameLabel = $L("Username");
    $scope.passwordLabel = $L("Password");

    $scope.resetMenu = resetMenu;
	$scope.uid = '';
    $scope.pwd = '';
    $scope.authentication = function () {
        AuthResource.auth($scope.uid, $scope.pwd).then(function (data) {
            if (data.success) {
				$rootScope.user = data;
                $location.path("/dashboard/");
				$scope.$emit('authSuccess', data);
            } else {
                $scope.$emit('authError', $L('Authentication Fail'));
            }
        }, function (err) {
            err.scope = $scope;
            $scope.$emit('ajaxError', err);
        });
    }
});