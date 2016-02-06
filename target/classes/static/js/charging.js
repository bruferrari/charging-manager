$('#confirmRemoveModal').on('show.bs.modal', function(event) {
	var button = $(event.relatedTarget);
	var titleId = button.data('id');
	var titleDescription = button.data('description');

	var modal = $(this);
	var form = modal.find('form');
	var action = form.data('url-base');
	if(!action.endsWith('/')) {
		action += '/';
	}
	form.attr('action', action + titleId);
	
	modal.find('.modal-body span').html('Do you want to remove title <strong>' + titleDescription + '</strong>?');
	
});

$(function() {
	$('[rel="tooltip"]').tooltip();
	$('.js-currency').maskMoney({decimal: ',', thousands: '.', allowZero: true});
	
	$('.js-refresh-status').on('click', function(event) {
		event.preventDefault();
		var receiveBtn = $(event.currentTarget);
		var urlReceive = receiveBtn.attr('href');
		
		var response = $.ajax({
			url: urlReceive,
			type: 'PUT'
		});
		
		response.done(function(e) {
			var titleId = receiveBtn.data('id');
			console.log(titleId);
			$('[data-role=' + titleId + ']').html('<span class="label label-success">' + e + '</span>');
			receiveBtn.hide();
		});
		
		response.fail(function(e) {
			console.log(e);
			alert('An error has been occurred when tying to update status');
		});
	});
});