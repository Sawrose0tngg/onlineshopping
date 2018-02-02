$(function() {
	switch (menu) {
	case 'Home':
		$('#home').addClass('active');
		$('#home a').addClass('text-danger');
		break;
	case 'About':
		$('#about').addClass('active');
		$('#about a').addClass('text-danger');
		break;
	case 'Contact':
		$('#contact').addClass('active');
		$('#contact a').addClass('text-danger');
		break;
	default:
		$('#product').addClass('active');
		$('#product a').addClass('text-danger');
		$('#a_'+menu).addClass('active');
		break;
	}
});