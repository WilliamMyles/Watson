$(function() {
	$("#myTable").tablesorter();
	
	$(function() {
		$('*[data-href]').click(function() {
			window.location = $(this).data('href');
		});
	});
});
