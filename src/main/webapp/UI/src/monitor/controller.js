app.controller('MonitorCtrl', function ($scope, StatusResource, $interval, $location, $L, resetMenu) {
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
        StatusResource.queryAll().then(function (data) {
            var boxes = [];
            for (var i = 0; i < data.boxes.length; i++) {
                var box = { 'boxId': data.boxes[i].boxId, 'boxName': data.boxes[i].boxName, 'rows': [] };
                boxes.push(box);
                var row = [];
                for (var k = 0; k < 80; k++) {
                    if (data.boxes[i].blocks[k].name) {
                        var block = { 'blockId': data.boxes[i].blocks[k].id, 'blockName': data.boxes[i].blocks[k].name, 'total': data.boxes[i].blocks[k].total };
                        block.color = mapStatusColor[data.boxes[i].blocks[k].status];
                        row.push(block);                        
                    }
                    if (row.length == 10) {
                        box.rows.push(row);
                        row = [];
                    }
                }
                if (row.length > 0)
                    box.rows.push(row);
            }
            $scope.boxes = boxes;
        }, function (err) {
            err.scope = $scope;
            $scope.$emit('ajaxError', err);
        });
    };

    refreshMonitor();
    
    var p = $interval(refreshMonitor, frequency);
    $scope.$on('$destroy', function () {
        if (angular.isDefined(p)) {
            $interval.cancel(p);
            p = undefined;
        }
    })
});