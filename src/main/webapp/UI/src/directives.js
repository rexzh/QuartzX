(function () {
    'use strict';
    var metro = angular.module('metro.directive', []);

    metro.factory('gritter', function () {
        return {
            push: function (title, text, className, sticky) {
                $.gritter.add({
                    "title": title,
                    "text": text,
                    "sticky": sticky,
                    "class_name": className
                });
            }
        }
    });

    metro.factory('timeFormatter', function () {
        return {
            format: function (t) {
                var yyyy = t.getFullYear().toString();
                var mm = (t.getMonth() + 1).toString();
                var dd = t.getDate().toString();
                var hh = t.getHours().toString();
                var mi = t.getMinutes().toString();
                var ss = t.getSeconds().toString();
                return yyyy + '-' + (mm[1] ? mm : "0" + mm[0]) + '-' + (dd[1] ? dd : "0" + dd[0]) + ' ' +
                        (hh[1] ? hh : '0' + hh[0]) + ':' + (mi[1] ? mi : '0' + mi[0]) + ':' + (ss[1] ? ss : '0' + ss[0]);
            }
        }
    });
    
    metro.directive('metroStatBox', function ($timeout) {
        return {
            restrict: 'AE',
            replace: true,
            scope: {
                span: '@sbSpan',
                color: '@sbColor',
                list: '@sbList',
                number: '@sbNumber',
                title: '@sbTitle',
                link: '@sbLink',
                icon: '@sbIcon'
            },            
            template: '<div class="{{span}} statbox {{color}}">' +
			                '<div class="boxchart {{color}}Font">{{list}}</div>' +
			                '<div class="number">{{number}}<i class="{{icon}}"></i></div>' +
			                '<div class="title">{{title}}</div>' +
			                '<div class="footer">' +
				                '<a>{{link}}</a>' +
			                '</div>' +
		                '</div>',
            compile: function (element, attrs) {
                return function (scope, element, attrs) {
                    function draw() {
                        if (scope.list && scope.list.length > 0) {
                            if ($(".boxchart", element)) {
                                $(".boxchart", element).sparkline('html', {
                                    type: 'bar',
                                    height: '60',
                                    barWidth: '4',
                                    barSpacing: '1',
                                    barColor: '#ffffff',
                                    negBarColor: '#eeeeee'
                                });
                            }
                        }
                    }

                    $timeout(draw, 1);
                }
            }
        }
    });

    metro.directive('metroStatCircle', function ($timeout) {
        return {
            restrict: 'AE',
            replace: true,
            scope: {
                span: '@scSpan',
                color: '@scColor',
                value: '@scValue',
                number: '@scNumber',
                total: '@scTotal',
                unit: '@scUnit',
                title: '@scTitle'
            },
            template: '<div class="{{span}}">' +
                            '<div class="circleStatsItemBox {{color}}">' +
                                '<div class="header">{{title}}</div>' +
                                '<span class="percent">%</span>' +
                                '<div class="circleStat">' +
                                    '<input type="text" value="{{value}}" class="whiteCircle" />' +
                                '</div>' +
                                '<div class="footer">' +
                                    '<span class="count">' +
                                        '<span class="number">{{number}}</span>' +
                                        '<span class="unit">{{unit}}</span>' +
                                    '</span>' +
                                    '<span class="sep"> / </span>' +
                                    '<span class="value">' +
                                        '<span class="number">{{total}}</span>' +
                                        '<span class="unit">{{unit}}</span>' +
                                    '</span>' +
                                '</div>' +
                            '</div>' +
                        '</div>',
            compile: function (element, attrs) {
                return function (scope, element, attrs) {
                    function draw() {
                        $(".whiteCircle", element).knob({
                            'min': 0,
                            'max': 100,
                            'readOnly': true,
                            'width': 120,
                            'height': 120,
                            'bgColor': 'rgba(255,255,255,0.5)',
                            'fgColor': 'rgba(255,255,255,0.9)',
                            'dynamicDraw': true,
                            'thickness': 0.2,
                            'tickColorizeValues': true
                        });

                        var c = $(".circleStatsItemBox", element).first();
                        c.find(".count > .unit").html(scope.unit);
                        c.find(".count > .number").html(scope.number);
                    }

                    
                    $timeout(draw, 200);
                    scope.$watchGroup(['number', 'value'], function () {
                        $(".whiteCircle", element).trigger('change');
                        var c = $(".circleStatsItemBox", element).first();
                        c.find(".count > .unit").html(scope.unit);
                        c.find(".count > .number").html(scope.number);
                    });
                }
            }
        }
    });

    metro.directive('metroButton', function () {
        return {
            restrict: 'AE',
            replace: true,
            scope: {
                span: '@btnSpan',
                color: '@btnColor',
                icon: '@btnIcon',                
                text: '@btnText',
                note: '@btnNote'
            },
            template: '<a class="quick-button {{span}}">' +
                            '<i class="{{icon}}"></i>' +
                            '<p>{{text}}</p>' +
                            '<span class="notification {{color}}">{{note}}</span>' +
                        '</a>',
            compile: function (element, attrs) {                
                return function (scope, element, attrs) {                    
                }
            }
        }
    });

    metro.directive('metroSmallButton', function () {
        return {
            restrict: 'AE',
            replace: true,
            scope: {
                span: '@btnSpan',
                color: '@btnColor',
                icon: '@btnIcon',
                text: '@btnText',
                note: '@btnNote'
            },
            template: '<a class="quick-button-small {{span}}">' +
                            '<i class="{{icon}}"></i>' +
                            '<h5>{{text}}</h5>' +
                            '<span class="notification {{color}}">{{note}}</span>' +
                        '</a>',
            compile: function (element, attrs) {
                return function (scope, element, attrs) {
                }
            }
        }
    });

    metro.directive('metroMessage', function () {
        return {
            restrict: 'AE',
            replace: true,
            scope: {
                type: '@msgType',                
                text: '@msgText',
                lead: '@msgLead'
            },
            template: '<div class="alert alert-{{type}}">' +
                            '<strong>{{lead}}</strong> {{text}}' +
                        '</div>',
            compile: function (element, attrs) {
                return function (scope, element, attrs) {
                }
            }
        }
    });

    
    metro.directive('metroTabSet', function () {
        return {
            restrict: 'AE',
            transclude: true,
            replace: true,
            scope: {
                tabChanged: '&'
            },
            template:
                '<div class="box-content">' +
                  '<ul class="nav tab-menu nav-tabs">' +
                    '<li ng-repeat="tab in tabs" ng-class="{active:tab.selected}">' +
                      '<a ng-click="select(tab)">{{tab.tabHeader}}</a>' +
                    '</li>' +
                  '</ul>' +
                  '<div class="tab-content" ng-transclude></div>' +
                '</div>',

            controller: function ($scope) {
                var tabs = $scope.tabs = [];

                $scope.select = function (tab) {
                    angular.forEach(tabs, function (tab) {
                        tab.selected = false;
                    });
                    tab.selected = true;
                    if (tabs.length > 1)
                        $scope.tabChanged && $scope.tabChanged({ tab: tab });
                }

                this.addTab = function (tab) {
                    if (tabs.length == 0) $scope.select(tab);
                    tabs.push(tab);
                }
            }
        };
    });

    metro.directive('metroTab', function () {
        return {
            require: '^metroTabSet',
            restrict: 'AE',
            transclude: true,
            replace: true,
            scope: {
                tabHeader: '@',
                tabId: '@'
            },
            template:
                '<div class="tab-pane" ng-class="{active: selected}" ng-transclude>' +
                '</div>',
            link: function (scope, element, attrs, tabsCtrl) {
                tabsCtrl.addTab(scope);
            },
        };
    });

    metro.directive('metroLineChart', function ($timeout) {
        return {
            restrict: 'AE',
            replace: true,
            scope: {
                data: '=lcData',
                tag: '@lcTag'
            },
            template: '<div></div>',
            compile: function (element, attrs) {
                return function (scope, element, attrs) {
                    function draw() {
                        if (!scope.data) return;
                        var minV = 99999, maxV = -99999, minT = new Date(2999, 12).getTime(), maxT = new Date(1970, 1).getTime();
                        for (var i = 0; i < scope.data.length; i++) {
                            var d = scope.data[i];
                            if (d[1]< minV) minV = d[1];
                            if (d[1] > maxV) maxV = d[1];
                            if (d[0] < minT) minT = d[0];
                            if (d[0] > maxT) maxT = d[0];
                        }
                        $(element).empty();
                        var plot = $.plot($(element),
                           [{ data: scope.data, label: scope.tag }], {
                               series: {
                                   lines: {
                                       show: true,
                                       lineWidth: 2,
                                   },
                                   points: { show: true },
                                   shadowSize: 2
                               },
                               grid: {
                                   hoverable: true,
                                   clickable: false,
                                   tickColor: "#dddddd",
                                   borderWidth: 0
                               },
                               yaxis: { min: minV, max: maxV },
                               xaxis: {
                                   mode: "time",
                                   minTickSize: [1, "second"],
                                   min: (new Date(minT)).getTime(),
                                   max: (new Date(maxT)).getTime()
                               },
                               colors: ["#FA5833"]
                           });
                    }
                    $timeout(draw, 50);
                    scope.$watchGroup(['data'], draw, true);
                }
            }
        }
    });

    metro.directive('metroStackChart', function ($timeout) {
        return {
            restrict: 'AE',
            replace: true,
            scope: {
                data: '=scData',
                tag: '@scTag'
            },
            template: '<div></div>',
            compile: function (element, attrs) {
                return function (scope, element, attrs) {
                    function draw() {
                        if (!scope.data) return;
                        $.plot($(element), scope.data, {
                            series: {
                                stack: 0,
                                lines: { show: false, fill: true, steps: false },
                                bars: { show: true, barWidth: 60 * 600, align: 'center' },//Note: Width = 60 * 1000 * 0.6
                            },
                            colors: ["#FABB3D", "#FA5833"],
                            xaxis: {
                                mode: "time",
                                minTickSize: [1, "minute"]
                            }
                        });
                        
                    }
                    $timeout(draw, 50);
                    scope.$watchGroup(['data'], draw, true);
                }
            }
        }
    });
})();
