//window.onbeforeunload = function(event) {
//	(event || window.event).returnValue = "感谢来到小站,期待下次交流,谢谢！";
//}
var app = angular.module('app',['ngAnimate']);

app.controller('indexController', ['$scope', function($scope) {

	$scope.blogs = [
					{ahref:'WEB/HTML5/read-html5-cookbook.html',imgsrc:'http://www.w3.org/html/logo/img/mark-word-icon.png',titletext:'HTML5',spantext:'Reading HTML5 Cookbook Note.'}
				   ];

	$scope.islayout = false;
	$scope.isnewview = true;
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

}]);

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