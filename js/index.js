
var app = angular.module('app',['ngAnimate']);

app.controller('indexController', function($scope,$http) {

	$http.get('blog.json').success(function(data) {$scope.blogs = data;});

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


	$scope.showContent = function(blog){
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


	app.directive('myDirective',function(){
		return{
			restrict: "AECM",
			transclude: true,//true,表示在页面中嵌套的元素将显示出来,不进行覆盖.false(默认):表示覆盖
			template: "<span>Hello, Directive, <span ng-transclude></span></span>"
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
	unload: false,
	callback: function (element, op) {
		//console.log(element, 'has been', op + 'ed')
	}
});