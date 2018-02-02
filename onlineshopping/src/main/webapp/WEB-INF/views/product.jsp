<div class="container">
	<div class="row">
		<%@include file="./shared/sidebar.jsp"%>
		<div class="col-lg-9">
			<!-- BreadCrumb Component -->
			<br>
			<div class="row">
				<div class="col-lg-12">
					<c:if test="${userClickAllProduct == true}">
						<ol class="breadcrumb">
							<li class="breadcrumb-item"><a href="${contextRoot}/home">Home</a></li>
							<li class="breadcrumb-item active">All Products</li>
						</ol>
					</c:if>
					<c:if test="${userClickCategoryProduct == true}">
						<ol class="breadcrumb">
							<li class="breadcrumb-item"><a href="${contextRoot}/home">Home</a></li>
							<li class="breadcrumb-item active">Cagetgory</li>
							<li class="breadcrumb-item active">${category.name}</li>
						</ol>
					</c:if>
				</div>
			</div>
		</div>
	</div>
</div>