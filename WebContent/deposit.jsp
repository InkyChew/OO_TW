<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>ePay - Deposit</title>

</head>
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

<body>
<div class="deposit" style="border:2px blo#3FB6B2 solid;font-size:18px; background-color:#f0f0f0;margin:auto;width:300px;height:350px;text-align:center;line-height:50px;">
    	<h1>Deposit</h1>
    	<img src="deposit.png" width=90px align= middle align= middle><br>
        <form action="Deposit" method="post" enctype="multipart/form-data"> 
        <table align="center">
			
			<tr>
				
				<td align="left" >Amount:</td>
				<td><input type="text" name="transfer.amount"></td>
			</tr>
			
			
		</table>
		 
		<input type="submit" value="Deposit" align="right" style="margin-top:20px;"/>
		</form>
		<form action="ToPlatform" method="post" enctype="multipart/form-data"> 
		<input type="submit" style="background-color:#888888;border-color:#3fb6b2;border-width:3px;" value="< Back" />
		</form>
	</div>
</body>
</html>
