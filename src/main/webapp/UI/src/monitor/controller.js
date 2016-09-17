app.controller('MonitorCtrl', function ($scope, $rootScope, $interval, MonitorResource, $location, $L, resetMenu) {
    $scope.resetMenu = resetMenu;

    $scope.home = $L("Home");
    $scope.monitor = $L("Monitor");
    $scope.pieChart = $L("PieChart");
    $scope.barChart = $L("BarChart");

    var frequency = localStorage.getItem('frequency') || 5000;

    $scope.chart = 'bar';
    $scope.selectedChart = $scope.barChart;

    $scope.selChart = function (chart) {
        $scope.selectedChart = $('a[data-chart="' + chart + '"]').first().text();
        $scope.chart = chart;
    }

    function refreshMonitor() {
        MonitorResource.query($rootScope.token || '').then(function (data) {
            var r = data.aggregateMap;
            if($scope.chart == 'bar') {
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
            } else { //pie
                var pies = [];
                for(var d in r) {
                    var sum = 0;
                    for(var t in r[d]) {
                        var c = r[d][t];
                        sum += c;
                    }
                    pies.push({label: d, data: sum});
                }
                console.log(pies);
                $scope.data = pies;
            }
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