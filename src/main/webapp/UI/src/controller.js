app.controller('NavCtrl', function ($rootScope, $scope, $location, $L) {
    $scope.brand = $L("QuartzX");
	$scope.loggedIn = false;

    $scope.auth = function(){
        if(!$rootScope.user) {//Not login, goto login page.
            $location.path("/auth/");
        } else {//Login already, log out.
			$rootScope.user = null;
			$rootScope.token = null;
			$scope.$emit('authExit', null);
			$scope.loggedIn = false;
        }
    };

    $rootScope.$on('authSuccess', function(evt, args) {
		$scope.loggedIn = true;
		$scope.username = args.username;
		console.log(args);
    });
});

app.controller('MenuCtrl', function ($rootScope, $scope, $interval, $L, gritter) {
    $scope.monitor = $L("Monitor");
    $scope.language = $L("Language");
    $scope.settings = $L("Settings");
    $scope.management = $L("Management");
    $scope.history = $L("History");
    $scope.about = $L("About");
    $scope.dashboard = $L("Dashboard");
    $scope.messages = $L("Messages");
    $scope.password = $L("Password");

    var frequency = localStorage.getItem('frequency') || 5000;

    var _lastAjaxError = null;

    $rootScope.$on('ajaxError', function (evt, args) {
        if (!args) {
            args = $L('Communication Error');
        }
        gritter.push($L('Communication Error'), args, 'gritter-red');
        _lastAjaxError = new Date();
    });

    $rootScope.$on('save', function (evt, args) {
        gritter.push($L('Save success'), args, 'gritter-green');
    });

    $rootScope.$on('authError', function (evt, args) {
        gritter.push($L('Authentication Fail'), args, 'gritter-red');
    });
});