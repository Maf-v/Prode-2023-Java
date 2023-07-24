<%@page import="ar.com.deserialize.Match"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous"> 
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/prueba.css" /> 
	<title>PRUEBA</title>
</head>
	<body>
		<h1>PRUEBA</h1>
		
		<h2><%=request.getContextPath()%>/../CreateUser</h2>
		
		<% 
			Integer weight = (Integer)request.getAttribute("weight");
		%>
		
		<h2><%=weight%></h2>
		
		
		<div class="justify-content-center">
		<%
			for(Match match : (Match[])request.getAttribute("matches")) {
		
		%>
			<form action="<%=request.getContextPath()%>/NewPronostico" method="post" class="form">
				<div class="d-flex justify-content-between w-60 mt-3 mb-3 mx-auto"">
					<input type="hidden" name="matchId" id="matchId" value="<%=match.getID() %>">
					<div class="d-flex bd-highlight w-50">
					  <img src="<%=match.getHomeTeam().getCrest() %>" class="teamLogo col-2" alt="...">
					  <div class="p-2 flex-fill bd-highlight col-4 text-center align-self-center"><%=match.getHomeTeam().getName() %></div>
					  <input value="0" type="number" name="homeGoals" id="homeGoals" class="inputForm col-2 h-75 align-self-center" />
					</div>
					<div class="d-flex bd-highlight w-50">
					  <input value="0" type="number" name="awayGoals" id="awayGoals" class="inputForm col-2 h-75 align-self-center" />
					  <div class="p-2 flex-fill bd-highlight col-4 text-center align-self-center"><%=match.getAwayTeam().getName() %></div>
					  <img src="<%=match.getAwayTeam().getCrest() %>" class="teamLogo col-2" alt="...">
					</div>
				</div>
			</form>
		<%
			}
		%>
		
			<div class="row justify-content-center">
		        <button type="button" class="btn-success col-7 mt-4" 
					data-bs-toggle="modal" 
					data-bs-target="#unirseModal" 
					onclick="executeSubmit()">
					Guardar
				</button>
			</div>
												
		</div>
		
	</body>
	
	  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>

	  <script>
			function executeSubmit() {
				let forms = document.querySelectorAll(".form");
				forms.forEach((form) => form.submit());
			}
	  </script>
</html>