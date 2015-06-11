
$(document).ready(function() {
	console.log("页面加载完成。。。");
});

var app = angular.module('app',['ngAnimate']);
/**
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




 <li class="dropdown">
 <a href="#" class="dropdown-toggle" data-toggle="dropdown">
 JAVA
 <span class="caret"></span></a>
 <ul class="dropdown-menu" role="menu">
 <li><a href="#tab-chrome">Java SE</a></li>
 <li><a href="#tab-firefox">Java EE</a></li>
 <li><a href="#tab-ie">Java WEB</a></li>
 <li><a href="#tab-ie">Java API</a></li>
 <li><a href="#tab-ie">Tools</a></li>
 </ul>
 </li>
*/
app.controller('indexController', ['$scope', function($scope) {
		$scope.web =
				[ { name: 'Flex-ActionScript', url: 'web/flex-as/flex_as_index.html'},
				{ name: 'CSS3', url: 'web/css/css3_index.html'},
				{ name: 'HTML5', url: 'web/html5/h5_index.html'},
				{ name: 'Bootstrap', url: 'web/bootstrap/bootstrap_index.html'},
				{ name: 'AngularJS', url: 'web/angularjs/angularjs_index.html'},
				];
		$scope.java =
				[ { name: 'Java SE', url: 'java/java-se/javase_index.html'},
				{ name: 'Java EE', url: 'java/java-ee/javaee_index.html'},
				{ name: 'Java WEB', url: 'java/java-web/javaweb_index.html'},
				{ name: 'Java API', url: 'java/java-api/javaapi_index.html'},
				];
		//$scope.template = $scope.web[0];
}]);