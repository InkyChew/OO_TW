<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>depositSuccess</title>

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
<!--
<div style="border:90% blue solid;font-size:13px;">
HTML DIV æ¨ç±¤ç¯ä¾
</div>
-->

<body>
<div class="pay"style="border:2px blo#3FB6B2 solid;font-size:20px; background-color:#f0f0f0;width:300px;height:470px;margin:0px auto;text-align:center;line-height:45px;">
     <h1>deposit successful!</h1>
     <img src="deposit.png" width=90px align= middle align= middle><br>
        diposit Detail
        <form action="Payment" method="post" enctype="multipart/form-data"> 
        <table align="center">
   <tr>

    <td align="left" valign="left">Date:</td>
<!--    <td><input type="text" name="transfer.traderId" value="" ></td>-->
    
   </tr>
   <tr>
    
    <!--<td align="left" valign="left">Type:</td>-->
<!--    <td><input type="text" name="transfer.amount"></td>-->
   </tr>
   <tr>
    <!--<td align="left" valign="left">TraderId:</td>
    <td align="left"><input type="text" name="transfer.amount"></td>-->
   </tr>
    <tr>
    <td align="left" valign="left">Amount:</td>
<!--    <td align="left"><input type="text" name="transfer.amount"></td>-->
   </tr>
	 <tr>
    <td align="left" valign="left">Balance:</td>
<!--    <td align="left"><input type="text" name="transfer.amount"></td>-->
   </tr>		
  </table> 

  <input type="submit" value="< Back" />
  </form>
 </div>
</body>
</html>