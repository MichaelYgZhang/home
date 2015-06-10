
$(document).ready(function() {
			$("#demo-dropdown .dropdown-menu a").click(function() {
				var href = $(this).attr("href");

				$("#myTab a[href='" + href + "']").tab("show");

			});
		});
