<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>pay</title>
    <script
      type="text/javascript"
      src="https://cdnjs.cloudflare.com/ajax/libs/webrtc-adapter/3.3.3/adapter.min.js"
    ></script>
    <script
      type="text/javascript"
      src="https://cdnjs.cloudflare.com/ajax/libs/vue/2.1.10/vue.min.js"
    ></script>
    <script type="text/javascript" src="instascan.min.js"></script>

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
	
  transition-duration: 0.4s; 
	 
    } 
	
 .button:hover {
  background-color: #4CAF50; /* Green */
  color: white;
}
 
 </style>
<!--
<div style="border:90% blue solid;font-size:13px;">
HTML DIV æ¨ç±¤ç¯ä¾
</div>
-->

<body>
<div class="pay"style="border:2px blo#3FB6B2 solid;font-size:20px; background-color:#f0f0f0;width:300px;height:600px;margin:0px auto;text-align:center;line-height:45px;">
    <div id="app">
      <div class="sidebar">
        <section class="cameras">
          <h2>Cameras</h2>
          <ul>
            <li v-if="cameras.length === 0" class="empty">No cameras found</li>
            <li v-for="camera in cameras">
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
                <a @click.stop="selectCamera(camera)">{{
                  formatName(camera.name)
                }}</a>
              </span>
            </li>
          </ul>
        </section>
        <section class="scans">
          <h2>Scans</h2>
          <ul v-if="scans.length === 0">
            <li class="empty">No scans yet</li>
          </ul>
        </section>
      </div>
      <div class="preview-container">
        <video id="preview"></video>
      </div>
    </div>
    <script type="text/javascript" src="app.js"></script>     
 </div>
</body>
</html>