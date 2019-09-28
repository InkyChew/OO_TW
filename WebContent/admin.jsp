<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
    <head>
        <title>Administrator</title>
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
				<form action="readClientAll" method="post">
   					 <input type="submit" value="See User Info"/>
				</form>
    </body>
</html>