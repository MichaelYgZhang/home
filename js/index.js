
var app = angular.module('app',['ngAnimate','ui.bootstrap']);

app.controller('indexController', function($scope,$http,$timeout) {

	$scope.temp = [], // 当前页显示 默认每页显示10个()
	$scope.blogs = [], // 所有数据
	$scope.currentPage = 1,
	$scope.numPerPage = 4,//此处修改每页显示多少条数据，同时对应源码中要修改 itemsPerPage
	$scope.maxSize = 5;


	$http.get('blog.json').success(function(data) {
		$scope.blogs = data;
		$scope.$watch('currentPage + numPerPage', function() {
			var begin = (($scope.currentPage - 1) * $scope.numPerPage),
				end = begin + $scope.numPerPage;
			$scope.temp = $scope.blogs.slice(begin, end);
		});

		$scope.tempPages = Math.ceil($scope.blogs.length);

		$scope.filter = function () {
			$timeout(function () {
				$scope.tempPages = Math.ceil($scope.filtered.length);
			}, 100);
		};
	});

	$scope.paginationIsShow = true;
	$scope.islayout = true;
	$scope.isnewview = false;
	$scope.islayout.className = "c1 on";

	$scope.showV = function(){
		$scope.islayout = false;
		$scope.isnewview = true;
		$scope.islayout.className = "c1 on";
	};
	$scope.showH = function(){
		$scope.islayout = true;
		$scope.isnewview = false;
		$scope.islayout.className = "c1";
	};
	$scope.searchClick = function(){
		$scope.isShowContent = false;
		$scope.paginationIsShow = true;
	};

	$scope.showContent = function(blog){
		$scope.paginationIsShow = false;
		$scope.isShowContent = true;
		$scope.template = {url:""+blog.ahref+""};
	};


	$scope.html5_click = function(){
		$scope.searchText = "HTML5";
	};
	$scope.css3_click = function(){
		$scope.searchText = "CSS3";
	};
	$scope.java_click = function(){
		$scope.searchText = "Java";
	};
	$scope.tools_click = function(){
		$scope.searchText = "Tools";
	};

});

app.filter('startFrom', function() {
	return function(input, start) {
		if(input) {
			start = +start; //parse to int
			return input.slice(start);
		}
		return [];
	}
});
/**
 * dom方式 操作横向布局还是列布局
 */

//var obj = {
//	t: document.getElementById("layoutTarget"),
//	h: document.getElementById("layoutH"),
//	v: document.getElementById("layoutV")
//};
//
//obj.h.onclick = function(){
//	//判断当前布局
//	if(this.className === "cl on"){
//		//当前非此布局，进行切换
//		obj.t.className = "layout";
//		this.className = "cl";
//		obj.v.className = "cl on";
//	}
//	return false;
//};
//obj.v.onclick = function(){
//	//判断当前布局
//	if(this.className === "cl on"){
//		//当前非此布局，进行切换
//		obj.t.className = "layout newview";
//		this.className = "cl";
//		obj.h.className = "cl on";
//	}
//	return false;
//};

/**
 * backToTop
 */
$(document).ready(function(){
	$(window).scroll( function() {               //滚动时触发
		var top = $(document).scrollTop(),       //获取滚动条到顶部的垂直高度
			height = $(window).height();         //获得可视浏览器的高度
		if(top > 100){
			$("#backToTop").show(200, function(){
				$("#backToTop").css({
					top: height + top - 100
				})
			});
		}
	});
	/*点击回到顶部*/
	$('#backToTop').click(function(){
		$('html, body').animate({
			scrollTop: 0
		}, 500);
	});
});
//延迟加载图片  插件为 echo.js
echo.init({
	offset: 100,
	throttle: 250,
	unload: true,
	callback: function (element, op) {
		//console.log(element, 'has been', op + 'ed')
	}
});
