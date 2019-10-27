<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>Pay</title>
</head>
<script>
function update(){
	var amount = document.getElementById("amount").value;
	var discountRate = document.getElementById("discountRate").value;
	var freeBound = document.getElementById("freeBound").value;
	var discountRateDisplay = document.getElementById("discountRateDisplay");
	var chargeTotal = document.getElementById("chargeTotal");
	var total = document.getElementById("total");
	
	if (amount > freeBound){
		discountRateDisplay.value = "100%";
		chargeTotal.value = 30 * (1 - 1);
	}else{
		discountRateDisplay.value = discountRate * 100 + "%";
		chargeTotal.value = 30 * (1 - discountRate);
	}
	total.value = parseInt(amount) + parseInt(chargeTotal.value);
}
function check(){
	var total = document.getElementById("total").value;
	return confirm("Sure to pay " + total + " ?");
}
</script>

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
    } 
 
 
 </style>
<!--
<div style="border:90% blue solid;font-size:13px;">
HTML DIV æ¨ç±¤ç¯ä¾
</div>
-->

<body>
<div class="pay"style="border:2px blo#3FB6B2 solid;font-size:20px; background-color:#f0f0f0;width:500px;height:800px;margin:0px auto;text-align:center;line-height:50px;">
     <h1>Pay</h1>
     <img src="pay.png" width=90px align= middle align= middle><br>
        <form action="Payment" method="post" enctype="multipart/form-data" onsubmit="return check();"> 
        <table align="center">
   <tr style="display:none;">
    <td align="left" valign="left">Id:</td>
    <td><input type="text" name="transfer.traderId" value="<%= request.getParameter("transfer.traderId") %>"></td>
   </tr>
   <tr>
    <td align="left" valign="left">Amount:</td>
    <td><input type="text" name="transfer.amount" id="amount" onchange="update();" required></td>
   </tr>
   <tr>
    <td align="left" valign="left">OPT:</td>
    <td align="left"><input type="text" name="transfer.otp" required></td>
   </tr>
   <tr>
    <td align="left" valign="left">Service Charge:</td>
    <td align="left">30</td>
   </tr>
   <tr>
    <td align="left" valign="left">Service Charge Discount Rate:</td>
    <td align="left">
	   <s:if test="user.getUserLevel==1">
	    <input type="hidden" id="freeBound" value=1000>
	    <input type="hidden" id="discountRate" value=0.2>
	   </s:if>
	   <s:elseif test="user.getUserLevel==2">
	    <input type="hidden" id="freeBound" value=100>
	    <input type="hidden" id="discountRate" value=0.5>
	   </s:elseif>
	   <s:else>
	    <input type="hidden" id="freeBound" value=10000>
	    <input type="hidden" id="discountRate" value=0>
	   </s:else>
	    <input type="text" id="discountRateDisplay" disabled>
	</td>
   </tr>
   <tr>
    <td align="left" valign="left">Service Charge Total:</td>
    <td align="left">
	   <input type="number" id="chargeTotal" disabled/>
	</td>
   </tr>
   <tr>
    <td align="left" valign="left">Total:</td>
    <td align="left"><input type="text" id="total" disabled></td>
   </tr>
  </table> 
  <input type="submit" value="Payment" />
  </form>
  <form action="ToPlatform" method="post" enctype="multipart/form-data"> 
		<input type="submit" style="background-color:#888888;border-color:#3fb6b2;border-width:3px;" value="< Back" />
	</form>
 </div>
</body>
</html>