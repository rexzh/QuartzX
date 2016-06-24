app.controller('AboutCtrl', function ($scope, $L, resetMenu) {
    $scope.home = $L("Home");
    $scope.about = $L("About");
    $scope.quartzx = $L("QuartzX");
    $scope.version = $L("Version");

    $scope.resetMenu = resetMenu;

	/*
    AboutResource.query().then(function (data) {
        $scope.v = data.version;
    }, function (err) {
        err.scope = $scope;
        $scope.$emit('ajaxError', err);
    });
	*/
});