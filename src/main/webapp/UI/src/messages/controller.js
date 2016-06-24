app.controller('MessagesCtrl', function ($scope, $interval, $L, timeFormatter, resetMenu) {
    var mapStatusType = {
        "Normal": 'success',
        "High": 'block',
        "Low": 'block',
        "ExHigh": 'error',
        "ExLow": 'error',
        "Fail": 'error'
    };

    $scope.home = $L("Home");
    $scope.messages = $L("Messages");

    $scope.resetMenu = resetMenu;

    var count = localStorage.getItem('msgCount') || 10;
    
	/*
    function refresh() {
        MessageResource.query().then(function (data) {
            var l = [];
            for (var i = 0; i < data.length; i++) {
                var m = data[i];
                var t = new Date(m.change.changeTime / 100);
                
                m.time = timeFormatter.format(t);
                
                m.text = '[' + m.boxName + '][' + m.blockName + '][' + m.sensorName + ']' + $L('Status') + ': ' + $L(m.change.from) + '->' + $L(m.change.to);
                m.type = mapStatusType[m.change.to];
                l.push(m);
            }            
            l.sort(function (x, y) {
                return y.time - x.time;//y - x, order by time desc
            });
            if (l.length > count)
                l = l.slice(0, count);
            
            $scope.msgList = l;
        }, function (err) {
            err.scope = $scope;
            $scope.$emit('ajaxError', err);
        });
    }

    refresh();

    var frequency = localStorage.getItem('frequency') || 5000;
    
    var p = $interval(refresh, frequency);
    $scope.$on('$destroy', function () {
        if (angular.isDefined(p)) {
            $interval.cancel(p);
            p = undefined;
        }
    })
	*/
});