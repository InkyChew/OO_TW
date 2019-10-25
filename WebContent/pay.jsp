<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>Pay</title>

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
    } 
 
 
 </style>
<!--
<div style="border:90% blue solid;font-size:13px;">
HTML DIV æ¨ç±¤ç¯ä¾
</div>
-->

<body>
<div class="pay"style="border:2px blo#3FB6B2 solid;font-size:20px; background-color:#f0f0f0;width:300px;height:400px;margin:0px auto;text-align:center;line-height:50px;">
     <h1>Pay</h1>
     <img src="pay.png" width=90px align= middle align= middle><br>
        <form action="Payment" method="post" enctype="multipart/form-data"> 
        <table align="center">
   <tr style="display:none;">

    <td align="left" valign="left">Id:</td>
    <td><input type="text" name="transfer.traderId" value="<%= request.getParameter("transfer.traderId") %>"></td>
    
   </tr>
   <tr>
    
    <td align="left" valign="left">Amount:</td>
    <td><input type="text" name="transfer.amount" value=""></td>
   </tr>
   <tr>
    <td align="left" valign="left">opt:</td>
    <td align="left"><input type="text" name="transfer.otp"></td>
   </tr>
   
  </table> 
  <input type="submit" value="Payment" />
  </form>
 </div>
</body>
</html>