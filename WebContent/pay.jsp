<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!doctype html>
<html>
   <head>
      <meta charset="UTF-8" />
      <meta name="viewport" content="width=device-width, initial-scale=1.0" />
      <meta http-equiv="X-UA-Compatible" content="ie=edge" />
      <title>ePay - Pay</title>
      <link
        rel="stylesheet"
        href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
        integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
        crossorigin="anonymous"
      />
      <link rel="stylesheet" href="css/style.css" />
    </head>

<body>
   <div class="row align-items-center justify-content-center login-center">
      <div
        class="text-center col-10 col-md-7 col-lg-5 col-lg-xl-3"
        style="background-color:#f0f0f0;"
      >
        <h1>Pay</h1>
        <img
          src="images/pay.png"
          width="90px"
          align="middle"
          align="middle"
        /><br />
        <form action="Payment" method="post" enctype="multipart/form-data" onsubmit="return check();"> 
          <table align="center">
     <tr style="display:none;">
      <td align="left" valign="left">Id:</td>
      <td><input class="form-control" type="text" name="transfer.traderId" value="<%= request.getParameter("transfer.traderId") %>"></td>
     </tr>
     <tr>
      <td align="left" valign="left">Amount:</td>
      <td><input class="form-control" type="text" name="transfer.amount" id="amount" onchange="update();" required></td>
     </tr>
     <tr>
      <td align="left" valign="left">OTP:</td>
      <td align="left"><input class="form-control" type="text" name="transfer.otp" required></td>
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
        <input class="form-control" type="text" id="discountRateDisplay" disabled>
    </td>
     </tr>
     <tr>
      <td align="left" valign="left">Service Charge Total:</td>
      <td align="left">
       <input class="form-control" type="number" id="chargeTotal" disabled/>
    </td>
     </tr>
     <tr>
      <td align="left" valign="left">Total:</td>
      <td align="left"><input class="form-control" type="text" id="total" disabled></td>
     </tr>
    </table> 
    <input class="btn btn-info mb-2 mt-2" type="submit" value="Payment" />
    </form>
        <form action="ToPlatform" method="post" enctype="multipart/form-data">
          <div class="container text-left">
            <div class="text-center mb-2">
              <button type="submit" class="btn btn-dark">&lt;&lt;Back</button>
            </div>
          </div>
        </form>
      </div>
    </div>

    <script
      src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
      integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
      crossorigin="anonymous"
    ></script>
    <script
      src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
      integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
      crossorigin="anonymous"
    ></script>
    <script
      src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
      integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
      crossorigin="anonymous"
    ></script>
    <script>
      function update() {
        var amount = document.getElementById("amount").value;
        var discountRate = document.getElementById("discountRate").value;
        var freeBound = document.getElementById("freeBound").value;
        var discountRateDisplay = document.getElementById(
          "discountRateDisplay"
        );
        var chargeTotal = document.getElementById("chargeTotal");
        var total = document.getElementById("total");

        if (amount > freeBound) {
          discountRateDisplay.value = "100%";
          chargeTotal.value = 30 * (1 - 1);
        } else {
          discountRateDisplay.value = discountRate * 100 + "%";
          chargeTotal.value = 30 * (1 - discountRate);
        }
        if (amount != "") {
          total.value = parseInt(amount) + parseInt(chargeTotal.value);
        } else {
          total.value = 0;
        }
      }
      function check() {
        var total = document.getElementById("total").value;
        return confirm("Sure to pay " + total + " ?");
      }
    </script>
  </body>
</html>