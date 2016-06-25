app.controller('MonitorCtrl', function ($scope, $interval, SummaryResource, $location, $L, resetMenu) {
    var mapStatusColor = {
        'Normal': 'green',
        'Warning': 'orange',
        'Danger': 'red'
    };

    $scope.click = function(box, block) {
        if (block.total != 0) {
            $location.path("/block/" + box.boxId + "/" + block.blockId);
        }
    }

    $scope.resetMenu = resetMenu;

    $scope.home = $L("Home");
    $scope.monitor = $L("Monitor");

    var frequency = localStorage.getItem('frequency') || 5000;


    function refreshMonitor() {
        SummaryResource.query().then(function (data) {
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