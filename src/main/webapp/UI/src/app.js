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
        when('/about/', {
            templateUrl: './src/about/about.html',
            controller: 'AboutCtrl'
        }).
        when('/auth/', {
            templateUrl: './src/auth/auth.html',
            controller: 'AuthCtrl'
        }).
        otherwise({
            redirectTo: '/dashboard/'
        })
});