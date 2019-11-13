<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%> <%@ taglib
prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>ePay - Information</title>
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
        <h1>Information</h1>
        <table class="text-center table" align="center">
          <tr>
            <td>Level</td>
            <td>
              <s:if test="user.userRole.roleId == 2">
                Administrator
              </s:if>
              <s:else>
                <s:if test="user.userLevel == 1">
                  VIP
                </s:if>
                <s:elseif test="user.userLevel == 2">
                  VVIP
                </s:elseif>
                <s:else>
                  none
                </s:else>
              </s:else>
            </td>
          </tr>
          <tr>
            <td>UserId</td>
            <td><s:property value="user.userId" /></td>
          </tr>
          <tr>
            <td>Balance</td>
            <td>
              <s:if test="user.wallet == null">0</s:if>
              <s:else><s:property value="user.wallet.walletMoney"/></s:else>
            </td>
          </tr>
          <tr>
            <td>Name</td>
            <td><s:property value="user.userInfo.name" /></td>
          </tr>
          <tr>
            <td>Email</td>
            <td><s:property value="user.userInfo.email" /></td>
          </tr>
          <tr>
            <td>telephone</td>
            <td><s:property value="user.userInfo.telephone" /></td>
          </tr>
          <tr>
            <td>address</td>
            <td><s:property value="user.userInfo.address" /></td>
          </tr>
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
