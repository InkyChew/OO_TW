<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
    <head>
        <title>ePay</title>
    </head>
    <style>
    h1{
   	text-align: center;
    }
    table{
    width: 100%;
    }
    .outter_box{
	width: 1060px;
    margin: auto;
    padding: 100px
    }
    .inner_box{
    display: inline-block;
    vertical-align: top;
    min-height: 200px;
    width: 450px;
    background-color: #f0f0f0;
    padding: 20px;
    border: 1.5px solid;
    position: relative;
    margin: 15px;
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
    .center{
    	text-align: center;
    }
    </style>
    <body>
    <div class="outter_box">
    <div class="inner_box">
    	<h1>Pay</h1>
        <form action="Payment" method="post" enctype="multipart/form-data"> 
        <table>
			<tr>
				<td>Id:</td>
				<td><input type="text" name="transfer.traderId" value="<s:property value="transfer.traderId"/>" required></td>
			</tr>
			<tr>
				<td>Amount:</td>
				<td><input type="text" name="transfer.amount" required></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="Payment>>" /></td>
			</tr>
		</table> 
		</form>
	</div>
	<div class="inner_box">
		<h1>Transaction Detail</h1>
		<form action="TransactionDetail" method="post" enctype="multipart/form-data">
			<input type="submit" value="Check>>" />
		</form>
	</div>
	<div class="inner_box">
		<h1>Information</h1>
		<table class="center">
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
    </div>
    <div class="inner_box">
		<h1>Receivement</h1>
		<div class="center"><img src="https://api.qrserver.com/v1/create-qr-code/?data=<%=request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath()%>/ToPlatform?transfer.traderId=<s:property value="user.userId"/>&amp;size=100x100" alt="" title="" /></div>	
    </div>
    </div>
    
    </body>
</html>