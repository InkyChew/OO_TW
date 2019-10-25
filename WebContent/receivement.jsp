<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>ePay - Receivement</title>

</head>
<style>
	
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
	
	font-size:18px;
	margin: auto;
    background-color: #f0f0f0;
    width:300px;height:450px;
	text-align:center;
	
	} 
	
	</style>
<!--
<div style="border:90% blue solid;font-size:13px;">
HTML DIV 標籤範例
</div>
-->

<body>
<div>
		<h1>Receivement</h1>
		<img src="https://api.qrserver.com/v1/create-qr-code/?data=<%=request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath()%>/toPay?transfer.traderId=<s:property value="user.userId"/> &amp;size=250x250" alt="" title="" />	
    <form action="ToPlatform" method="post" enctype="multipart/form-data">
					<input type="submit" value="<<Back" /> 
				</form>
    </div>
		
</body>
</html>
