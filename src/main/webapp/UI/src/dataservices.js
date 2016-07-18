(function () {
    'use strict';
    
    var baseUrl = '/v1.0';
    if(window.location.href.indexOf('data-collector') > 0)
        baseUrl = '/data-collector/v1.0';


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
	
	svc.factory('AuthResource', function($http, $q) {
	    return {
    	    auth: function (uid, pwd) {
    	        var url = baseUrl + "/user";    	        
	            var delay = $q.defer();
	            $http.post(url, {'username': uid, 'password': pwd}).success(function (data, status) {
	                delay.resolve(data);
	            }).error(function (data, status) {
	                delay.reject(data);
	            });
	            return delay.promise;
    	    }
    	}
	});

	svc.factory('SummaryResource', function($resource, $http, $q) {
	    return {
    	    query: function (token) {
				$http.defaults.headers.common['Authorization'] = 'Basic ' + token;
    	        var url = baseUrl + "/summary";
    	        return simpleQuery($resource, $q, url);
    	    }
    	}
	});

	svc.factory('MonitorResource', function($resource, $http, $q) {
        return {
            query: function (token) {
				$http.defaults.headers.common['Authorization'] = 'Basic ' + token;
                var url = baseUrl + "/monitor";
                return simpleQuery($resource, $q, url);
            }
        }
    });

    svc.factory('MessageResource', function($resource, $http, $q) {
        return {
            query: function (token) {
				$http.defaults.headers.common['Authorization'] = 'Basic ' + token;
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