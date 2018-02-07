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
		$('#a_' + menu).addClass('active');
		break;
	}

	// Code for jquery dataTable
	// create a dataset

	var $table = $('#listAllProducts');
	// Execute the below code only where we have this table
	if ($table.length) {
		// console.log('Data');

		var jsonUrl = '';
		if (window.categoryId == '') {
			jsonUrl = window.contextRoot + '/json/data/all/products';
		} else {
			jsonUrl = window.contextRoot + '/json/data/category/'
					+ window.categoryId + '/products';
		}

		$table
				.DataTable({
					lengthMenu : [
							[ 3, 5, 10, -1 ],
							[ '3 RECORDS', '5 RECORDS', '10 RECORDS',
									'ALL RECORDS' ] ],
					pageLength : 5,
					ajax: {
						url: jsonUrl,
						dataSrc: ''
					},
					columns: [
						{
							data: 'code',
							mRender: function(data, type, row){
								return '<img src="'+window.contextRoot+'/resources/images/'+data+'.jpg" class="dataTableImage" alt="Images">';
							}
						},
						{
							data: 'name'
						},
						{
							data: 'brand'
						},
						{
							data: 'unitPrice',
							mRender: function(data, type, row){
								return '&#8377; '+data;
							}
						},
						{
							data: 'quantity',
							mRender: function(data,type,row){
								if(data<1){
									return "<span style='color:red'>Out of Stock</span>";
								}
								return data;
							}
						},
						{
							data: 'id',
							bSortable: false,
							mRender: function(data, type, row){
								var str = '';
								str += '<a href="'+window.contextRoot+'/show/'+data+'/product" class="btn btn-warning btn-sm"><span class="glyphicon glyphicon-eye-open"></span>View</a> &#160;';
								
								if(row.quantity < 1){
									str += '<a href="javascript.void(0)" class="btn btn-success btn-sm disabled"><span class="glyphicon glyphicon-shopping-cart"></span>Add To Cart</a>';
								}else {
								str += '<a href="'+window.contextRoot+'/cart/add/'+data+'/product" class="btn btn-success btn-sm"><span class="glyphicon glyphicon-shopping-cart"></span>Add To Cart</a>';
								}
								return str;
							}
						}
						
					]
					
				});
	}
	
	
	
	
	
	
	
	
	
	
	
	

});