<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
    <head>
        <title>Administrator</title>
    </head>
    <style>
    table{
        width: 90%;
    	text-align: center;
   	 	margin: auto;
    }
    table,th,td{
       table-layout: fixed;
    	border: 1px solid black;
    	border-collapse: collapse;
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
    <div class="deposit" style="border:2px blo#3FB6B2 solid;font-size:18px; background-color:#f0f0f0;margin:auto;width:1000px;height:210px;text-align:center;line-height:50px;">
	    <h1>Administrator</h1>
	    <table>
			<thead>
				<tr>
					<th style="width: 60px">Name</th>
					<th style="width: 80px">Password</th>
					<th style="width: 80px">Balance</th>
					<th style="width: 60px">Role</th>
				</tr>
			</thead>
			<tbody>
			<s:iterator value="userList">
					<tr>
						<td><s:property value="userName" /></td>
						<td><s:property value="userPass" /></td>
						<td><s:property value="wallet.walletMoney" /></td>
						<td><s:property value="userRole.roleName" /></td>
					</tr>
				</s:iterator>
			</tbody>
		</table>
	</div>
    </body>
</html>