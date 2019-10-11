<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>Success</title>

</head>
<style>
 table,th,td{
   table-layout: fixed
    
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
	
  transition-duration: 0.4s; 
	 
    } 
	
 .button:hover {
  background-color: #4CAF50; /* Green */
  color: white;
}
 
 </style>
<body>
<div class="loginSuccess" style="border:2px blo#3FB6B2 solid;font-size:20px; background-color:#f0f0f0;width:300px;height:250px;margin:0px auto;text-align:center;line-height:45px;">
     <h1>SUCCESS!</h1>
        <form action="Login" method="post" enctype="multipart/form-data"> 
		  	<input type="submit" value="< Login"/>
		</form>
 </div>
</body>
</html>