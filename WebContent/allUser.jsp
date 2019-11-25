<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%> <%@ taglib
prefix="s" uri="/struts-tags"%>
<html>
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>ePay - Administrator</title>
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
        class="text-center col-11 col-md-10 col-lg-8"
        style="background-color:#f0f0f0;"
      >
        <h1>Administrator</h1>
        <table class="table table-responsive-md m-auto">
          <thead>
            <tr>
              <th>Name</th>
              <th>Email</th>
              <th>Telephone</th>
              <th>Address</th>
              <th>Password</th>
              <th>Balance</th>
              <th>Role</th>
            </tr>
          </thead>
          <tbody>
            <s:iterator value="userList">
              <tr>
                <td><s:property value="userName"/></td>
                <td><s:property value="userInfo.email"/></td>
                <td><s:property value="userInfo.telephone"/></td>
                <td><s:property value="userInfo.address"/></td>
                <td><s:property value="userPass"/></td>
                <td><s:property value="wallet.walletMoney"/></td>
                <td><s:property value="userRole.roleName"/></td>
              </tr>
            </s:iterator>
          </tbody>
        </table>
        <form action="ToPlatform" method="post" enctype="multipart/form-data">
          <div class="container text-left">
            <div class="text-center mb-2">
              <button type="submit" class="btn btn-info">&lt;&lt;Back</button>
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
  </body>
</html>
