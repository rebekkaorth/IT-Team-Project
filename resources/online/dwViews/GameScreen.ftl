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
            background: #b5b5b5;
            color: black;
            max-width: 3072px;
            position: relative;
            margin: 0;
            padding-bottom: 6rem;
            min-height: 100%;
        }
        .navbar {
            background-color: #434343;
            color: #d6b945;
            text-align: center
        }

        a.brand-name {
            font-size: 2.125rem;
            font-family: 'Arial Rounded MT Bold';
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

        .card-img-top {
            width: 253px;
            height: 130px;
        }

        p {
            margin-bottom: 9px;
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
            background-color: #edc115

        }

        .list-group-item {
            padding-bottom: 11px;
        }

        #round {
            text-align: center;
        }
        .card {
            margin-top: 55px;
            border: solid black 1px;
        }

        .card-body {
            border-bottom: solid black 1px;
        }

        .playersCardsLeft {
            margin-top: 60px;
        }

        strong {
            margin-left: 10px;
        }
        
        .round{
            windows: 100%;
            text-align: center;
            
        }
        #draw {
            text-align: center;
        }

        #nextRound {
            text-align: center;
        }

        .result {
            width: 60%;
            height: 8%;
            margin-left: auto;
            margin-right: auto;
            text-align: center;
        }

        .animateButton {
            width: 300px;
            height: 50px;
            border: solid black 1px;
            background-color: #d6b945;
            margin-top: 20%;
            margin-left: auto;
            padding-top: 3%;
        }

        h4 {
            text-align: center;
        }
        
        .gameEnded {
            margin: 0 auto;
            float: left;
        }

        .btn-group-vertical {
            width: 50%;
        }

        .catButton {
            margin-left: auto;
        }

        .animateButton: hover {
            background-color: slategrey;
        }

         #resultOfPlayers {
             margin-top: 8%;             
         }

        .catBtn {
             padding-top: 6%;
        }

    </style>

    <nav class="navbar navbar-expand-lg navbar-inverse bg-inverse">
        <a class="navbar-brand" href="http://localhost:7777/toptrumps">
            <img src="https://vignette.wikia.nocookie.net/logopedia/images/0/08/Top_Trumps.svg/revision/latest?cb=20160628161856" width="80" height="40" alt="Logo">
        </a>
        <a class="brand-name navbar-left">The world's best card game!</a>
    </nav>

    <!--LEFT SIDE OF SCREEN -->
    <div class="container">
        <div class="row">
            <div class="col">
                <!--number of cards left in deck-->
                <div class="currentCard">
                    <h3>Your current card</h3>
                </div>

                <!-- playersCard object-->
                <div class="card">
                    <img class="card-img-top" src="http://www.twizzle.co.uk/wp-content/uploads/2016/11/mfalcon.jpg" alt="Card image cap">
                    <div class="card-body">
                        <h5 class="cardDescription"></h5>
                    </div>
                    <ul class="list-group list-group-flush">
                        <li class="list-group-item">
                            <h6 id="nameOfCat1"></h6>
                            <h6 id="cat1Value"></h6>
                        </li>
                        <li class="list-group-item">
                            <h6 id="nameOfCat2"></h6>
                            <h6 id="cat2Value"></h6>
                        </li>
                        <li class="list-group-item">
                            <h6 id="nameOfCat3"></h6>
                            <h6 id="cat3Value"></h6>
                        </li>
                        <li class="list-group-item">
                            <h6 id="nameOfCat4"></h6>
                            <h6 id="cat4Value"></h6>
                        </li>
                        <li class="list-group-item">
                            <h6 id="nameOfCat5"></h6>
                            <h6 id="cat5Value"></h6>
                        </li>
                    </ul>
                </div>
            </div>


            <!--MIDDLE OF SCREEN (changes during the game) -->
            <!-- choose category form when players turn -->
            <div class="col-6 middle">

                <!-- first round -->
                <div id="firstRound">
                    <h4>Start the first round</h4>
                    <div class="btn animateButton btn-primary" onclick="showCategory()"><p id="nextRound">First Round</p></div>
                </div>


                <!-- players turn -->
                  <div id="playersTurn">
                       <h2>It's your turn!</h2>
                      <h4>Choose a category</h4>
                      <div class="btn-group-vertical" role="group">
                          <div class="btn animateButton btn-primary catButton"> <p class="catBtn" id="nameOfCat1Btn" onclick="setHumanChosenCat($(this).text())"></p></div>
                          <div class="btn animateButton btn-primary"> <p class="catBtn" id="nameOfCat2Btn" onclick="setHumanChosenCat($(this).text())"></p></div>
                          <div class="btn animateButton btn-primary"> <p class="catBtn" id="nameOfCat3Btn" onclick="setHumanChosenCat($(this).text())"></p></div>
                          <div class="btn animateButton btn-primary"> <p class="catBtn" id="nameOfCat4Btn" onclick="setHumanChosenCat($(this).text())"></p></div>
                          <div class="btn animateButton btn-primary"> <p class="catBtn" id="nameOfCat5Btn" onclick="setHumanChosenCat($(this).text())"></p></div>
                      </div>
                  </div>

                <!-- chosen category -->
                   <div id="chosenCat">
                       <h4>Chosen category: <strong id="chosenCategory"></strong></h4>
                       <div class="btn animateButton btn-primary" onclick="roundResult()"><p id="playGameButton">Show result</p></div>
                   </div>

                <!-- round result -->
                <div id="round">
                       <h4>Round winner:<strong id="roundWinner"></strong></h4>

                   <div id="resultOfPlayers" >
                   <ul class="list-group list-group-flush">
                           <li class="list-group-item result"><p  class="nameOfPlayer1"></p><p id="valueCatPlayer1"></p></li>
                           <li class="list-group-item result"><p  class="nameOfPlayer2"></p><p id="valueCatPlayer2"></p></li>
                           <li class="list-group-item result"><p  class="nameOfPlayer3"></p><p id="valueCatPlayer3"></p></li>
                           <li class="list-group-item result"><p  class="nameOfPlayer4"></p><p id="valueCatPlayer4"></p></li>
                           <li class="list-group-item result"><p  class="nameOfPlayer5"></p><p id="valueCatPlayer5"></p></li>
                         </ul>
                       <div class="btn animateButton btn-primary" onclick="showCategory()"> <p>Next Round</p></div>
                       </div>
                   </div>

                <!-- draw occurred -->
                    <div id="draw">
                         <h4>There was a draw!</h4>
                         <div class="btn animateButton btn-primary" onclick="showCategory()"><p>Next Round</p></div>
                    </div>

            </div>

            <!-- RIGHT SIDE OF THE SCREEN -->
            <div class="col">
                <div class="updatedGameData">
                    <h6>Number of cards in communal pile:</h6>
                    <h6 id="numberOfCardsInCommunalPile"></h6>
                </div>

                <div class="playersCardsLeft">
                    <ul class="list-group">
                        <li class="list-group-item">
                            <p class="nameOfPlayer1"></p>
                            <p id="cardsOfPlayer1"></p>
                        </li>
                        <li class="list-group-item">
                            <p class="nameOfPlayer2"></p>
                            <p id="cardsOfPlayer2"></p>
                        </li>
                        <li class="list-group-item">
                            <p class="nameOfPlayer3"></p>
                            <p id="cardsOfPlayer3"></p>
                        </li>
                        <li class="list-group-item">
                            <p class="nameOfPlayer4"></p>
                            <p id="cardsOfPlayer4"></p>
                        </li>
                        <li class="list-group-item">
                            <p class="nameOfPlayer5"></p>
                            <p id="cardsOfPlayer5"></p>
                        </li>
                    </ul>
                </div>

            </div>
        </div>

    </div>
    </div>

    <div class="updatedGameData round">
        <h6>Number of rounds:</h6>
        <h6 id="numberOfRounds"></h6>
    </div>

    <!-- game ended prompt -->
    <div class="gameEnded">
        <div class="gameEndedText">
            <h2>Game over!</h2>
            <h3>The winner is: </>
            <h3 id="gameWinner"></h3>
        </div>
        <div class="selectionButtons">
            <div class="btn animateButton btn-primary">
                <a href="http://localhost:7777/toptrumps">Back to selection screen</a>
            </div>
            <div class="btn animateButton btn-primary">
                <a href="http://localhost:7777/toptrumps/stats">Show Stats</a>
            </div>
        </div>
    </div>

    <div class="footer">powered by THE GOATS</br>Rebekka Orth - Lisa Laux - Vincent Schlatt - Neil Kennedy - Liang Shan
    </div>
		
		<script type="text/javascript">

            var activePlayerVar;
            var numOfPlayers;
            var gameWinner;
            var noHumanPlayer;

			// Method that is called on page load
			function initalize() {
                startSetup(); // start game
                noHumanPlayer = false;
			}

            // FUNCTIONALITY TO CALL REST API METHODS

            //get category names of first card of user
            function numberOfPlayers() {
                var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/numOfPlayers"); // Request type and URL
                if (!xhr) {
                    alert("CORS not supported");
                }
                xhr.onload = function(e) {
                    var responseText = JSON.parse(xhr.response); // the text of the response
                    numOfPlayers = parseInt(responseText[0]);
                };
                xhr.send();
            }

            //get number of cards left in user's deck
            function roundCount() {
                // First create a CORS request, this is the message we are going to send (a get request in this case)
                var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/roundCount"); // Request type and URL
                // Message is not sent yet, but we can check that the browser supports CORS
                if (!xhr) {
                    alert("CORS not supported");
                }
                // CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
                // to do when the response arrives
                xhr.onload = function(e) {
                    var responseText = JSON.parse(xhr.response); // the text of the response
                    $("#numberOfRounds").text(parseInt(responseText[0]));
                };
                // We have done everything we need to prepare the CORS request, so send it
                xhr.send();
            }

            function getNumOfCardsInComPile() {
                var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/numCardsInComPile"); // Request type and URL
                if (!xhr) {
                    alert("CORS not supported");
                }
                xhr.onload = function(e) {
                    var responseText = JSON.parse(xhr.response); // the text of the response
                    $('#numberOfCardsInCommunalPile').text(parseInt(responseText[0]));
                };
                xhr.send();
            }

            function namesOfPlayers() {
                var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/namesOfPlayers"); // Request type and URL
                if (!xhr) {
                    alert("CORS not supported");
                }
                xhr.onload = function() {
                   var  responseText = JSON.parse(xhr.response); // the text of the response
                    var n = parseInt(responseText[0]); //number of players
                    for (var i=1; i<(n+1); i++) {
                        $(".nameOfPlayer"+i).text(responseText[i]);
                       // if(activePlayerVar === responseText[i]){
                       //    $("p:contains('"+ activePlayerVar +"')").parent().addClass("active");
                       // }
                    }
                };
                xhr.send();
            }

            //cards of each player and names of each player need to be sent together
           /* function getNumOfCardsForEachPlayer() {
                var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/getNumOfCardsForEachPlayer"); // Request type and URL
                if (!xhr) {
                    alert("CORS not supported");
                }
                xhr.onload = function(e) {
                    var responseText = JSON.parse(xhr.response); // the text of the response
                    var n = parseInt(responseText[0]);
                    $("#numberOfCardsInPlayersDeck").text(parseInt(responseText[1]));
                    for(var i=1; i<(n+1); i++) {
                        $("#cardsOfPlayer"+i).text(parseInt(responseText[i]));
                    }
                };
                xhr.send();
            } */

            function activePlayer() {
                var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/activePlayer"); // Request type and URL
                if (!xhr) {
                    alert("CORS not supported");
                }
                xhr.onload = function(e) {
                    var responseText = JSON.parse(xhr.response); // the text of the response
                    $("p").parent().removeClass("active");
                    console.log("response old active player: " + activePlayerVar);
                    activePlayerVar = responseText;
                    $("p:contains('"+ activePlayerVar +"')").parent().toggleClass("active");
                    console.log("response active player: " + activePlayerVar);
                };
                xhr.send();
            }

            function cardCatNames() {
                var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/cardCatNames"); // Request type and URL
                if (!xhr) {
                    alert("CORS not supported");
                }
                xhr.onload = function(e) {
                    var responseText = JSON.parse(xhr.response); // the text of the response
                    for(var i=0; i<responseText.length; i++) {
                        $("#nameOfCat"+(i+1)).text(responseText[i]);
                        $("#nameOfCat"+(i+1)+"Btn").text(responseText[i]);
                    }
                };
                xhr.send();
            }

            function getFirstCardDescription() {
                var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/getFirstCardDescription"); // Request type and URL
                if (!xhr) {
                    alert("CORS not supported");
                }
                xhr.onload = function(e) {
                    var responseText = JSON.parse(xhr.response); // the text of the response
                    $(".cardDescription").text(responseText);
                   // $(".card-img-top").attr("src", "http://dcs.gla.ac.uk/~richardm/TopTrumps/, in the format http://dcs.gla.ac.uk/~richardm/TopTrumps/"+responseText+".jpg")
                };
                xhr.send();
            }

            function getFirstCardValues() {
                var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/getFirstCardValues"); // Request type and URL
                if (!xhr) {
                    alert("CORS not supported");
                }
                xhr.onload = function(e) {
                    var responseText = JSON.parse(xhr.response); // the text of the response
                    for (var i=0; i<responseText.length; i++) {
                        $("#cat"+(i+1)+"Value").text(parseInt(responseText[i]));
                    }
                };
                xhr.send();
            }

            function getRoundWinner() {
                var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/getRoundWinner"); // Request type and URL
                if (!xhr) {
                    alert("CORS not supported");
                }
                xhr.onload = function(e) {
                    var responseText = JSON.parse(xhr.response); // the text of the response
                    $('#roundWinner').text(responseText);
                };
                xhr.send();
            }

            function getGameWinner() {
                var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/getGameWinner"); // Request type and URL
                if (!xhr) {
                    alert("CORS not supported");
                }
                xhr.onload = function(e) {
                     gameWinner = JSON.parse(xhr.response);
                     console.log(gameWinner);
                };
                xhr.send();
            }

            //get names and number of cards of players
            function playerNamesAndNumOfCards() {
                var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/playerNamesAndNumOfCards"); // Request type and URL
                if (!xhr) {
                    alert("CORS not supported");
                }
                xhr.onload = function(e) {
                    var namesAndCards = JSON.parse(xhr.response);

                    for(var m=0; m<numOfPlayers; m++){
                        $(".nameOfPlayer" + (m+1)).text("");
                        $("#cardsOfPlayer" + (m+1)).text("");
                        $("#valueCatPlayer" + j).text("");
                    }

                    console.log(namesAndCards);
                    var allPlayers = namesAndCards.length;
                    console.log(allPlayers);
                    var j=0;
                    for(var i=0; i<allPlayers; i++) {
                        $(".nameOfPlayer" + (j + 1)).text(namesAndCards[i]);
                        console.log(namesAndCards[i]);
                        $("#cardsOfPlayer" + (j + 1)).text(parseInt(namesAndCards[i + 1]));
                        console.log(namesAndCards[i + 1]);
                        j++;
                        i++;
                    }
                    if(namesAndCards[0] !== "Human Player") {
                        noHumanPlayer = true;
                    }
                };
                xhr.send();
            }

            function catValuesOfPlayers() {
                var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/catValuesOfPlayers"); // Request type and URL
                if (!xhr) {
                    alert("CORS not supported");
                }
                xhr.onload = function(e) {
                    var responseText = JSON.parse(xhr.response); // the text of the response
                    for (var j=1; j<6; j++){
                        $("#valueCatPlayer" + j).text("");
                    }
                    console.log(responseText);
                    for (var i=0; i<responseText.length; i++){
                        $("#valueCatPlayer"+(i+1)).text(parseInt(responseText[i]));
                    }
                };
                xhr.send();
            }


            function humanPlayerChosenCategory(category) {
                var xhr = createCORSRequest('PUT', "http://localhost:7777/toptrumps/humanPlayerChosenCategory?category="+category); // Request type and URL+parameters
                if (!xhr) {
                    alert("CORS not supported");
                }
                xhr.onload = function(e) {
                    var responseText = xhr.response; // the text of the response
                    $('#chosenCategory').text(responseText);

                };
                xhr.send();
            }

            function getAIchosenCategory() {
                var xhr = createCORSRequest('PUT', "http://localhost:7777/toptrumps/getAIchosenCategory"); // Request type and URL+parameters
                if (!xhr) {
                    alert("CORS not supported");
                }
                xhr.onload = function(e) {
                    var responseText = xhr.response; // the text of the response
                    $('#chosenCategory').text(responseText);

                };
                xhr.send();
            }


            //FUNCTIONALITY TO UPDATE UI DURING A GAME

            //update middle of game
            function showRoundResult() {
			    $('#firstRound').hide();
                $('#draw').hide();
                $('#playersTurn').hide();
                $("#chosenCat").hide();
                $('#round').show();
            }

            function showDrawOccurred() {
			    $('#firstRound').hide();
                $('#round').hide();
                $('#playersTurn').hide();
                $("#chosenCat").hide();
                $('#draw').show();
            }

            function showChooseCategory() {
			    $('#firstRound').hide();
                $('#round').hide();
                $('#draw').hide();
                $("#chosenCat").hide();
                $('#playersTurn').show();
            }

            function showSelectedCategory() {
                $('#firstRound').hide();
                $('#round').hide();
                $('#draw').hide();
                $('#playersTurn').hide();
                $("#chosenCat").show();
            }

            function setHumanChosenCat(categoryChosen) {
                humanPlayerChosenCategory(categoryChosen);
                getRoundWinner();
                showSelectedCategory();
            }

            function showCategory() {
                activePlayer();
                if(activePlayerVar==="Human Player") {
                    showChooseCategory();

                } else {
                    getAIchosenCategory();
                    getRoundWinner();
                    showSelectedCategory();
                }
                getRoundWinner();
            }

            function roundResult() {
                numberOfPlayers();
                catValuesOfPlayers();
                playerNamesAndNumOfCards();
                getNumOfCardsInComPile();
                getFirstCardValues();
                getFirstCardDescription();
                if(noHumanPlayer && numOfPlayers > 1) {
                    $(".row").hide();
                    endGameWithoutHumanPlayer();
                }
                    if ($("#roundWinner").text() === "none") {
                        showDrawOccurred();
                    } else {
                        namesOfPlayers();
                        showRoundResult();
                    }

                roundCount();
                activePlayer();

                if(numOfPlayers < 2) {
                    getGameWinner();
                    $(".row").hide();
                    $("#gameWinner").text(gameWinner);
                    $(".gameEnded").show();
                }
            }

            function endGameWithoutHumanPlayer() {
                numberOfPlayers();
                while (numOfPlayers > 1) {
                    getAIchosenCategory();
                    catValuesOfPlayers();
                    $(".row").hide();
                }
                getGameWinner();
                $("#gameWinner").text(gameWinner);
                $(".gameEnded").show();
            }

            function startSetup() {
                activePlayer();
                $("#round").hide();
                $("#draw").hide();
                $("#playersTurn").hide();
                $("#chosenCat").hide();
                $(".gameEnded").hide();
                playerNamesAndNumOfCards();
                cardCatNames();
                roundCount();
                numberOfPlayers();
                getNumOfCardsInComPile();
                getFirstCardDescription();
                getFirstCardValues();

            }

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
		</body>
</html>