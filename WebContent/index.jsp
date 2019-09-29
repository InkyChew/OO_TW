<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
    <head>
        <title>ePay - Login</title>
    </head>
    <style>
    table{
   	width: 100%;
   	}
   	h1{
   	text-align: center;
   	}
    div{
    width: 300px;
    min-height: 200px;
    margin: auto;
    background-color: #f0f0f0;
    padding: 20px;
    border: 1.5px solid;
    position: relative;
    }
    input:not([type="submit"]) {
    width: 100%;
	}
    input[type="submit"]{
    padding: 5px 20px;
    position: absolute;
   	bottom: 20;
   	right: 10;
    }
    </style>
    <body>
    <div>
    	<h1>Login</h1>
   	 	<form action="Login" method="post" enctype="multipart/form-data">  
   	 	<table>
			<tr>
				<td>Name:</td>
				<td><input type="text" name="user.userName" required/> </td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><input type="password" name="user.userPass" required/> </td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="login>>"/> </td>
			</tr>
		</table>
		</form>  
    </div>
    </body>
</html>