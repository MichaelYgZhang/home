
$(document).ready(function() {
			$("#demo-dropdown .dropdown-menu a").click(function() {
				var href = $(this).attr("href");

				$("#myTab a[href='" + href + "']").tab("show");

			});
			var today,intYears,intMonths,intHours,intMinutes,intSeconds;
			today = new Date();
			intYears = today.getFullYear();  //得到年份,getFullYear()比getYear()更普适
    			intMonths = today.getMonth() + 1; //得到月份，要加1
    			intDays = today.getDate();   //得到日期
    			intHours = today.getHours();  //得到小时 
    			intMinutes = today.getMinutes(); //得到分钟
    			intSeconds = today.getSeconds(); //得到秒钟
    			currentTime.innerHTML = "今天是"+intYears+"年"+intMonths+"月"+intDays+"日，时间:"+intHours+":"+intMinutes+":"+intSeconds;
		});
