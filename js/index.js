
$(document).ready(function() {
	console.log("页面加载完成。。。");
});

var app = angular.module('app',['ngAnimate']);
app.directive('myTabs', function() {
		return {
			restrict: 'E',
			transclude: true,
			scope: {},
			controller: function($scope) {
				var panes = $scope.panes = [];

				$scope.select = function(pane) {
					angular.forEach(panes, function(pane) {
						pane.selected = false;
					});
					pane.selected = true;
				};

				this.addPane = function(pane) {
					if (panes.length === 0) {
						$scope.select(pane);
					}
					panes.push(pane);
				};
			},
			templateUrl: 'common/my-tabs.html'
		};
	})
	.directive('myPane', function() {
		return {
			require: '^myTabs',
			restrict: 'E',
			transclude: true,
			scope: {
				title: '@'
			},
			link: function(scope, element, attrs, tabsCtrl) {
				tabsCtrl.addPane(scope);
			},
			templateUrl: 'common/my-pane.html'
		};
	});

app.controller('ExampleController', ['$scope', function($scope) {
		$scope.templates =
			[ { name: 'template1.html', url: 'common/page1.html'},
				{ name: 'template2.html', url: 'common/page2.html'} ];
		$scope.template = $scope.templates[0];
}]);