<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
    <head>
        <title>ePay - Transaction</title>
        <style>
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
		border: 1.5px solid;
   		}
   		td, th {
    	padding: 5px 15px;
		}
   		input[type="submit"]{
   		padding: 5px 20px;
    	}
        </style>
    </head>
    <body>
    <div>
   		 <h1>Transaction</h1>
   		<s:set name="month" value="{1,2,3,4,5,6,7,8,9,10,11,12}"/>
   		<form action="timeSearch" method="post">
	   		<select name="timeSearchYear">
	   		 	<option value="2019">2019</option>
	   		 </select>
	   		 year
	   		 <select name="timeSearchMonth">
	   		 	<s:iterator value="#month" status="status">
	   		 		<option value="<s:property />"><s:property /></option>
				</s:iterator>
	   		 </select>
	   		 month
	   		 <input type="submit" value="Search"/>
   		</form>
   		 
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
					<input type="submit" value="<<Back" />
				</form>
    </div>
        
        
    </body>
</html>