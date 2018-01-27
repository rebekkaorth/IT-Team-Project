<html>

	<head>
		<!-- Web page title -->
    	<title>Top Trumps</title>
    	
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
        body {
            max-width: 3072px;
        }
        .navbar {
            background-color: #434343;
            color: #d6b945;
        }

        .col {
            margin-top: 7%;
        }
        .col-6 {
            margin-top: 15%;
            padding-left: 5%;
            padding-right: 5%;
        }

        #playersTurn {
            text-align: center;

        }

        .list-group-item.active {
            background-color: #edc115;
        }

        #round {
            text-align: center;
        }

        .card {
            margin-top: 60px;
        }

        .playersCardsLeft {
            margin-top: 60px;
        }

        strong {
            margin-left: 10px;
        }

        .round{
            margin-top: 3%;
            widows: 100%;
            text-align: center;
        }

        #draw {
            text-align: center;
        }

        .result {
            width: 60%;
            height: 8%;
            margin-left: auto;
            margin-right: auto;
            text-align: center;
        }

        .overrides button {
            margin-left: auto;
            margin-right: auto;
        }

        button {
            margin-top: 15%;
            margin-left: 40%;
            margin-right: 40%;
        }

        .continue {
            width: 50%;
            margin-top: 3%;
            margin-left: auto;
            margin-right: auto;
        }

        .footer {
            position: fixed;
            left: 0;
            bottom: 0;
            width: 100%;
            background-color: #434343;
            color: #d6b945;
            text-align: center;
        }


    </style>
    <nav class="navbar navbar-expand-lg navbar-inverse bg-inverse">
        <a class="navbar-brand">
            <img src="https://vignette.wikia.nocookie.net/logopedia/images/0/08/Top_Trumps.svg/revision/latest?cb=20160628161856" width="80" height="40" alt="Logo">
        </a>
        <h2 class="brand brand-name navbar-left"><div class"navbar-left">The world's best card game!</h2>
    </nav>

    <!--LEFT SIDE OF SCREEN -->
    <div class="container">
        <div class="row">
            <div class="col">
                <!--number of cards left in deck-->
                <div class="updatedGameData">
                    <h6>Number of cards left in your deck:<strong id="numberOfCardsInPlayersDeck">10</strong></h6>
                </div>

                <!-- playersCard object-->
                <div class="card">
                    <img class="card-img-top" src="https://www.washingtonian.com/wp-content/uploads/2017/06/6-30-17-goat-yoga-congressional-cemetery-1.jpg" width="200px" height="130px" alt="Card image cap">
                    <div class="card-body">
                        <h5 class="cardDescription">Card title</h5>
                    </div>
                    <ul class="list-group list-group-flush">
                        <li class="list-group-item" id="nameOfCat1">Category<strong id="cat1Value"></strong></li>
                        <li class="list-group-item" id="nameOfCat2">Category 2<strong id="cat2Value"></strong></li>
                        <li class="list-group-item" id="nameOfCat3">Category 3<strong id="cat3Value"></strong></li>
                        <li class="list-group-item" id="nameOfCat4">Category 4</p><strong id="cat4Value"></strong></li>
                        <li class="list-group-item" id="nameOfCat5">Category 5<strong id="cat5Value"></strong></li>
                    </ul>
                </div>
            </div>


            <!--MIDDLE OF SCREEN (changes during the game) -->
            <!-- choose category form when players turn -->
            <div class="col-6">
                <!--
                   <div id="playersTurn">
                       <h2>It's your turn!</h2>
                       <form id="userCategory" name="chooseCategory">
                           Choose category: <input title="categoryName" type="text" name="category"><br>
                           <input type="submit" value="Choose">
                       </form>
                   </div>

               -->

                <!-- round result -->
                <!--
                   <div id="round">
                       <h4>Chosen category: <strong id="chosenCategory">category</strong></h4>
                   </div>
                   <div id="resultOfPlayers" >
                   <ul class="list-group list-group-flush">
                           <li class="list-group-item result active"><p>You</p><strong id="valueUserCat">5</strong></li>
                           <li class="list-group-item result"><p>AI Player 1</p><strong id="valueAIOneCat">5</strong></li>
                           <li class="list-group-item result"><p>AI Player 2</p><strong id="valueAITwoCat">5</strong></li>
                           <li class="list-group-item result"><p>AI Player 3</p><strong id="valueAIThreeCat">5</strong></li>
                           <li class="list-group-item result"><p>AI Player 4</p><strong id="valueAIFourCat">5</strong></li>
                         </ul>
                       </div>
                       <button type="submit" class="btn btn-primary">Next Round</button>
                -->

                <!-- draw occurred -->
                <!--  <
                     <div id="draw">
                         <h4>There was a draw!</h4>
                         </div>
                         <button type="submit" class="btn btn-primary">Next Round</button>
                -->
            </div>

            <!-- RIGHT SIDE OF THE SCREEN -->
            <div class="col">
                <div class="updatedGameData">
                    <h6>Number of cards in communal pile:<strong id="numberOfCardsInCommunalPile">0</strong></h6>
                </div>

                <div class="playersCardsLeft">
                    <ul class="list-group">
                        <li class="list-group-item active">
                            <p id="nameOfPlayer1">You</p>
                            <p id="cardsOfPlayer1">0</p>
                        </li>
                        <li class="list-group-item">
                            <p id="nameOfPlayer2">AI Player 1</p>
                            <p id="cardsOfPlayer2">0</p>
                        </li>
                        <li class="list-group-item">
                            <p id="nameOfPlayer3">AI Player 2</p>
                            <p id="cardsOfPlayer3">0</p>
                        </li>
                        <li class="list-group-item">
                            <p id="nameOfPlayer4">AI Player 3</p>
                            <p id="cardsOfPlayer4">0</p>
                        </li>
                        <li class="list-group-item">
                            <p id="nameOfPlayer5">AI Player 4</p>
                            <p id="cardsOfPlayer5">0</p>
                        </li>
                    </ul>
                </div>

            </div>
        </div>

    </div>
    </div>

    <div class="updatedGameData round">
        <h6>Number of rounds:<strong id="numberOfRounds">20</strong></h6>
    </div>


    <div class="footer">
        <p>powered by THE GOATS</p>
        <p>Rebekka Orth - Lisa Laux - Vincent Schlatt - Neil Kennedy - Liang Shan</p>
    </div>
		
		<script type="text/javascript">
		
			// Method that is called on page load
			function initalize() {
			
				// --------------------------------------------------------------------------
				// You can call other methods you want to run when the page first loads here
				// --------------------------------------------------------------------------
				
				// For example, lets call our sample methods
				//helloJSONList();
				//helloWord("Student");
				
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