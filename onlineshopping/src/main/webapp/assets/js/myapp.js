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
	case 'Manage Products':
		$('#manageProducts').addClass('active');
		$('#manageProducts a').addClass('text-danger');
		break;
	case 'User Cart':
		$('#userCart').addClass('active');
		$('#userCart a').addClass('text-danger');
		break;
	default:
		$('#product').addClass('active');
		$('#product a').addClass('text-danger');
		$('#a_' + menu).addClass('active');
		break;
	}
	// for handling CSRF token
	var token = $('meta[name="_csrf"]').attr('content');
	var header = $('meta[name="_csrf_header"]').attr('content');

	if ((token != undefined && header != undefined)
			&& (token.length > 0 && header.length > 0)) {
		// set the token header for the ajax request
		$(document).ajaxSend(function(e, xhr, options) {
			xhr.setRequestHeader(header, token);
		});
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
					ajax : {
						url : jsonUrl,
						dataSrc : ''
					},
					columns : [
							{
								data : 'code',
								mRender : function(data, type, row) {
									return '<img src="'
											+ window.contextRoot
											+ '/resources/images/'
											+ data
											+ '.jpg" class="dataTableImage" alt="Images">';
								}
							},
							{
								data : 'name'
							},
							{
								data : 'brand'
							},
							{
								data : 'unitPrice',
								mRender : function(data, type, row) {
									return '&#8377; ' + data;
								}
							},
							{
								data : 'quantity',
								mRender : function(data, type, row) {
									if (data < 1) {
										return "<span style='color:red'>Out of Stock</span>";
									}
									return data;
								}
							},
							{
								data : 'id',
								bSortable : false,
								mRender : function(data, type, row) {
									var str = '';
									str += '<a href="'
											+ window.contextRoot
											+ '/show/'
											+ data
											+ '/product" class="btn btn-danger btn-sm"><span class="glyphicon glyphicon-eye-open"></span>View</a> &#160;';

									if (userRole == 'ADMIN') {
										str += '<a href="'
												+ window.contextRoot
												+ '/manage/'
												+ data
												+ '/product" class="btn btn-warning btn-sm"><span class="glyphicon glyphicon-pencil"></span>Edit</a>';

									} else {
										if (row.quantity < 1) {
											str += '<a href="javascript.void(0)" class="btn btn-success btn-sm disabled"><span class="glyphicon glyphicon-shopping-cart"></span>Add To Cart</a>';
										} else {
											str += '<a href="'
													+ window.contextRoot
													+ '/cart/add/'
													+ data
													+ '/product" class="btn btn-success btn-sm"><span class="glyphicon glyphicon-shopping-cart"></span>Add To Cart</a>';
										}
									}
									return str;
								}
							}

					]

				});
	}
	// Dismission alert after 3 second
	var $alert = $('.alert');
	if ($alert.length) {
		setTimeout(function() {
			$alert.fadeOut('slow');
		}, 3000);
	}

	// -------------

	// Data Table
	var $adminProductsTable = $('#adminProductsTable');
	// Execute the below code only where we have this table
	if ($adminProductsTable.length) {
		// console.log('Data');

		var jsonUrl = window.contextRoot + '/json/data/admin/all/products';

		$adminProductsTable
				.DataTable({
					lengthMenu : [
							[ 10, 30, 50, -1 ],
							[ '10 RECORDS', '30 RECORDS', '50 RECORDS',
									'ALL RECORDS' ] ],
					pageLength : 30,
					ajax : {
						url : jsonUrl,
						dataSrc : ''
					},
					columns : [
							{
								data : 'id'
							},
							{
								data : 'code',
								bSortable : false,
								mRender : function(data, type, row) {
									return '<img src="'
											+ window.contextRoot
											+ '/resources/images/'
											+ data
											+ '.jpg" class="dataTableImage" alt="Image">';
								}
							},
							{
								data : 'name'
							},
							{
								data : 'brand'
							},
							{
								data : 'quantity',
								mRender : function(data, type, row) {
									if (data < 1) {
										return "<span style='color:red'>Out of Stock</span>";
									}
									return data;
								}
							},
							{
								data : 'unitPrice',
								mRender : function(data, type, row) {
									return '&#8377; ' + data;
								}
							},
							{
								data : 'active',
								bSortable : false,
								mRender : function(data, type, row) {
									var str = '';

									str += '<label class="switch">';
									if (data) {
										str += '<input type="checkbox" checked="checked" value="'
												+ row.id + '">'
									} else {
										str += '<input type="checkbox" value="'
												+ row.id + '">'
									}
									str += '<div class="slider round"></div></label>';

									return str;
								}
							},
							{
								data : 'id',
								bSortable : false,
								mRender : function(data, type, row) {
									var str = '';
									str += '<a href="' + window.contextRoot
											+ '/manage/' + data
											+ '/product" class="btn btn-info">';
									str += '<span class="glyphicon glyphicon-pencil"></span>Edit</a>'

									return str;
								}
							}

					],
					initComplete : function() {
						var api = this.api();
						api
								.$('.switch input[type="checkbox"]')
								.on(
										'change',
										function() {
											var checkbox = $(this);
											var checked = checkbox
													.prop('checked');
											var dMsg = (checked) ? 'Do you want to activate the product?'
													: 'Do you want to deactive product?';

											var value = checkbox.prop('value');

											bootbox
													.confirm({
														size : 'medium',
														title : 'Product Activation & Deactivation',
														message : dMsg,
														callback : function(
																confirmed) {
															if (confirmed) {
																var activationUrl = window.contextRoot
																		+ '/manage/product/'
																		+ value
																		+ '/activation';
																$
																		.post(
																				activationUrl,
																				function(
																						data) {
																					bootbox
																							.alert({
																								size : 'medium',
																								title : 'Information',
																								message : data
																							});
																				});
															} else {
																checkbox
																		.prop(
																				'checked',
																				!checked);
															}
														}
													});
										});
					}

				});
	}

	// Validation code for category.
	var $categoryForm = $('#categoryForm');
	if ($categoryForm.length) {
		$categoryForm.validate({
			rules : {
				name : {
					required : true,
					minlength : 2
				},
				description : {
					required : true
				}
			},
			messages : {
				name : {
					required : 'Please Enter Category Name',
					minlength : 'Category Name Should not be less than 2'
				},
				description : {
					required : 'Please Add Description'
				}
			},
			errorElement : 'em',
			errorPlacement : function(error, element) {
				// add class of help-block
				error.addClass('help-block');
				// add the error element after inputing value
				error.insertAfter(element);
			}
		});
	}
	// -----------------------------
	// Validation User.
	var $loginForm = $('#loginForm');
	if ($loginForm.length) {
		$loginForm.validate({
			rules : {
				username : {
					required : true,
					email : true
				},
				password : {
					required : true
				}
			},
			messages : {
				username : {
					required : 'Please Enter Email Address',
					email : 'Please Enter Valid Email Address'
				},
				description : {
					required : 'Please Enter Password'
				}
			},
			errorElement : 'em',
			errorPlacement : function(error, element) {
				// add class of help-block
				error.addClass('help-block');
				// add the error element after inputing value
				error.insertAfter(element);
			}
		});
	}
	//CLICK Event of Refresh card button -----------------------------
	$('button[name="refreshCart"]').click(function(){
		
		var cartLineId = $(this).attr('value');
		var countElement = $('#cart_'+cartLineId);
		
		var originalCount = countElement.attr('value');
		var currentCount = countElement.val();
		//Work only when the count has changed
		if(currentCount !== originalCount){
			if(currentCount < 1 || currentCount > 3){
				//setting same original count.
				countElement.val(originalCount);
				bootbox.alert({
					size: 'medium',
					title: 'Error',
					message: 'Product count should be minimum 1 and maximum 3'
				});
			}else {
				var updateUrl = window.contextRoot+'/cart/'+cartLineId+'/update?count='+currentCount;
				//forwarded to controller
				window.location.href = updateUrl;
			}
		}
	});
	
	
	

});