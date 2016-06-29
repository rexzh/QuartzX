(function () {
    'use strict';
	var baseUrl = '/data-collector/v1.0';
	var svc = angular.module('data.service', ['ngResource']);

	function errMsg(url, response) {
	    var msg = 'Error when call: ' + url;
	    if (response) {
	        msg += ' [' + response.status + ':' + response.statusText + '] ';
	    }
	    
	    if (response && response.data && response.data.errorMessage) {
	        msg = response.data.errorMessage + msg;
	    }
	    return msg;
	};

	function simpleQuery($resource, $q, url) {
	    var res = $resource(url);
	    var delay = $q.defer();
	    res.get(
            function (response) {
                if (response) {
                    delay.resolve(response);
                } else {
                    delay.reject(errMsg(url, response));
                }
            },
            function (response) {
                delay.reject(errMsg(url, response));
            });

	    return delay.promise;
	};

	function arrayQuery($resource, $q, url) {
        var res = $resource(url);
        var delay = $q.defer();
        res.query(
            function (response) {
                if (response) {
                    delay.resolve(response);
                } else {
                    delay.reject(errMsg(url, response));
                }
            },
            function (response) {
                delay.reject(errMsg(url, response));
            });

        return delay.promise;
    };

	svc.factory('SummaryResource', function($resource, $q) {
	    return {
    	    query: function () {
    	        var url = baseUrl + "/summary";
    	        return simpleQuery($resource, $q, url);
    	    }
    	}
	});

	svc.factory('MonitorResource', function($resource, $q) {
        return {
            query: function () {
                var url = baseUrl + "/monitor";
                return simpleQuery($resource, $q, url);
            }
        }
    });

    svc.factory('MessageResource', function($resource, $q) {
        return {
            query: function () {
                var url = baseUrl + "/message";
                return arrayQuery($resource, $q, url);
            }
        }
    });

	svc.factory('AboutResource', function ($resource, $q) {
	    return {
	        query: function () {
	            var url = baseUrl + "/about";
	            return simpleQuery($resource, $q, url);
	        }
	    }
	});

    svc.factory('SystemResource', function ($resource, $q) {
        return {
            query: function () {
                var url = baseUrl + "/system";
                return simpleQuery($resource, $q, url);
            }
        }
	});
})();