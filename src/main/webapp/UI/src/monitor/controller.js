app.controller('MonitorCtrl', function ($scope, $rootScope, $interval, MonitorResource, $location, $L, resetMenu) {


    $scope.resetMenu = resetMenu;

    $scope.home = $L("Home");
    $scope.monitor = $L("Monitor");

    var frequency = localStorage.getItem('frequency') || 5000;


    function refreshMonitor() {
        MonitorResource.query($rootScope.token || '').then(function (data) {
            var x = [];
            var r = data.minuteAggregateMap;
            for(var p in r) {
                x.push([p, r[p]]);
            }
            $scope.data = [{"data": x, "label": $L("RFID")}];
        }, function (err) {

        });
    };

    refreshMonitor();
    
    var p = $interval(refreshMonitor, frequency);
    $scope.$on('$destroy', function () {
        if (angular.isDefined(p)) {
            $interval.cancel(p);
            p = undefined;
        }
    });
});