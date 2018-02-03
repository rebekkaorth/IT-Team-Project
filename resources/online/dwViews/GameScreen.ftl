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
            margin-top: 55px;
        }
        .playersCardsLeft {
            margin-top: 60px;
        }
        strong {
            margin-left: 10px;
        }
        .round{
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
                <div class="updatedGameData">
                    <h6>Number of cards left in your deck:
                        <h6 id="numberOfCardsInPlayersDeck"></h6>
                    </h6>
                </div>

                <!-- playersCard object-->
                <div class="card">
                    <img class="card-img-top" src="http://www.twizzle.co.uk/wp-content/uploads/2016/11/mfalcon.jpg" width="200px" height="130px" alt="Card image cap">
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

                  <div id="playersTurn">
                       <h2>It's your turn!</h2>
                       <form id="userCategory" name="chooseCategory">
                           Choose category: <input title="categoryName" type="text" name="category" id="input"/><br>
                           <input type="submit" value="Choose" class="submit"/>
                       </form>
                   </div>



                <!-- round result -->
                   <div id="round">
                       <h4>Chosen category: <strong id="chosenCategory"></strong></h4>
                       <h4>Round winner:<strong id="roundWinner"></strong></h4>

                   <div id="resultOfPlayers" >
                   <ul class="list-group list-group-flush">
                           <li class="list-group-item result"><p  class="nameOfPlayer1"></p><p id="valueCatPlayer1"></p></li>
                           <li class="list-group-item result"><p  class="nameOfPlayer2"></p><p id="valueCatPlayer2"></p></li>
                           <li class="list-group-item result"><p  class="nameOfPlayer3"></p><p id="valueCatPlayer3"></p></li>
                           <li class="list-group-item result"><p  class="nameOfPlayer4"></p><p id="valueCatPlayer4"></p></li>
                           <li class="list-group-item result"><p  class="nameOfPlayer5"></p><p id="valueCatPlayer5"></p></li>
                         </ul>
                       <button onclick="round()" type="submit">Next Round</button>
                       </div>
                   </div>

                <!-- draw occurred -->

                    <div id="draw">
                         <h4>There was a draw!</h4>
                         <button onclick="round()" type="submit" id="nextRound">Next Round</button>
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


    <div class="footer">powered by THE GOATS</br>Rebekka Orth - Lisa Laux - Vincent Schlatt - Neil Kennedy - Liang Shan
    </div>
		
		<script type="text/javascript">


            var numOfPlayers = 5;
            var activePlayerVar;
            var drawOc;

			// Method that is called on page load
			function initalize() {
                game(); // start game
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
                var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/numCardsInComPIle"); // Request type and URL
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
                    var n = parseInt(responseText[0]);
                    for (var i=1; i<(n+1); i++) {
                        $(".nameOfPlayer"+i).text(responseText[i]);
                    }
                };
                xhr.send();


            }

            function getNumOfCardsForEachPlayer() {
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
            }

            function activePlayer() {
                var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/activePlayer"); // Request type and URL
                if (!xhr) {
                    alert("CORS not supported");
                }
                xhr.onload = function(e) {
                    var responseText = JSON.parse(xhr.response); // the text of the response
                    console.log(responseText);
                    $("p:contains('"+ activePlayerVar +"')").parent().removeClass("active");
                    activePlayerVar = responseText;
                    $("p:contains('"+ activePlayerVar +"')").parent().addClass("active");
                };
                xhr.send();
            }

            function getChosenCategory() {
                var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/getChosenCategory"); // Request type and URL
                if (!xhr) {
                    alert("CORS not supported");
                }
                xhr.onload = function(e) {
                    var responseText = JSON.parse(xhr.response); // the text of the response
                    $('#chosenCategory').text(responseText);
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
                    for(var i=0; i<numOfPlayers; i++) {
                        $("#nameOfCat"+(i+1)).text(responseText[i]);
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
                    for (var i=0; i<numOfPlayers; i++) {
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

            function drawOccurred() {
                var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/drawOccurred"); // Request type and URL
                if (!xhr) {
                    alert("CORS not supported");
                }
                xhr.onload = function(e) {
                    var responseText = JSON.parse(xhr.response); // the text of the response
                    drawOc = responseText;
                    console.log("response: " + drawOc);
                };
                xhr.send();
            }

            function catValue() {
                var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/catValue"); // Request type and URL
                if (!xhr) {
                    alert("CORS not supported");
                }
                xhr.onload = function(e) {
                    var responseText = JSON.parse(xhr.response); // the text of the response
                    for (var i=0; i<numOfPlayers; i++){
                        $("#valueCatPlayer"+(i+1)).text(parseInt(responseText[i]));
                    }
                };
                xhr.send();
            }

            //get category values of first card of user
            function gameFinished() {
                var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/gameFinished"); // Request type and URL
                if (!xhr) {
                    alert("CORS not supported");
                }
                xhr.onload = function(e) {
                    var responseText = xhr.response; // the text of the response
                    return(responseText);
                };
                xhr.send();
            }
            

            //send to Java

            function roundEnded() {
                var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/roundEnded"); // Request type and URL+parameters
                if (!xhr) {
                    alert("CORS not supported");
                }
                xhr.onload = function(e) {
                    var responseText = xhr.response; // the text of the response
                };
                xhr.send();
            }

            function playerChoosesCat(Category) {
                var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/playerChoosesCat?Category="+Category); // Request type and URL+parameters
                if (!xhr) {
                    alert("CORS not supported");
                }
                xhr.onload = function(e) {
                    var responseText = xhr.response; // the text of the response

                };
                xhr.send();
            }

            function categoryChosen() {
                var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/categoryChosen");
                if (!xhr) {
                    alert("CORS not supported");
                }
                xhr.onload = function(e) {
                    var responseText = xhr.response; // the text of the response

                };
                xhr.send();
            }

            function setRoundWinner(roundWinner) {
                var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/setRoundWinner?Category="+roundWinner); // Request type and URL+parameters
                if (!xhr) {
                    alert("CORS not supported");
                }
                xhr.onload = function(e) {
                    var responseText = xhr.response; // the text of the response
                };
                xhr.send();
            }

            function setRoundCount(roundCount) {
                var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/setRoundCount?roundCount="+roundCount); // Request type and URL+parameters
                if (!xhr) {
                    alert("CORS not supported");
                }
                xhr.onload = function(e) {
                    var responseText = xhr.response; // the text of the response
                };
                xhr.send();
            }

            function updatePlayer(updatePlayer) {
                var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/updatePlayer?updatePlayer="+updatePlayer); // Request type and URL+parameters
                if (!xhr) {
                    alert("CORS not supported");
                }
                xhr.onload = function(e) {
                    var responseText = xhr.response; // the text of the response
                };
                xhr.send();
            }

            function setGameWinner(gameWinner) {
                var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/setGameWinner?gameWinner="+gameWinner); // Request type and URL+parameters
                if (!xhr) {
                    alert("CORS not supported");
                }
                xhr.onload = function(e) {
                    var responseText = xhr.response; // the text of the response
                };
                xhr.send();
            }

            //send request - when button to write game data to the DB was clicked
            function writeDatabase(writeDatabase) {
                // First create a CORS request, this is the message we are going to send (a get request in this case)
                var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/writeDatabase?writeDatabase="+writeDatabase); // Request type and URL+parameters
                // Message is not sent yet, but we can check that the browser supports CORS
                if (!xhr) {
                    alert("CORS not supported");
                }
                // CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
                // to do when the response arrives
                xhr.onload = function(e) {
                    var responseText = xhr.response; // the text of the response
                };
                // We have done everything we need to prepare the CORS request, so send it
                xhr.send();
            }


            //FUNCTIONALITY TO UPDATE UI DURING A GAME

            //update middle of game
            function showRoundResult() {
                $('#draw').hide();
                $('#playersTurn').hide();
                $('#round').show();
            }

            function showDrawOccurred() {
                $('#round').hide();
                $('#playersTurn').hide();
                $('#draw').show();
            }

            function showChooseCategory() {
                $('#round').hide();
                $('#draw').hide();
                $('#playersTurn').show();
            }

            function roundResult() {
                getChosenCategory();   //updates category field
                setRoundWinner();
                drawOccurred();
                getNumOfCardsInComPile();
                console.log("in game: " + drawOc);
                if (drawOc) {
                    showDrawOccurred();
                }
                namesOfPlayers(); //get names of players
			    catValue();   //updates values of all players
                updatePlayer();
                getRoundWinner(); //highlights winner of round
                showRoundResult();
                //set active player to roundWinner;
                numberOfPlayers();
                setRoundCount(1);
                roundEnded();
            }

            function round() {
                roundCount();
                activePlayer();
                console.log(activePlayerVar);
                getNumOfCardsInComPile();
                getNumOfCardsForEachPlayer();
                getFirstCardDescription();
                getFirstCardValues();
                if(activePlayerVar=="Human Player") {
                    showChooseCategory();
                    $('.submit').click(function() {
                        playerChoosesCat($('#input').text());
                        roundResult();
                    });

                } else {
                    categoryChosen();
                    roundResult();
                }
            }


            //game loop
            function game() {
                $("#round").hide();
                $("#draw").hide();
                $("#playersTurn").hide();
                namesOfPlayers();
                cardCatNames();
                //game

             //   while (numOfPlayers > 1) {
                    round(); //start a round
                //  }
                // writeDatabase();  //calls db connector in java
            }

            //prompt when game is finished

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