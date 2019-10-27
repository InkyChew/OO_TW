<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>ePay - VIP</title>

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
	table, tr, td, th {
    border: 2px solid;
    border-collapse: collapse;
	}
	table{
	margin: 10px
	}
	td{
	padding: 10px
	}
	</style>
<!--
<div style="border:90% blue solid;font-size:13px;">
HTML DIV æ¨ç±¤ç¯ä¾
</div>
-->

<body>
<div class="Information" >
    	<h1>ServiceDetails</h1>
    	<div style="font-style: italic;">service charge is 30 dollars for each payment</div>
        <table class="center" align="center">
			<tr>
				<td>Level\Service Charge</td>
				<td>Pay</td>
				<td>Receive</td>
				<td>Deposit</td>
			</tr>
			<tr>
				<td>Normal</td>
				<td>
				> 10000 no charge
				</td>
				<td>no</td>
				<td>no</td>
			</tr>
			<tr>
				<td>VIP</td>
				<td>
				Service charge discount 20%, 
				> 1000 no charge
				</td>
				<td>no</td>
				<td>no</td>
			</tr>
			<tr>
				<td>Normal</td>
				<td>
				Service charge discount 50%, 
				> 100 no charge
				</td>
				<td>no</td>
				<td>no</td>
			</tr>
		</table>
		<form action="ToPlatform" method="post" enctype="multipart/form-data">
			<input type="submit" value="<<Back" />
		</form>
	</div>
</body>
</html>
