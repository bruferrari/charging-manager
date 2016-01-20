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