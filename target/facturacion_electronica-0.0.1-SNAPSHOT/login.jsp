<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!doctype html>
<html class="no-js" lang="zxx">

<head>
<meta charset="utf-8">
<meta http-equiv="x-ua-compatible" content="ie=edge">
  	<title>Login 04</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

	<link href="https://fonts.googleapis.com/css?family=Lato:300,400,700&display=swap" rel="stylesheet">

	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
	
	<link rel="stylesheet" href="<%=request.getContextPath()+"/assets/css/style.css"%>">

	</head>
	<body>
	<section class="ftco-section">
		<div class="container">
			<div class="row justify-content-center">
				<div class="col-md-6 text-center mb-5">
					<h1 class="heading-section"><b>FactuPyme</b></h1>
				</div>
			</div>
			<div class="row justify-content-center">
				<div class="col-md-12 col-lg-10">
					<div class="wrap d-md-flex">
						<div class="img" style="background-image: url(<%=request.getContextPath()%>/assets/images/is.jpg);">
			      </div>
						<div class="login-wrap p-4 p-md-5">
			      	<div class="d-flex">
			      		<div class="w-100">
			      			<h3 class="mb-4">Iniciar sesi�n</h3>
			      		</div>
			      	</div>
							<form action="#" class="signin-form">
			      		<div class="form-group mb-3">
			      			<label class="label" for="name">Correo electr�nico</label>
			      			<input type="text" class="form-control" placeholder="Correo electr�nico" required>
			      		</div>
		            <div class="form-group mb-3">
		            	<label class="label" for="password">Contrase�a</label>
		              <input type="password" class="form-control" placeholder="Contrase�a" required>
		            </div>
		            <div class="form-group">
		            	<button type="submit" class="form-control btn btn-primary rounded submit px-3">Ingresar</button>
		            </div>
		          </form>
		          <p class="text-center">No est� registrado? <a data-toggle="tab" href="#registrarse">Reg�strese</a></p>
		        </div>
		      </div>
				</div>
			</div>
		</div>
	</section>

	<script src="<%=request.getContextPath()%>/assets/js/jquery.min.js"></script>
  <script src="<%=request.getContextPath()%>/assets/js/popper.js"></script>
  <script src="<%=request.getContextPath()%>/assets/js/bootstrap.min.js"></script>
  <script src="<%=request.getContextPath()%>/assets/js/main.js"></script>

	</body>
</html>
