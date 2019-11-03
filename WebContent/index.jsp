<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
    <head>
        <title>ePay - Login</title>
        <style>
	    table,th,td{
	   table-layout: fixed;
		   
	    }
	 	input[type="submit"]{
	   background-color: #3fb6b2;
	    padding: 12px 45px;
	    
	    border-radius: 5px;
	    cursor: pointer;
	    color: #ffffff;
	    border: none;
	    outline: none;
	    margin: 0;
	    font-weight: bold;
	    position:relative;
	    }	
	    </style>
    </head>
    <body>
    <div class="deposit" style="border:2px blo#3FB6B2 solid;font-size:18px; background-color:#f0f0f0;margin:auto;width:300px;text-align:center;line-height:50px;">
    	<h1>Login</h1>
    	<img src="login.png" width=90px align= middle align= middle><br>
   	 	<form action="Login" method="post" enctype="multipart/form-data">  
   	 	<table align="center">
			<tr>
				<td align="left">Name:</td>
				<td><input type="text" name="user.userName" maxlength="10" required/> </td>
			</tr>
			<tr>
				<td align="left">Password:</td>
				<td><input type="password" name="user.userPass" maxlength="10" required/> </td>
			</tr>
			<tr>
				<td align="left"></td>
				<td><input type="submit" value="login>>"/> </td>
			</tr>
			
			<s:if test="#session['failTimes'] == 1">
				<div style="color: red;">Fail 1 time, if more than 3 then you will be blocked for 10 minutes</div>
			</s:if>
    		<s:elseif test="#session['failTimes'] == 2">
				<div style="color: red;">Fail 2 times, if more than 3 then you will be blocked for 10 minutes</div>
			</s:elseif>
    		<s:elseif test="#session['failTimes'] == 3">
				<div style="color: red;">Fail 3 times, you will be blocked for 10 minutes</div>
			</s:elseif>
		</table>
		</form>  
	</div>
    </body>
</html>