<html xmlns="http://www.w3.org/1999/html">

	<head>
		<!-- Web page title -->
    	<title>Top Trumps - Statistics</title>
    	
    	<!-- Import JQuery, as it provides functions you will probably find useful (see https://jquery.com/) -->
    	<script src="https://code.jquery.com/jquery-2.1.1.js"></script>
    	<script src="https://code.jquery.com/ui/1.11.1/jquery-ui.js"></script>
    	<link rel="stylesheet" href="https://code.jquery.com/ui/1.11.1/themes/flick/jquery-ui.css">

		<!-- Optional Styling of the Website, for the demo I used Bootstrap (see https://getbootstrap.com/docs/4.0/getting-started/introduction/) -->
		<link rel="stylesheet" href="http://dcs.gla.ac.uk/~richardm/TREC_IS/bootstrap.min.css">
    	<script src="http://dcs.gla.ac.uk/~richardm/vex.combined.min.js"></script>
    	<script>vex.defaultOptions.className = 'vex-theme-os';</script>
    	<link rel="stylesheet" href="http://dcs.gla.ac.uk/~richardm/assets/stylesheets/vex.css"/>
    	<link rel="stylesheet" href="http://dcs.gla.ac.uk/~richardm/assets/stylesheets/vex-theme-os.css"/>
    	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">

	</head>

    <body onload="initalize()"> <!-- Call the initalize method when the page loads -->

		<style>

            .navbar {
                background-color: #434343;
                color: #d6b945;
                text-align: center
            }

            a.brand-name {
                font-size: 2.125rem;
                font-family: 'Arial Rounded MT Bold'
            }

            .footer {
                position: absolute;
                right: 0;
                bottom: 0;
                left: 0;
                padding: 1rem;
                width: 100%;
                background-color: #434343;
                color: #d6b945;
                text-align: center;
            }

            body {
                background: #b5b5b5;
                color: black;
            }

            .row.top {
				height: 15%;
            }

			.row.middle {
				background-color: #d6b945;
				height: 40%;
			}

			.row.bottom {
				padding-top: 15px;
				height: 25%;
			}

            /* Clear floats after the columns */
            .row.bottom:after {
                content: "";
                display: table;
                clear: both;
            }

            .col {

                float: left;
                padding: 15px;
			}

			/*
            .button {
                display: inline-block;
                padding: 15px 25px;
                font-size: 24px;
                cursor: pointer;
                text-align: center;
                text-decoration: none;
                outline: none;
                color: #fff;
                background-color: #4CAF50;
                border: none;
                border-radius: 15px;
                box-shadow: 0 9px #999;
            }

            .button:hover {background-color: #3e8e41}

            .button:active {
                background-color: #3e8e41;
                box-shadow: 0 5px #666;
                transform: translateY(4px);
            }
			*/


            .centerButtons{
                margin:0 auto;
                float:left;
            }

            a.animateButton:link, a.animateButton:visited {
                position: relative;
                display: block;
                margin: 30px auto 0;
                padding: 14px 15px;
                color: black;
                font-size:14px;
                font-weight: bold;
                text-align: center;
                text-decoration: none;
                text-transform: uppercase;
                overflow: hidden;
                letter-spacing: .08em;
                border-radius: 0;
                text-shadow: 0 0 1px rgba(0, 0, 0, 0.2), 0 1px 0 rgba(0, 0, 0, 0.2);
                -webkit-transition: all 1s ease;
                -moz-transition: all 1s ease;
                -o-transition: all 1s ease;
                transition: all 1s ease;
            }
            a.animateButton:link:after, a.animateButton:visited:after {
                content: "";
                position: absolute;
                height: 0%;
                left: 50%;
                top: 50%;
                width: 150%;
                z-index: -1;
                -webkit-transition: all 0.75s ease 0s;
                -moz-transition: all 0.75s ease 0s;
                -o-transition: all 0.75s ease 0s;
                transition: all 0.75s ease 0s;
            }
            a.animateButton:link:hover, a.animateButton:visited:hover {
                color: black;
                text-shadow: none;
            }
            a.animateButton:link:hover:after, a.animateButton:visited:hover:after {
                height: 450%;
            }
            a.animateButton:link, a.animateButton:visited {
                position: relative;
                display: block;
                margin: 30px auto 0;
                padding: 14px 15px;
                color: black;
                font-size:14px;
                border-radius: 0;
                font-weight: bold;
                text-align: center;
                text-decoration: none;
                text-transform: uppercase;
                overflow: hidden;
                letter-spacing: .08em;
                text-shadow: 0 0 1px rgba(0, 0, 0, 0.2), 0 1px 0 rgba(0, 0, 0, 0.2);
                -webkit-transition: all 1s ease;
                -moz-transition: all 1s ease;
                -o-transition: all 1s ease;
                transition: all 1s ease;
            }

            /*
            Play Game button animation
             */
            a.animateButton.playButton {
                border: 2px solid #000000;
            }
            a.animateButton.playButton:after {
                background: #d6b945;
                -moz-transform: translateX(-50%) translateY(-50%) rotate(-25deg);
                -ms-transform: translateX(-50%) translateY(-50%) rotate(-25deg);
                -webkit-transform: translateX(-50%) translateY(-50%) rotate(-25deg);
                transform: translateX(-50%) translateY(-50%) rotate(-25deg);
            }


		</style>

        <nav class="navbar navbar-expand-lg navbar-inverse bg-inverse">
            <a class="navbar-brand" href="http://localhost:7777/toptrumps">
                <img src="https://vignette.wikia.nocookie.net/logopedia/images/0/08/Top_Trumps.svg/revision/latest?cb=20160628161856" width="80" height="40" alt="Logo">
            </a>
            <a class="brand-name navbar-left">The world's best card game!</a>
        </nav>



		<div class="row top" style="padding-top: 50px">
            <div class="col"></div>
			<div class="col" style="text-align: center">
                <h3>Total number of games played: <strong>id = ?</strong></h3>
			</div>
            <div class="col"></div>
		</div>




    	<div class="row middle" style="align-items: center">
            <div class="col" style="text-align: center">
                <h4 style="font-family: 'PT Serif'">Human</h4>
                <p>
                    <img src="https://image.flaticon.com/icons/svg/453/453376.svg" alt="Human Icon" width="50" height="80">
                </p>
                <h4><strong>id = ?</strong></h4>
            </div>
			<div class="col" style="text-align: center">
                <h1 style="font-family: 'PT Serif'">Games won by:</h1>
				<br><br>
                <h5>Average number of draws: <strong>id = ?</strong></h5>
                <br><br>
                <h5>Highest number of rounds in one game: <strong>id = ?</strong></h5>
                <br><br>
			</div>
			<div class="col" style="text-align: center">
                <h4 style="font-family: 'PT Serif'">AI</h4>
                <p>
                    <img src="https://image.flaticon.com/icons/svg/653/653507.svg" alt="AI Icon" width="50" height="80">
                </p>
                <h4><strong>id = ?</strong></h4>
			</div>
    	</div>




    	<div class="row bottom">
			<div class="col"></div>
			<div class="col centerButtons">
                <a href="http://localhost:7777/toptrumps/game" id="playGameButton" class="btn animateButton playButton">Play Again</a>
			</div>
            <div class="col"></div>
    	</div>




    <div class="footer">powered by THE GOATS</br>Rebekka Orth - Lisa Laux - Vincent Schlatt - Neil Kennedy - Liang Shan
    </div>


		
		<script type="text/javascript">
		
			// Method that is called on page load
			function initalize() {
			
				// --------------------------------------------------------------------------
				// You can call other methods you want to run when the page first loads here
				// --------------------------------------------------------------------------
				
				// For example, lets call our sample methods
				helloJSONList();
				helloWord("Student");
				
			}
			
			// -----------------------------------------
			// Add your other Javascript methods Here
			// -----------------------------------------
		
			// This is a reusable method for creating a CORS request. Do not edit this.
			function createCORSRequest(method, url) {
  				var xhr = new XMLHttpRequest();
  				if ("withCredentials" in xhr) {

    				// Check if the XMLHttpRequest object has a "withCredentials" property.
    				// "withCredentials" only exists on XMLHTTPRequest2 objects.
    				xhr.open(method, url, true);

  				} else if (typeof XDomainRequest != "undefined") {

    				// Otherwise, check if XDomainRequest.
    				// XDomainRequest only exists in IE, and is IE's way of making CORS requests.
    				xhr = new XDomainRequest();
    				xhr.open(method, url);

 				 } else {

    				// Otherwise, CORS is not supported by the browser.
    				xhr = null;

  				 }
  				 return xhr;
			}
		
		</script>
		
		<!-- Here are examples of how to call REST API Methods -->
		<script type="text/javascript">
		
			// This calls the helloJSONList REST method from TopTrumpsRESTAPI
			function helloJSONList() {
			
				// First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/helloJSONList"); // Request type and URL
				
				// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
  					alert("CORS not supported");
				}

				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives 
				xhr.onload = function(e) {
 					var responseText = xhr.response; // the text of the response
					alert(responseText); // lets produce an alert
				};
				
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();		
			}
			
			// This calls the helloJSONList REST method from TopTrumpsRESTAPI
			function helloWord(word) {
			
				// First create a CORS request, this is the message we are going to send (a get request in this case)
				var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/helloWord?Word="+word); // Request type and URL+parameters
				
				// Message is not sent yet, but we can check that the browser supports CORS
				if (!xhr) {
  					alert("CORS not supported");
				}

				// CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
				// to do when the response arrives 
				xhr.onload = function(e) {
 					var responseText = xhr.response; // the text of the response
					alert(responseText); // lets produce an alert
				};
				
				// We have done everything we need to prepare the CORS request, so send it
				xhr.send();		
			}

		</script>
		
		</body>
</html>