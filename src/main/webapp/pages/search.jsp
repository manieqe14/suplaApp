<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Results</title>
</head>
<body>
	<div id="main-container">
		<div id="second-container">
			<div class="container">
				<h1>Search results:</h1>
				<table>
					<thead>
						<tr>
							<th>id</th>
							<th>title</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${booksFounded}" var="book">
							<tr>
								<td>${book.book_id}</td>
								<td>${book.title}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			
		</div>
	</div>
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Lato:wght@100;300;400;700&display=swap" rel="stylesheet">
<script>
(function(){
	whenDomReady();
})();
</script>

</body>
</html>