<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="es">

<head>
<meta charset="utf-8">
<meta content="IE=edge" http-equiv="X-UA-Compatible">
<meta content="width=device-width, initial-scale=1, shrink-to-fit=no"
	name="viewport">

<title>Productos</title>
<!-- Custom fonts for this template-->
<link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet"
	type="text/css">
<link
	href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
	rel="stylesheet">
<!-- Custom styles for this template-->
<link href="css/sb-admin-2.min.css" rel="stylesheet">
<link href="css/ver-clientes.css" rel="stylesheet" type="text/css">

</head>

<body id="page-top">
	<!-- Page Wrapper -->
	<div id="wrapper">

		<!-- Sidebar -->
		<jsp:include page="navMenu.jsp" />
		<!-- End of Sidebar -->


		<!-- Content Wrapper -->
		<div class="d-flex flex-column" id="content-wrapper">
			<!-- Main Content -->
			<div id="content">
				<!-- Topbar -->
				<nav
					class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">
					<!-- Sidebar Toggle (Topbar) -->
					<button class="btn btn-link d-md-none rounded-circle mr-3"
						id="sidebarToggleTop">
						<i class="fa fa-bars"> </i>
					</button>
					<!-- Topbar Search -->
					<form
						class="d-none d-sm-inline-block form-inline mr-auto ml-md-3 my-2 my-md-0 mw-100 navbar-search">
						<div class="input-group">
							<input aria-describedby="basic-addon2" aria-label="Search"
								class="form-control bg-light border-0 small"
								placeholder="Buscar..." type="text">
							<div class="input-group-append">
								<button class="btn btn-primary" type="button">
									<i class="fas fa-search fa-sm"> </i>
								</button>
							</div>
							</input>
						</div>
					</form>
					<!-- Topbar Navbar -->
					<ul class="navbar-nav ml-auto">
						<!-- Nav Item - Search Dropdown (Visible Only XS) -->
						<li class="nav-item dropdown no-arrow d-sm-none"><a
							aria-expanded="false" aria-haspopup="true"
							class="nav-link dropdown-toggle" data-toggle="dropdown" href="#"
							id="searchDropdown" role="button"> <i
								class="fas fa-search fa-fw"> </i>
						</a> <!-- Dropdown - Messages -->
							<div aria-labelledby="searchDropdown"
								class="dropdown-menu dropdown-menu-right p-3 shadow animated--grow-in">
								<form class="form-inline mr-auto w-100 navbar-search">
									<div class="input-group">
										<input aria-describedby="basic-addon2" aria-label="Search"
											class="form-control bg-light border-0 small"
											placeholder="Search for..." type="text">
										<div class="input-group-append">
											<button class="btn btn-primary" type="button">
												<i class="fas fa-search fa-sm"> </i>
											</button>
										</div>
										</input>
									</div>
								</form>
							</div></li>
						<!-- Nav Item - User Information -->
						<li class="nav-item dropdown no-arrow"><a
							aria-expanded="false" aria-haspopup="true"
							class="nav-link dropdown-toggle" data-toggle="dropdown" href="#"
							id="userDropdown" role="button"> <span
								class="mr-2 d-none d-lg-inline text-gray-600 small">
									Usuario </span> <img class="img-profile rounded-circle"
								src="img/undraw_profile.svg"> </img>
						</a> <!-- Dropdown - User Information -->
							<div aria-labelledby="userDropdown"
								class="dropdown-menu dropdown-menu-right shadow animated--grow-in">
								<a class="dropdown-item" data-target="#logoutModal"
									data-toggle="modal" href="#"> <i
									class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400">
								</i> Cerrar sesión
								</a>
							</div></li>
					</ul>
				</nav>
				<!-- End of Topbar -->
				<!-- Begin Page Content -->
				<div class="container-fluid">

					<!-- Page Heading -->
					<h1 class="title_client">Agregar producto</h1>
					<!-- DataTales Example -->
					<div class="card shadow mb-4">
						<div class="card-body">
							<form action="<%=request.getContextPath()%>/inicio/producto/agregar" method="post">
								<div class="user-details">
									<div class="input-box">
										<span class="details"> ID producto </span> 
										<input type="number"  name="codigo"  required>
									</div>
									<br>
									<div class="input-box">
										<span class="details"> Nombre </span> 
										<input name="nombre" type="text" required>
									</div>
									<br>
									<div class="input-box">
										<span class="details"> Descripción </span> 
										<input name="descripcion" type="text" required>
									</div>
									<br>
									<div class="input-box">
										<span class="details"> Unidad de medida </span>
										 <input name="unidad_medida" type="text" required>
									</div>
									<br>
									<div class="input-box">
										<span class="details"> Valor unitario </span>
										 <input name="valor_unitario" type="number" mrequired>
									</div>
									<br>
									<div class="input-box">
										<span class="details"> IVA </span> 
										<input name="iva" type="number"  required>
									</div>
									<br>
									<div class="input-box">
										<span class="details"> % Descuento </span> <input
											name="porcentaje_descuento" type="number"  required>
									</div>
									<br>
									<div class="input-box">
										<span class="details"> Estado </span> 
										<input name="estado" type="number"  required>
									</div>
								</div>
								<div class="button">
									<button class="button_style">Guardar</button>
								</div>

								<div class="button2">
									<button class="button_style" href="<%=request.getContextPath()%>/inicio/producto/ver" >Cancelar</button>
								</div>
							</form>
						</div>
					</div>

				</div>
				<!-- /.container-fluid -->


				<!-- Scroll to Top Button-->
				<a class="scroll-to-top rounded" href="#page-top"> <i
					class="fas fa-angle-up"> </i>
				</a>
				<!-- Logout Modal-->
				<div aria-hidden="true" aria-labelledby="exampleModalLabel"
					class="modal fade" id="logoutModal" role="dialog" tabindex="-1">
					<div class="modal-dialog" role="document">
						<div class="modal-content">
							<div class="modal-header">
								<h5 class="modal-title" id="exampleModalLabel">¿Cerrar
									sesión?</h5>
								<button aria-label="Close" class="close" data-dismiss="modal"
									type="button">
									<span aria-hidden="true"> × </span>
								</button>
							</div>
							<div class="modal-body">Seleccione "Salir" si está listo
								para cerrar su sesión.</div>
							<div class="modal-footer">
								<button class="btn btn-secondary" data-dismiss="modal"
									type="button">Cancelar</button>
								<a class="btn btn-primary" href="login.html"> Salir </a>
							</div>
						</div>
					</div>
				</div>
				<!-- Bootstrap core JavaScript-->
				<script src="vendor/jquery/jquery.min.js">
					
				</script>
				<script src="vendor/bootstrap/js/bootstrap.bundle.min.js">
					
				</script>
				<!-- Core plugin JavaScript-->
				<script src="vendor/jquery-easing/jquery.easing.min.js">
					
				</script>
				<!-- Custom scripts for all pages-->
				<script src="js/sb-admin-2.min.js">
					
				</script>
</body>
</html>