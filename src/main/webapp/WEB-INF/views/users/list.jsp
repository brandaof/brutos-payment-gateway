<%@ page session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>

	<head>
		<jsp:include page="../fragments/header.jsp"/>
	</head>

<body>

	<jsp:include page="../fragments/navigation.jsp"/>
	
    <div class="container">
    
		<c:if test="${not empty msg}">
		    <div class="alert alert-${css} alert-dismissible" role="alert">
			<button type="button" class="close" data-dismiss="alert"
                                aria-label="Close">
				<span aria-hidden="true">�</span>
			</button>
			<strong>${msg}</strong>
		    </div>
		</c:if>

		<h1>All Users</h1>

		<table class="table table-striped">
			<thead>
				<tr>
					<th>#ID</th>
					<th>First Name</th>
					<th>Last Name</th>
				</tr>
			</thead>

			<c:forEach var="user" items="${users}">
			    <tr>
				<td>
					${user.id}
				</td>
				<td>${user.firstname}</td>
				<td>${user.lastname}</td>
				<td>
				  <button class="btn btn-info"
                                          onclick="get('/users/${user.id}')">Query</button>
				  <button class="btn btn-primary"
                                          onclick="get('/users/${user.id}/update')">Update</button>
				  <button class="btn btn-danger"
                                          onclick="post('/users/${user.id}/delete', true)">Delete</button>
                                </td>
			    </tr>
			</c:forEach>
		</table>
		    
	</div>
	
	<jsp:include page="../fragments/footer.jsp"/>

</body>

</html>