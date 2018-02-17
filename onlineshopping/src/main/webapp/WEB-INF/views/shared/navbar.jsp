<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>

<nav class="navbar navbar-expand-sm navbar-light bg-light">
	<a class="navbar-brand" href="${contextRoot}/home">Online Shopping</a>
	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#navbarColor03" aria-controls="navbarColor03"
		aria-expanded="false" aria-label="Toggle navigation" style="">
		<span class="navbar-toggler-icon"></span>
	</button>
	<div class="collapse navbar-collapse" id="navbarColor03">
		<ul class="navbar-nav ml-auto">
			<li class="nav-item" id="home"><a class="nav-link"
				href="${contextRoot}/home">Home</a></li>
			<li class="nav-item" id="product"><a class="nav-link"
				href="${contextRoot}/show/all/products">Products</a></li>
			<li class="nav-item" id="about"><a class="nav-link"
				href="${contextRoot}/about">About</a></li>
			<li class="nav-item" id="contact"><a class="nav-link"
				href="${contextRoot}/contact">Contact</a></li>
			<security:authorize access="hasAuthority('ADMIN')">
				<li class="nav-item" id="manageProducts"><a class="nav-link"
					href="${contextRoot}/manage/products">Manage Products</a></li>
			</security:authorize>
		</ul>

		<ul class="nav navbar-nav navbar-right">
			<security:authorize access="isAnonymous()">
				<li id="register"><a class="nav-link"
					href="${contextRoot}/register">Signup</a></li>
				<li id="login"><a class="nav-link" href="${contextRoot}/login">Login</a></li>
			</security:authorize>
		</ul>
		<security:authorize access="isAuthenticated()">
			<ul class="nav navbar-nav navbar-right">

				<li class="nav dropdown" id="userCart"><a class="nav-link dropdown-toggle"
					data-toggle="dropdown" href="#" role="button" aria-haspopup="true"
					aria-expanded="false">${userModel.fullName}</a>
					<div class="dropdown-menu" x-placement="bottom-start"
						style="position: absolute; transform: translate3d(0px, 38px, 0px); top: 0px; left: -68px; will-change: transform;">
						<security:authorize access="hasAuthority('USER')">
							<a class="dropdown-item" href="${contextRoot}/cart/show"> <span
								class="glyphicon glyphicon-shopping-cart"></span> <span
								class="badge">${userModel.cart.cartLines}</span>- &#8377;
								${userModel.cart.grandTotal}
							</a>
						</security:authorize>
						<div class="dropdown-divider"></div>
						<a class="dropdown-item" href="${contextRoot}/perform-logout">Logout</a>
					</div></li>
			</ul>
		</security:authorize>
	</div>
</nav>

<script type="text/javascript">

	window.userRole = '${userModel.role}';

</script>







