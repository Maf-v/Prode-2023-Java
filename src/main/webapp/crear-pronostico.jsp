<%@page import="ar.com.deserialize.Partido"%>
<%@page import="java.util.List"%>
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
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/prueba.css" /> 

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
                
                	<form class="form-inline align-content-center" method="get" action="<%=request.getContextPath()%>/CreatePronosticos">
					  <label class="my-1 mr-2" for="inlineFormCustomSelectPref">Seleccionar etapa de la Copa:</label>
					  <select class="custom-select my-1 mr-sm-2" name="etapa" id="etapa">
					    <option selected>Etapa de la Copa..</option>
					    <option value="prueba">Prueba</option>
					    <option value="LAST_16">16vos de final</option>
					    <option value="LAST_8" class="disabled" disabled>8vos de final</option>
					    <option value="LAST_4" class="disabled" disabled>4tos de final</option>
					    <option value="semifinal" class="disabled" disabled>Semifinal</option>
					    <option value="final" class="disabled" disabled>Final</option>
					  </select>
					  <button type="submit" class="btn btn-success col-2">
						Seleccionar
					  </button>
					</form>
					
					<%
					  	String userId = (String)session.getAttribute("uid");
	            		
	                    if (userId == null) {
					%>
                	
                	<p class="error-color fs-3 mt-3">Debes registrarte o ingresar para poder completar tus pronosticos</p>
                	
                	<% } else { %>
                	
                	<%
							List<Partido> partidos = (List<Partido>)request.getAttribute("partidos");
							if(partidos != null && !partidos.isEmpty()) {
                	%>
					<div class="justify-content-center">
						<form action="<%=request.getContextPath()%>/NewPronosticos" method="post" class="form">
						<%
								for(Partido partido : partidos) {									
						%>
								<div class="d-flex justify-content-between w-60 mt-3 mb-3 mx-auto"">
									<input type="hidden" name="matchId<%=partido.getId()%>" id="matchId" value="<%=partido.getId() %>">
									<div class="d-flex bd-highlight w-50">
									  <img src="<%=partido.getHomeTeamCrest() %>" class="teamLogo" alt="...">
									  <div class="p-2 flex-fill bd-highlight text-center align-self-center"><%=partido.getHomeTeamName() %></div>
									  <input value="0" type="number" name="homeGoals<%=partido.getId()%>" id="homeGoals" class="inputForm col-2 h-75 align-self-center" />
									</div>
									<div class="d-flex bd-highlight w-50">
									  <input value="0" type="number" name="awayGoals<%=partido.getId()%>" id="awayGoals" class="inputForm col-2 h-75 align-self-center" />
									  <div class="p-2 flex-fill bd-highlight text-center align-self-center"><%=partido.getAwayTeamName() %></div>
									  <img src="<%=partido.getAwayTeamCrest() %>" class="teamLogo" alt="...">
									</div>
								</div>
						<%
								}
						%>
							<div class="row justify-content-center">
						        <button type="submit" class="btn btn-success col-7 mt-4 mb-5">
									Guardar
								</button>
							</div>
						</form>
					<%
							}
                		}
					%>
						
																
						</div>

                </div>
                <!-- /.container-fluid -->
                
        	</div>
            <!-- End of Main Content -->
            
		</div>

	</div>
		    <!-- Logout Modal-->
    <div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
        aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
                    <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">×</span>
                    </button>
                </div>
                <div class="modal-body">Select "Logout" below if you are ready to end your current session.</div>
                <div class="modal-footer">
                    <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
                    <a class="btn btn-primary" href="<%=request.getContextPath()%>/Logout">Logout</a>
                </div>
            </div>
        </div>
    </div>
	
        <!-- Bootstrap core JavaScript -->
        <script src="vendor/jquery/jquery.min.js"></script>
        <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

        <!-- Custom scripts for all pages -->
        <script src="js/sb-admin-2.min.js"></script>
    
</body>
</html>