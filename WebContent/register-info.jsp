<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>ePay - Register</title>
    <link
      rel="stylesheet"
      href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
      integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
      crossorigin="anonymous"
    />
    <link rel="stylesheet" href="css/style.css" />
  </head>

  <body>
    <div class="row align-items-center justify-content-center login-center">
      <div
        class="text-center col-10 col-md-7 col-lg-5 col-lg-xl-3"
        style="background-color:#f0f0f0;"
      >
        <h1>Info</h1>
        <form action="ToRegisterInfo" method="post" enctype="multipart/form-data">
          <div class="container text-left">
          <% if (request.getAttribute("msg") != null){ %>
				<div class="alert alert-danger" role="alert">
					<%= request.getAttribute("msg") %>
				</div>
			<% } %>
          	  <div class="form-group">
			    <label for="exampleInputEmail1">Email address</label>
			    <input type="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" name="email" 
			    value="<% if (request.getAttribute("email") != null){ %><%= request.getAttribute("email") %><% } %>">
			    <small id="emailHelp" class="form-text text-muted">We'll never share your email with anyone else.</small>
			  </div>
			  <div class="form-group">
			    <label for="InputUsername">Username</label>
			    <input type="text" class="form-control" id="InputUsername" name="username" 
			    value="<% if (request.getAttribute("username") != null){ %><%= request.getAttribute("username") %><% } %>">
			    <small id="UsernameHelpBlock" class="form-text text-muted">
				  Your username must be under or equal than 10 characters long.
				</small>
			  </div>
			  <div class="form-group">
			    <label for="InputName">Name</label>
			    <input type="text" class="form-control" id="InputName" name="name" 
			    value="<% if (request.getAttribute("name") != null){ %><%= request.getAttribute("name") %><% } %>">
			  </div>
			  <div class="form-group">
			    <label for="InputTelephone">Telephone</label>
			    <input type="text" class="form-control" id="InputTelephone" name="telephone" 
			    value="<% if (request.getAttribute("telephone") != null){ %><%= request.getAttribute("telephone") %><% } %>">
			  </div>
			  <div class="form-group">
			    <label for="InputAddress">Address</label>
			    <input type="text" class="form-control" id="InputAddress" name="address" value="<% if (request.getAttribute("address") != null){ %><%= request.getAttribute("address") %><% } %>">
			  </div>
            <div class="text-center mb-2 mt-2">
              <button type="submit" class="btn btn-secondary" name="type" value="back">Back</button>
              <button type="submit" class="btn btn-info" name="type" value="next">Next</button>
            </div>
          </div>
        </form>
      </div>
    </div>

    <script
      src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
      integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
      crossorigin="anonymous"
    ></script>
    <script
      src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
      integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
      crossorigin="anonymous"
    ></script>
    <script
      src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
      integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
      crossorigin="anonymous"
    ></script>
  </body>
</html>
