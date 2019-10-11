<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>ePay - Information</title>

</head>
<style>
	body{
		text-align: center;
	}
    td{
    text-align:left;
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
	div{ 
	/* border:2px #3FB6B2 solid;*/
	font-size:18px; 
	margin: auto;
    background-color: #f0f0f0;
    display: inline-block;
	text-align:center; 
	line-height:50px;
	min-width: 450px;
	} 
	
	</style>
<!--
<div style="border:90% blue solid;font-size:13px;">
HTML DIV 標籤範例
</div>
-->

<body>
<div class="Information" >
    	<h1>Information</h1>
        
        <table class="center" align="center">
			<tr>
				<td>UserId</td>
				<td><s:property value="user.userId" /></td>
			</tr>
			<tr>
				<td>Balance</td>
				<td><s:property value="user.wallet.walletMoney" /></td>
			</tr>
			<tr>
				<td>Name</td>
				<td><s:property value="user.userInfo.name" /></td>
			</tr>
			<tr>
				<td>telephone</td>
				<td><s:property value="user.userInfo.telephone" /></td>
			</tr>
			<tr>
				<td>address</td>
				<td><s:property value="user.userInfo.address" /></td>
			</tr>
		</table>
		<form action="ToPlatform" method="post" enctype="multipart/form-data">
					<input type="submit" value="<<Back" />
				</form>
		
	</div>
</body>
</html>
