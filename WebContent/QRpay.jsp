<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
    <head>
        <title>QRPay</title>
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
    	<h1>QRPay</h1>
        <form action="Payment" method="post" enctype="multipart/form-data"> 
        <input type="hidden" name="<s:property value="transfer.traderId"/>" value="<s:property value="transfer.traderId"/>"/>
        <table>
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
	
    </div>
    
    </body>
</html>