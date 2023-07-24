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
<body>
        <!-- Sidebar -->
        <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

            <!-- Sidebar - Brand -->
            <a class="sidebar-brand d-flex align-items-center justify-content-center" href="index.jsp">
                <img src="<%=request.getContextPath()%>/img/ball-icon.png" class="iconLogo"></img>
                <div class="sidebar-brand-text mx-3">PRODE 2023</div>
            </a>

            <!-- Divider -->
            <hr class="sidebar-divider my-0">

            <!-- Nav Item - Dashboard -->
            <li class="nav-item">
                <a class="nav-link" href="index.jsp">
                    <span>Inicio</span></a>
            </li>

            <!-- Divider -->
            <hr class="sidebar-divider">

            <!-- Heading -->
            <div class="sidebar-heading">
                Personal
            </div>

            <!-- Nav Item - Charts -->
            <li class="nav-item">
                <a class="nav-link" href="<%=request.getContextPath()%>/MisTorneos">
                    <i class="fas fa-fw fa-chart-area"></i>
                    <span>Mis Torneos</span></a>
            </li>

            <!-- Nav Item - Tables -->
            <li class="nav-item">
                <a class="nav-link" href="<%=request.getContextPath()%>/MisPronosticos">
                    <i class="fas fa-fw fa-folder"></i>
                    <span>Mis Pronosticos</span></a>
            </li>
            
            <li class="nav-item">
                <a class="nav-link" href="<%=request.getContextPath()%>/CrearPronosticos">
                    <i class="fas fa-fw fa-table"></i>
                    <span>Nuevo Pronostico</span></a>
            </li>

            <!-- Divider -->
            <hr class="sidebar-divider d-none d-md-block">
            
                        <!-- Heading -->
            <div class="sidebar-heading">
                General
            </div>
            
            <li class="nav-item">
                <a class="nav-link" href="<%=request.getContextPath()%>/ListaTorneos">
                    <i class="fas fa-fw fa-chart-area"></i>
                    <span>Torneo de Amigos</span></a>
            </li>
            
            <li class="nav-item">
                <a class="nav-link" href="newTorneo.jsp">
                    <i class="fas fa-fw fa-chart-area"></i>
                    <span>Crear Torneo</span></a>
            </li>

            <!-- Sidebar Toggler (Sidebar) -->
            <div class="text-center d-none d-md-inline mt-rem-7">
                <button class="rounded-circle border-0" id="sidebarToggle"></button>
            </div>
            
        </ul>
</body>
</html>