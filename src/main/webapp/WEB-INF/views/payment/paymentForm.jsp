<%@page session="false" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<html>

	<head>
		<jsp:include page="../fragments/header.jsp"/>
	</head>

<body>

	<jsp:include page="../fragments/navigation.jsp"/>
	
    <div class="container">
    	<section>
	    	<div class="col-lg-8 col-lg-offset-2">
	    		<div class="page-header">
					<h1>Register Payment</h1>
                </div>
	    	</div>
	    	
			<form class="form-horizontal" 
						method="post" 
						action="${pageContext.request.contextPath}/payment">
				
						  <div class="form-group row">
							<label class="col-sm-2 control-label">Value</label>
							<div class="col-sm-10">
								<input name="user.firstname" type="text" value="${payment.value}" 
									class="form-control ${!empty errors.causes['payment.value'] ? 'is-invalid' : ''}" 
									placeholder="First Name">
								<c:forEach var="ex" items="${errors.causes['payment.value']}">
									<div class="text-danger"><small>${ex.message}</small></div>
								</c:forEach>
							</div>
						  </div>

						  <div class="form-group row">
							<label class="col-sm-2 control-label">Currency</label>
							<div class="col-sm-5">
								<select name="payment.currency" class="form-control ${!empty errors.causes['payment.currency'] ? 'is-invalid' : ''}">
									<option value="">--- Select ---</option>
				                   	<c:forEach var="currency" items="${currencyList}">
										<option value="${currency.currencyCode}" ${payment.currency == currency.currencyCode? 'selected' : ''}>${currency.displayName}</option>
								  	</c:forEach>
								</select>
								<c:forEach var="ex" items="${errors.causes['payment.currency']}">
									<div class="text-danger"><small>${ex.message}</small></div>
								</c:forEach>
							</div>
							<div class="col-sm-5"></div>
						  </div>
				
						  <div class="form-group row">
							<label class="col-sm-2 control-label">Payment type</label>
							<div class="col-sm-5">
								<select id="payment" name="payment.paymentType" class="form-control ${!empty errors.causes['payment.paymentType'] ? 'is-invalid' : ''}">
									<option value="">--- Select ---</option>
				                   	<c:forEach var="paymentType" items="${paymentTypeList}">
										<option value="${paymentType.id}" ${payment.paymentType == paymentType.id? 'selected' : ''}>${paymentType.name}</option>
								  	</c:forEach>
								</select>
								<c:forEach var="ex" items="${errors.causes['payment.currency']}">
									<div class="text-danger"><small>${ex.message}</small></div>
								</c:forEach>
							</div>
							<div class="col-sm-5"></div>
						  </div>
						  
						<div id="paymentContent">
						  <c:if test="${!empty payment.paymentType}">
						  	<jsp:include page="./gateway/${payment.paymentType}/paymentForm.jsp"/>
						  </c:if>
						</div>
						  
						<div class="form-group row">
						  <div class="col-sm-offset-2 col-sm-12">
						     <button type="submit" class="btn-lg btn-primary float-right">Register
				                             </button>
						  </div>
						</div>
				
			</form>	    	
    	</section>
    	
    </div>

	<jsp:include page="../fragments/footer.jsp"/>

</body>

</html>