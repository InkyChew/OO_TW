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
   	table-layout: automatic;
   	}
   	input[type="submit"]{
    background-color: #3fb6b2;
    padding: 10px 10px;
    border-radius: 12px;
    cursor: pointer;
    color: #ffffff;
    outline: none;
    width:140px;
    height:140px;
    margin: 0;
    font-weight: bold;
    position:relative;
    }	
	tr,td{
	text-align:center;
	}
	td{
	width:160px;
	height:160px;
	}
	.epay{
		text-align:center;
	}
    </style>
    <body>
    <h1>ePay</h1>
    <div class="epay">
    <table align="center">
			
			<tr>
				<td>
					<form action="ToScan" method="post" enctype="multipart/form-data">
						<input type="submit" value="Pay" />
					</form>
				</td>
				<td>
					<form action="ToTransactionDetail" method="post" enctype="multipart/form-data">
						<input type="submit" value="Transaction Details" />
					</form>
				</td>
			</tr>
				<td>
					<form action="ToReceivement" method="post" enctype="multipart/form-data">
						<input type="submit" value="Receivement" />
					</form>
				</td>
				<td>
					<form action="ToInformation" method="post" enctype="multipart/form-data">
						<input type="submit" value="Information" />
					</form>
				</td>
			<tr>
				<td>
					<form action="ToDeposit" method="post" enctype="multipart/form-data">
						<input type="submit" value="Deposit" />
					</form>
				</td>
				<td>
					<form action="Logout" method="post" enctype="multipart/form-data">
						<input type="submit" value="Log Out" />
					</form>
				</td>
			</tr>
		</table> 
		</div>
    
    
    
    </body>
</html>