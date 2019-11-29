<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="s" uri="/struts-tags"%>
<html>
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>ePay - Login</title>
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
        class="deposit text-center col-10 col-md-7 col-lg-5 col-lg-xl-3"
        style="background-color:#f0f0f0;"
      >
        <h1>Login</h1>
        <img src="images/login.png" align="middle" align="middle" /><br />
        <form action="Login" method="post" enctype="multipart/form-data">
          <div class="container text-left">
            <div class="form-group">
              <label for="exampleInputEmail1">Username</label>
              <input
                type="text"
                class="form-control"
                placeholder="Enter username"
                name="user.userName"
                maxlength="10"
                required
              />
            </div>
            <div class="form-group">
              <label for="exampleInputPassword1">Password</label>
              <input
                type="password"
                class="form-control"
                placeholder="Password"
                name="user.userPass"
                maxlength="10"
                required
              />
            </div>
            <div class="text-center mb-2">
              <button type="submit" class="btn btn-info">Login&gt;&gt;</button>
            </div>
            <s:if test="#session['failTimes'] == 1">
              <div class="alert alert-danger" role="alert"
                >Fail 1 time, if more than 3 then you will be blocked for 10
                minutes</div
              >
            </s:if>
            <s:elseif test="#session['failTimes'] == 2">
              <div class="alert alert-danger" role="alert"
                >Fail 2 times, if more than 3 then you will be blocked for 10
                minutes</div
              >
            </s:elseif>
            <s:elseif test="#session['failTimes'] == 3">
              <div class="alert alert-danger" role="alert"
                >Fail 3 times, you will be blocked for 10 minutes</div
              >
            </s:elseif>
          </div>
        </form>
        <form action="ToRegisterContract" method="post" enctype="multipart/form-data">
        	<button type="submit" class="btn btn-dark">Register</button>
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
