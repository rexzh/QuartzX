app.controller('MonitorCtrl', function ($scope, $rootScope, $interval, MonitorResource, $location, $L, resetMenu) {
    $scope.resetMenu = resetMenu;

    $scope.home = $L("Home");
    $scope.monitor = $L("Monitor");
    $scope.pieChart = $L("PieChart");
    $scope.barChart = $L("BarChart");
    $scope.lineChart = $L("LineChart");

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
            } else if($scope.chart == 'line') {
                var hash = {};
                var keys = [];
                //console.log(r);
                for(var d in r) {
                    var agg = r[d];

                    for(var p in agg) {
                        //p, agg[p]
                        if(hash[p]) {
                            var s = hash[p];
                            hash[p] = s + agg[p];
                        } else {
                            hash[p] = agg[p];
                            keys.push(p);
                        }
                    }
                }
                keys.sort();
                var lines = [];
                for(var i = 0; i < keys.length; i++) {
                    lines.push([keys[i], hash[[keys[i]]]]);
                }
                $scope.data = lines;
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