<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!doctype html>
<html>
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <meta http-equiv="X-UA-Compatible" content="ie=edge" />
  <title>ePay - Scan</title>
  <link
    rel="stylesheet"
    href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
    integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
    crossorigin="anonymous"
  />
  <link rel="stylesheet" href="css/style.css" />
  <script
    type="text/javascript"
    src="https://cdnjs.cloudflare.com/ajax/libs/webrtc-adapter/3.3.3/adapter.min.js"
  ></script>
  <script
    type="text/javascript"
    src="https://cdnjs.cloudflare.com/ajax/libs/vue/2.1.10/vue.min.js"
  ></script>
  <script type="text/javascript" src="js/instascan.min.js"></script>
</head>

<body>
  <div id="app">
    <div class="row align-items-center justify-content-center login-center">
      <div
        class="text-center col-10 col-md-7 col-lg-5 col-lg-xl-3"
        style="background-color:#f0f0f0;"
      >
        <h1>PayScan</h1>
        <div class="sidebar">
          <section class="cameras text-left">
            <h4>Cameras</h4>
            <ol>
              <li v-if="cameras.length === 0" class="empty">
                No cameras found
              </li>
              <li v-for="camera in cameras">
                <div class="btn btn-link" style="cursor: pointer;">
                  <span
                  v-if="camera.id == activeCameraId"
                  :title="formatName(camera.name)"
                  class="active"
                  >{{ formatName(camera.name) }}</span
                >
                <span
                  v-if="camera.id != activeCameraId"
                  :title="formatName(camera.name)"
                >
                  <a @click.stop="selectCamera(camera)">
                    {{ formatName(camera.name) }}
                  </a>
                </span>
              </div>
              </li>
            </ul>
          </section>
        </div>
        <div class="preview-container">
          <video id="preview" style="width: 100%;"></video>
        </div>
        <form action="ToPlatform" method="post" enctype="multipart/form-data">
          <div class="container text-left">
            <div class="text-center mb-2">
              <button type="submit" class="btn btn-info">&lt;&lt;Back</button>
            </div>
          </div>
        </form>
      </div>
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
  <script type="text/javascript" src="js/app.js"></script>
</body>
</html>