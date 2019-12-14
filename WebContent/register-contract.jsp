<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>ePay - Register</title>
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
        <h1>Contract</h1>
        
        <form action="ToRegisterContract" method="post" enctype="multipart/form-data">
          <div class="container text-left">
	    	<% if (request.getAttribute("msg") != null){ %>
				<div class="alert alert-danger" role="alert">
					<%= request.getAttribute("msg") %>
				</div>
			<% } %>
			
			<div style="height: 50vh;overflow-y: scroll; background: #FEFEFE; padding: 10px;" data-spy="scroll" data-target="#navbar-example3" data-offset="0">
  <h2>Privacy Policy</h2>
					<p>Effective date: DES 14, 2019</p>
					<p>epay ("us", "we", or "our") operates the website (the "Service").</p>
					<p>This page informs you of our policies regarding the collection, use, and disclosure of personal data when you use our Service and the choices you have associated with that data.</p>
					<p>We use your data to provide and improve the Service. By using the Service, you agree to the collection and use of information in accordance with this policy. Unless otherwise defined in this Privacy Policy, terms used in this Privacy Policy have the same meanings as in our Terms and Conditions, accessible from www.fenatelier.de</p>

					<h2>Information Collection And Use</h2>
					<p>We collect several different types of information for various purposes to provide and improve our Service to you.</p>

					<h2>Types of Data Collected</h2>
					<h3>Personal Data</h3>
					<p>While using our Service, we may ask you to provide us with certain personally identifiable information that can be used to contact or identify you ("Personal Data"). Personally identifiable information may include, but is not limited to:</p>
					<ul>
						<li>Email address</li>
						<li>First name and last name</li>
						<li>Phone number</li>
						<li>Cookies and Usage Data</li>
					</ul>
					<h3>Usage Data</h3>
					<p>We may also collect information how the Service is accessed and used ("Usage Data"). This Usage Data may include information such as your computer's Internet Protocol address (e.g. IP address), browser type, browser version, the pages of our Service that you visit, the time and date of your visit, the time spent on those pages, unique device identifiers and other diagnostic data.</p>
					<h3>Tracking &amp; Cookies Data</h3>
					<p>We use cookies and similar tracking technologies to track the activity on our Service and hold certain information.</p>
					<p>Cookies are files with small amount of data which may include an anonymous unique identifier. Cookies are sent to your browser from a website and stored on your device. Tracking technologies also used are beacons, tags, and scripts to collect and track information and to improve and analyze our Service.</p>
					<p>You can instruct your browser to refuse all cookies or to indicate when a cookie is being sent. However, if you do not accept cookies, you may not be able to use some portions of our Service.</p>
					<p>Examples of Cookies we use:</p>
					<ul>
						<li>Session Cookies. We use Session Cookies to operate our Service.</li>
						<li>Preference Cookies. We use Preference Cookies to remember your preferences and various settings.</li>
						<li>Security Cookies. We use Security Cookies for security purposes.</li>
					</ul>


					<h2>Use of Data</h2>
					<p>Formosaism uses the collected data for various purposes:</p>
					<ul>
						<li>To provide and maintain the Service</li>
						<li>To notify you about changes to our Service</li>
						<li>To allow you to participate in interactive features of our Service when you choose to do so</li>
						<li>To provide customer care and support</li>
						<li>To provide analysis or valuable information so that we can improve the Service</li>
						<li>To monitor the usage of the Service</li>
						<li>To detect, prevent and address technical issues</li>
					</ul>


					<h2>Transfer Of Data</h2>
					<p>Your information, including Personal Data, may be transferred to — and maintained on — computers located outside of your state, province, country or other governmental jurisdiction where the data protection laws may differ than those from your jurisdiction.</p>
					<p>If you are located outside Germany and choose to provide information to us, please note that we transfer the data, including Personal Data, to Germany and process it there.</p>
					<p>Your consent to this Privacy Policy followed by your submission of such information represents your agreement to that transfer.</p>
					<p>Formosaism will take all steps reasonably necessary to ensure that your data is treated securely and in accordance with this Privacy Policy and no transfer of your Personal Data will take place to an organization or a country unless there are adequate controls in place including the security of your data and other personal information.</p>

					<h2>Disclosure Of Data</h2>
					<h3>Legal Requirements</h3>
					<p>Formosaism may disclose your Personal Data in the good faith belief that such action is necessary to:</p>
					<ul>
						<li>To comply with a legal obligation</li>
						<li>To protect and defend the rights or property of Formosaism</li>
						<li>To prevent or investigate possible wrongdoing in connection with the Service</li>
						<li>To protect the personal safety of users of the Service or the public</li>
						<li>To protect against legal liability</li>
					</ul>

					<h2>Security Of Data</h2>
					<p>The security of your data is important to us, but remember that no method of transmission over the Internet, or method of electronic storage is 100% secure. While we strive to use commercially acceptable means to protect your Personal Data, we cannot guarantee its absolute security.</p>

					<h2>Service Providers</h2>
					<p>We may employ third party companies and individuals to facilitate our Service ("Service Providers"), to provide the Service on our behalf, to perform Service-related services or to assist us in analyzing how our Service is used.</p>
					<p>These third parties have access to your Personal Data only to perform these tasks on our behalf and are obligated not to disclose or use it for any other purpose.</p>

					<h2>Links To Other Sites</h2>
					<p>Our Service may contain links to other sites that are not operated by us. If you click on a third party link, you will be directed to that third party's site. We strongly advise you to review the Privacy Policy of every site you visit.</p>
					<p>We have no control over and assume no responsibility for the content, privacy policies or practices of any third party sites or services.</p>

					<h2>Children's Privacy</h2>
					<p>Our Service does not address anyone under the age of 18 ("Children").</p>
					<p>We do not knowingly collect personally identifiable information from anyone under the age of 18. If you are a parent or guardian and you are aware that your Children has provided us with Personal Data, please contact us. If we become aware that we have collected Personal Data from children without verification of parental consent, we take steps to remove that information from our servers.</p>

					<h2>Changes To This Privacy Policy</h2>
					<p>We may update our Privacy Policy from time to time. We will notify you of any changes by posting the new Privacy Policy on this page.</p>
					<p>We will let you know via email and/or a prominent notice on our Service, prior to the change becoming effective and update the "effective date" at the top of this Privacy Policy.</p>
					<p>You are advised to review this Privacy Policy periodically for any changes. Changes to this Privacy Policy are effective when they are posted on this page.</p>
</div>
<br/>
	          <div class="form-group form-check">
			    <input type="checkbox" class="form-check-input" id="exampleCheck1" name="contract" <% if (request.getAttribute("contract") != null && (boolean) request.getAttribute("contract") == true){ %>checked<% } %>>
			    <label class="form-check-label" for="exampleCheck1">
			    	I have read and agreed to the above contract.
			    </label>
			  </div>
            <div class="text-center mb-2 mt-2">
            	<button type="submit" class="btn btn-secondary" name="type" value="back">Back</button>
              <button type="submit" class="btn btn-info" name="type" value="next">Next</button>
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
