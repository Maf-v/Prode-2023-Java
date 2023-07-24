<%@page import="ar.com.deserialize.Torneo"%>
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

					                    <!-- DataTales Example -->
                    <div class="card shadow mb-4">
                        <div class="card-header py-3">
                            <h6 class="m-0 font-weight-bold text-primary">Mis Torneos</h6>
                        </div>
                        <div class="card-body">
                            <div class="table-responsive">
                                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                    <thead>
                                        <tr>
                                            <th class="col-1">Id</th>
                                            <th class="col-7">Nombre</th>
                                            <th class="col-3">Acciones</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    
                                    	<%
                                    		List<Torneo> torneos = (List<Torneo>)request.getAttribute("torneos");
                                    		
                                    		for (Torneo torneo : torneos) {
                                    	%>
                                    
                                        <tr>
                                            <td class="col-1"><%=torneo.getId() %></td>
                                            <td class="col-7">
                                            
                                            <form method="get" action="<%=request.getContextPath()%>/Torneo" id="form">
											  <input type="hidden" name="idTorneo" id="idTorneo" value="" /> 
                                            	<a onclick="setTorneoId(<%=torneo.getId()%>)">
                                            		<%=torneo.getNombre()%>
                                            	</a>											  
											</form>
                                            </td>
                                            <td class="col-3"></td>
                                        </tr>
                                        <% } %>
                                        
                                     </tbody>
                                </table>
                            </div> 
                         </div>
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

    <!-- Bootstrap core JavaScript-->
    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="vendor/jquery-easing/jquery.easing.min.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="js/sb-admin-2.min.js"></script>
	
	
</body>
	  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>

	  <script>
			function setTorneoId(id) {
				document.getElementById('idTorneo').value=id;
				document.getElementById('form').submit()
			}
	  </script>
</html>