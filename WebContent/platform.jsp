<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%> <%@ taglib
prefix="s" uri="/struts-tags"%>
<html>
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>ePay</title>
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
        <h1>ePay</h1>
        <table class="mb-3" align="center">
          <tr>
            <td>
              <form action="ToScan" method="post" enctype="multipart/form-data">
                <input
                  class="btn btn-info btn-lg btn-block btn-height-150"
                  type="submit"
                  value="Pay"
                />
              </form>
            </td>
            <td>
              <form
                action="ToTransactionDetail"
                method="post"
                enctype="multipart/form-data"
              >
                <input
                  class="btn btn-info btn-lg btn-block btn-height-150"
                  type="submit"
                  value="Transaction Details"
                />
              </form>
            </td>
          </tr>
          <td>
            <form
              action="ToReceivement"
              method="post"
              enctype="multipart/form-data"
            >
              <input
                class="btn btn-info btn-lg btn-block btn-height-150"
                type="submit"
                value="Receivement"
              />
            </form>
          </td>
          <td>
            <form
              action="ToInformation"
              method="post"
              enctype="multipart/form-data"
            >
              <input
                class="btn btn-info btn-lg btn-block btn-height-150"
                type="submit"
                value="Information"
              />
            </form>
          </td>
          <tr>
            <td>
              <form
                action="ToDeposit"
                method="post"
                enctype="multipart/form-data"
              >
                <input
                  class="btn btn-info btn-lg btn-block btn-height-150"
                  type="submit"
                  value="Deposit"
                />
              </form>
            </td>
            <td>
              <form
                action="ToServiceDetails"
                method="post"
                enctype="multipart/form-data"
              >
                <input
                  class="btn btn-info btn-lg btn-block btn-height-150"
                  type="submit"
                  value="ServiceDetails"
                />
              </form>
            </td>
          </tr>
          <tr>
            <td colspan="2">
              <form action="Logout" method="post" enctype="multipart/form-data">
                <input
                  class="btn btn-danger btn-lg btn-block btn-height-150"
                  type="submit"
                  value="Log Out"
                />
              </form>
            </td>
          </tr>
        </table>
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
