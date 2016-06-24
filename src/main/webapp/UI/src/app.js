var app = angular.module('AppModule', ['ngRoute', 'metro.directive', 'data.service', 'l10n']);

app.constant('resetMenu', function () {
    var mn = $("#sidebar-left").first();
    mn.find("a").each(function () {
        if ($(this).attr('href') == '#/dashboard/')
            $(this).parent().addClass('active');
        else
            $(this).parent().removeClass('active');
    });
});

app.config(function ($routeProvider, $LProvider) {
    var lang = localStorage.getItem('lang');
    if(lang)
        $LProvider.setLocale(lang);
    else
        $LProvider.setLocale('en_us');

    $routeProvider.
        when('/monitor/', {
            templateUrl: './src/monitor/monitor.html',
            controller: 'MonitorCtrl'
        }).
        when('/block/:boxId/:blockId/', {
            templateUrl: './src/block/block.html',
            controller: 'BlockCtrl'
        }).
        when('/sensor/:boxId/:blockId/:sensorId', {
            templateUrl: './src/sensor/sensor.html',
            controller: 'SensorCtrl'
        }).
        when('/messages/', {
            templateUrl: './src/messages/messages.html',
            controller: 'MessagesCtrl'
        }).
        when('/dashboard/', {
            templateUrl: './src/dashboard/dashboard.html',
            controller: 'DashboardCtrl'
        }).
        when('/settings/', {
            templateUrl: './src/settings/settings.html',
            controller: 'SettingsCtrl'
        }).
        when('/management/', {
            templateUrl: './src/management/management.html',
            controller: 'ManagementCtrl'
        }).
        when('/history/:boxId/:blockId/:sensorId', {
            templateUrl: './src/history/history.html',
            controller: 'HistoryCtrl'
        }).
        when('/about/', {
            templateUrl: './src/about/about.html',
            controller: 'AboutCtrl'
        }).
        when('/auth/', {
            templateUrl: './src/auth/auth.html',
            controller: 'AuthCtrl'
        }).
        when('/chgpwd/', {
            templateUrl: './src/chgpwd/chgpwd.html',
            controller: 'ChgpwdCtrl'
        }).
        otherwise({
            redirectTo: '/dashboard/'
        })
});