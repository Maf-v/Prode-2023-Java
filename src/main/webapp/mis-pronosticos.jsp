<%@page import="ar.com.deserialize.Partido"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>PRODE 2023</title>

    <!-- Custom fonts for this template-->
    <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="css/sb-admin-2.min.css" rel="stylesheet">
    <link href="css/styles.css" rel="stylesheet">

</head>
<body id="page-top">

    <!-- Page Wrapper -->
    <div id="wrapper">

		<jsp:include page="menu.jsp"/>
		
		<!-- Content Wrapper -->
        <div id="content-wrapper" class="d-flex flex-column">

        	<!-- Main Content -->
        	<div id="content">
        	
        		<jsp:include page="top-bar.jsp"/>
        	
        		<!-- Begin Page Content -->
                <div class="container-fluid">         
				
				<%
				List<Partido> partidos = (List<Partido>)request.getAttribute("partidos");
				Map<Long,Integer> mapPartidoPuntos = (Map<Long,Integer>)request.getAttribute("mapPartidoPuntos");
				
				if(partidos.size() == 0) {
				%>
					<h2 class="text-center mt-5">Aun no tienes pronosticos guardados</h2>
				<% 
				} else {
				%>	
					<h4 class="m-3">Mis Pronosticos: </h4>
				<%
					for(Partido partido : partidos) {
				%>
					<div class="d-flex justify-content-between w-52 mt-3 mb-3 mx-auto">
						<div class="d-flex bd-highlight col-5">
							<img src="<%=partido.getHomeTeamCrest() %>" class="equipoEscudo" alt="...">
							<div class="p-2 flex-fill bd-highlight text-center align-self-center"><%=partido.getHomeTeamName() %></div>
							<input value="<%=partido.getScoreHome() %>" type="number" name="homeGoals" id="homeGoals" class="inputForm only-read col-2 h-75 align-self-center" readonly/>
						</div>
						<div class="d-flex bd-highlight col-5">
							<input value="<%=partido.getScoreAway() %>" type="number" name="awayGoals" id="awayGoals" class="inputForm only-read col-2 h-75 align-self-center" readonly/>
							<div class="p-2 flex-fill bd-highlight text-center align-self-center"><%=partido.getAwayTeamName() %></div>
							<img src="<%=partido.getAwayTeamCrest() %>" class="equipoEscudo" alt="...">
						</div>
					<%
						if( mapPartidoPuntos.get(partido.getId()) < 0 ) {
					%>
						<div class="col-2 d-flex">
							<p class="points"> - </p>
						</div>
					<% } else { %>
						<div class="col-2 d-flex">
							<p class="points"> <%=mapPartidoPuntos.get(partido.getId()) %> </p>
						</div>
					<% } %>
					</div>
				
				<% 
					}
				  }
				%>
                </div>
                <!-- /.container-fluid -->
                
        	</div>
            <!-- End of Main Content -->
            
		</div>

	</div>
	
	    <!-- Bootstrap core JavaScript-->
    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="vendor/jquery-easing/jquery.easing.min.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="js/sb-admin-2.min.js"></script>
    
</body>
	  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>

</html>