app.controller('NavCtrl', function ($scope, $L) {
    $scope.brand = $L("QuartzX");
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

    var ajaxError;
    var pointError;
    var notification = $('#msg').first();
});