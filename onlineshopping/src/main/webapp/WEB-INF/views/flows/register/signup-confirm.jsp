<%@taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@include file="../shared/flows-header.jsp"%>
<br>
<div class="container">
	<div class="row">
		<div class="col-sm-6">
			<div class="panel panel-primary">
				<div class="panel-heading">
					<h4>Personal Details</h4>
				</div>

				<div class="panel-body">
					<div class="text-center">
						<h3>
							Name : <strong>${registerModel.user.firstName}
								${registerModel.user.lastName}</strong>
						</h3>
						<h4>
							Email : <strong>${registerModel.user.email}</strong>
						</h4>
						<h4>
							Contact : <strong>${registerModel.user.contactNumber}</strong>
						</h4>
						<h4>
							Role : <strong>${registerModel.user.role}</strong>
						</h4>
						<p>
							<a href="${flowExecutionUrl}&_eventId_personal"
								class="btn btn-primary">Edit</a>
						</p>
					</div>
				</div>
			</div>
		</div>

		<div class="col-sm-6">
			<div class="panel panel-primary">
				<div class="panel-heading">
					<h4>Billing Address</h4>
				</div>

				<div class="panel-body">
					<div class="text-center">
						<h3>
							Address Line One : <strong>
								${registerModel.billing.addressLineOne}</strong>
						</h3>
						<h4>
							Address Line Two : <strong>${registerModel.billing.addressLineTwo}</strong>
						</h4>
						<h4>
							City : <strong>${registerModel.billing.city}-
								${registerModel.billing.postalCode}</strong>
						</h4>
						<h4>
							State : <strong>${registerModel.billing.state}</strong>
						</h4>
						<h4>
							Country : <strong>${registerModel.billing.country}</strong>
						</h4>
						<p>
							<a href="${flowExecutionUrl}&_eventId_billing"
								class="btn btn-primary">Edit</a>
						</p>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="row">
		<div class="col-sm-4 col-sm-offset-4">
			<div class="text-center">
				<a href="${flowExecutionUrl}&_eventId_submit"
					class="btn btn-lg btn-primary">Confirm</a>
			</div>
		</div>
	</div>

</div>
<br>
<%@include file="../shared/flows-footer.jsp"%>
