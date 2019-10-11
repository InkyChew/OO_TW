<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
    <head>
        <title>ePay - Transaction</title>
        <style>
		body{
			text-align:center;
		}
        table{
        	font-size: 18px;
        }
        table, tr, td, th{
         text-align: center;
    	 border: 2px solid;
    	 border-collapse: collapse;
   		 margin: 20px auto; 
        }
        div{
    	margin: auto;
    	background-color: #f0f0f0;
    	padding: 20px;
		text-align: center;	
		border: 1.5px ;
		display: inline-block;
   		}
   		td, th {
    	padding: 5px 15px;
		}
   		input[type="submit"]{
   		padding: 5px 20px;
   		background-color: #3fb6b2;
	    border-radius: 5px;
	    cursor: pointer;
	    color: #ffffff;
	    border: none;
	    outline: none;
	    margin: 0;
    	}  
        </style>
    </head>
    <body>
    <div>
   		 <h1>Transaction</h1>
        <table>
					<thead>
						<tr>
							<th style="width: 60px">Date</th>
							<th style="width: 80px">Type</th>
							<th style="width: 80px">TraderId</th>
							<th style="width: 60px">Amount</th>
							<th style="width: 60px">Balance</th>
						</tr> 
					</thead>
					<tbody>
						<s:iterator value="transactionDetails">
							<tr>
								<td><s:property value="date" /></td>
								<td><s:property value="type" /></td>
								<td><s:property value="traderId" /></td> 
								<td><s:property value="amount" /></td>
								<td><s:property value="balance" /></td>
							</tr>
						</s:iterator>
					</tbody>
				</table> 
				<form action="ToPlatform" method="post" enctype="multipart/form-data">
					<input type="submit" value="<<Back" / style="padding: 12px 45px;font-weight: bold;">
				</form>
    </div>
        
        
    </body>
</html>