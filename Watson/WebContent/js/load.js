$(function() {
	$(".main-table").tablesorter();
	
	$(function() {
		$('*[data-href]').click(function() {
			window.location = $(this).data('href');
		});
	});
	
	$("input[type=range]").each(function() {
		$(this).on("change mousemove", function() {
			$(this).next().html($(this).val());
		});
	});
});

function hide(event, id) {
	$("#" +  id).css("display", "none");
	
	$(event).attr("onClick", "show(this, "+id+");");
	$(event).html("Show");
	
}

function show(event, id) {
	$("#" +  id).css("display", "table");
	
	$(event).attr("onClick", "hide(this, "+id+");");
	$(event).html("Hide");
}
