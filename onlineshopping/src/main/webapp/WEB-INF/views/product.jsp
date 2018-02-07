<div class="container">
	<div class="row">
		<%@include file="./shared/sidebar.jsp"%>
		<div class="col-lg-9">
			<!-- BreadCrumb Component -->
			<br>
			<div class="row">
				<div class="col-lg-12">

					<c:if test="${userClickAllProduct == true}">

						<script type="text/javascript">
							window.categoryId = '';
						</script>

						<ol class="breadcrumb">
							<li class="breadcrumb-item"><a href="${contextRoot}/home">Home</a></li>
							<li class="breadcrumb-item active">All Products</li>
						</ol>
					</c:if>
					<c:if test="${userClickCategoryProduct == true}">

						<script type="text/javascript">
							window.categoryId = '${category.id}';
						</script>

						<ol class="breadcrumb">
							<li class="breadcrumb-item"><a href="${contextRoot}/home">Home</a></li>
							<li class="breadcrumb-item active">Cagetgory</li>
							<li class="breadcrumb-item active">${category.name}</li>
						</ol>
					</c:if>
				</div>
			</div>

			<div class="row">
				<table id="listAllProducts"
					class="table table-striped table-bordered" cellspacing="0"
					width="100%">
					<thead>
						<tr>
							<th></th>
							<th>Name</th>
							<th>Brand</th>
							<th>Price</th>
							<th>Qty. Available</th>
							<th></th>
						</tr>
					</thead>
					<tfoot>
						<tr>
							<th></th>
							<th>Name</th>
							<th>Brand</th>
							<th>Price</th>
							<th>Qty. Available</th>
							<th></th>
						</tr>
					</tfoot>
				</table>
			</div>
			<br>
		</div>
	</div>
</div>