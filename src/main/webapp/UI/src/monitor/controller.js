app.controller('MonitorCtrl', function ($scope, $rootScope, $interval, MonitorResource, $location, $L, resetMenu) {


    $scope.resetMenu = resetMenu;

    $scope.home = $L("Home");
    $scope.monitor = $L("Monitor");

    var frequency = localStorage.getItem('frequency') || 5000;


    function refreshMonitor() {
        MonitorResource.query($rootScope.token || '').then(function (data) {
            var r = data.aggregateMap;
            var bars = [];

            for(var d in r) {
                var x = [];
                var agg = r[d];
                for(var p in agg) {
                    x.push([p, agg[p]]);
                }
                bars.push({"data": x, "label": d});
            }

            $scope.data = bars;
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