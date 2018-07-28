<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<!-- This snippet uses Font Awesome 5 Free as a dependency. You can download it at fontawesome.io! -->

<link rel="stylesheet" href="/newproject/indexStyling.css" type="text/css">
<head>
<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>

</head>
<body>
<%
String loginstatus = request.getParameter("loginStatus");
System.out.println(loginstatus);
if(loginstatus == "failed"){ %>
	<script type="text/javascript">
	
	  $('myAlert-bottom').show();
	  setTimeout(function(){
	    $('myAlert-bottom').hide();
	  	}, 3000);
	  
	</script>
<%}
%>
  <div class="container">
    <div class="row">
      <div class="col-sm-9 col-md-7 col-lg-5 mx-auto">
        <div class="card card-signin my-5">
          <div class="card-body">
            <h5 class="card-title text-center">Sign In</h5>
            <form class="form-signin" method="post" action="/newproject/LoginServlet">
              <div class="form-label-group">
                <input type="email" name="inpEmail" id="inputEmail" class="form-control" placeholder="Email address" required autofocus>
                <label for="inputEmail">Email address</label>
              </div>

              <div class="form-label-group">
                <input type="password" name="inpPassword" id="inputPassword" class="form-control" placeholder="Password" required>
                <label for="inputPassword">Password</label>
              </div>

              <div class="custom-control custom-checkbox mb-3">
                <input type="checkbox" class="custom-control-input" id="customCheck1">
                <label class="custom-control-label" for="customCheck1">Remember password</label>
              </div>
              <button class="btn btn-lg btn-primary btn-block text-uppercase" type="submit">Sign in</button>
              <hr class="my-4">
              <a class="d-block text-center mt-2 small" href="/newproject/register.jsp">Register</a>
            </form>
          </div>
        </div>
      </div>
    </div>
    <div id="myAlert-bottom" class="myAlert-bottom alert alert-danger">
  		<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
  		<strong>Error!</strong> Your username or password is Wrong.
	</div>
</div>


</body>
</html>