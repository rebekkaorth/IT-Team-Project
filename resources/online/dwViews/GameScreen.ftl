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

        .draw_hide .turn_hide .round_hide {
            display: none;
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
                        <h6 id="numberOfCardsInPlayersDeck">10</h6>
                    </h6>
                </div>

                <!-- playersCard object-->
                <div class="card">
                    <img class="card-img-top" src="https://www.washingtonian.com/wp-content/uploads/2017/06/6-30-17-goat-yoga-congressional-cemetery-1.jpg" width="200px" height="130px" alt="Card image cap">
                    <div class="card-body">
                        <h5 class="cardDescription">Card title</h5>
                    </div>
                    <ul class="list-group list-group-flush">
                        <li class="list-group-item">
                            <h6 id="nameOfCat1">Category</h6>
                            <h6 id="cat1Value">3</h6>
                        </li>
                        <li class="list-group-item">
                            <h6 id="nameOfCat2">Category 2</h6>
                            <h6 id="cat2Value">4</h6>
                        </li>
                        <li class="list-group-item">
                            <h6 id="nameOfCat3">Category 3</h6>
                            <h6 id="cat3Value">4</h6>
                        </li>
                        <li class="list-group-item" id="nameOfCat4">
                            <h6 id="nameOfCat4">Category 4</h6>
                            <h6 id="cat4Value">5</h6>
                        </li>
                        <li class="list-group-item">
                            <h6 id="nameOfCat5">Category 5</h6>
                            <h6 id="cat5Value">6</h6>
                        </li>
                    </ul>
                </div>
            </div>


            <!--MIDDLE OF SCREEN (changes during the game) -->
            <!-- choose category form when players turn -->
            <div class="col-6 middle">

                   <div class="turn_hide" id="playersTurn">
                       <h2>It's your turn!</h2>
                       <form id="userCategory" name="chooseCategory">
                           Choose category: <input title="categoryName" type="text" name="category"><br>
                           <input type="submit" value="Choose">
                       </form>
                   </div>



                <!-- round result -->
                   <div class="round_hide" id="round">
                       <h4>Chosen category: <strong id="chosenCategory">category</strong></h4>
                   </div>
                   <div id="resultOfPlayers" >
                   <ul class="list-group list-group-flush">
                           <li class="list-group-item result"><p>You</p><strong id="valueUserCat">5</strong></li>
                           <li class="list-group-item result"><p>AI Player 1</p><strong id="valueAIOneCat">5</strong></li>
                           <li class="list-group-item result"><p>AI Player 2</p><strong id="valueAITwoCat">5</strong></li>
                           <li class="list-group-item result"><p>AI Player 3</p><strong id="valueAIThreeCat">5</strong></li>
                           <li class="list-group-item result"><p>AI Player 4</p><strong id="valueAIFourCat">5</strong></li>
                         </ul>
                       </div>
                       <button type="submit" class="btn btn-primary">Next Round</button>

                <!-- draw occurred -->

                     <div class="draw_hide" id="draw">
                         <h4>There was a draw!</h4>
                         </div>
                         <button type="submit" class="btn btn-primary">Next Round</button>

            </div>

            <!-- RIGHT SIDE OF THE SCREEN -->
            <div class="col">
                <div class="updatedGameData">
                    <h6>Number of cards in communal pile:</h6>
                    <h6 id="numberOfCardsInCommunalPile">0</h6>
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
        <h6>Number of rounds:</h6>
        <h6 id="numberOfRounds">20</h6>
    </div>


    <div class="footer">powered by THE GOATS</br>Rebekka Orth - Lisa Laux - Vincent Schlatt - Neil Kennedy - Liang Shan
    </div>
		
		<script type="text/javascript">

			// Method that is called on page load
			function initalize() {
                //call init function of globalController
                numOfCardsInDeckOfPlayer(10);
                usersFirstCardCatNames("Size", "Speed", "Range", "Firepower", "Cargo");
                setFirstCardValues(4,5,3,1,4);


                //start game on load
                startGame();

			}

            // FUNCTIONALITY TO CALL REST API METHODS

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
                    var responseText = xhr.response; // the text of the response
                    return(responseText);
                };
                // We have done everything we need to prepare the CORS request, so send it
                xhr.send();
            }

            //get category names of first card of user
            function numOfPlayers() {
                var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/numOfPlayers"); // Request type and URL
                if (!xhr) {
                    alert("CORS not supported");
                }
                xhr.onload = function(e) {
                    var responseText = xhr.response; // the text of the response
                    return(responseText);
                };
                xhr.send();
            }

            function getNumOfCardsForEachPlayer() {
                var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/getNumOfCardsForEachPlayer"); // Request type and URL
                if (!xhr) {
                    alert("CORS not supported");
                }
                xhr.onload = function(e) {
                    var responseText = xhr.response; // the text of the response
                    return(responseText); // lets produce an alert
                };
                xhr.send();
            }

            function activePlayer() {
                var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/activePlayer"); // Request type and URL
                if (!xhr) {
                    alert("CORS not supported");
                }
                xhr.onload = function(e) {
                    var responseText = xhr.response; // the text of the response
                    return(responseText);
                };
                xhr.send();
            }

            function getChosenCategory() {
                var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/getChosenCategory"); // Request type and URL
                if (!xhr) {
                    alert("CORS not supported");
                }
                xhr.onload = function(e) {
                    var responseText = xhr.response; // the text of the response
                    return(responseText);
                };
                xhr.send();
            }

            function getFirstCardDescription() {
                var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/getFirstCardDescription"); // Request type and URL
                if (!xhr) {
                    alert("CORS not supported");
                }
                xhr.onload = function(e) {
                    var responseText = xhr.response; // the text of the response
                    return(responseText);
                };
                xhr.send();
            }

            function getRoundWinner() {
                var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/getRoundWinner"); // Request type and URL
                if (!xhr) {
                    alert("CORS not supported");
                }
                xhr.onload = function(e) {
                    var responseText = xhr.response; // the text of the response
                    return(responseText);
                };
                xhr.send();
            }

            function drawOccurred() {
                var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/drawOccurred"); // Request type and URL
                if (!xhr) {
                    alert("CORS not supported");
                }
                xhr.onload = function(e) {
                    var responseText = xhr.response; // the text of the response
                    return(responseText);
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
            function draw(draw) {
                // First create a CORS request, this is the message we are going to send (a get request in this case)
                var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/draw?Draw="+draw); // Request type and URL+parameters
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

            function roundEnded(round) {
                var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/round?Round="+round); // Request type and URL+parameters
                if (!xhr) {
                    alert("CORS not supported");
                }
                xhr.onload = function(e) {
                    var responseText = xhr.response; // the text of the response
                    alert(responseText); // lets produce an alert
                };
                xhr.send();
            }

            function categoryChosen(Category) {
                var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/categoryChosen?Category="+Category); // Request type and URL+parameters
                if (!xhr) {
                    alert("CORS not supported");
                }
                xhr.onload = function(e) {
                    var responseText = xhr.response; // the text of the response
                    alert(responseText); // lets produce an alert
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
                    alert(responseText); // lets produce an alert
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
                    alert(responseText); // lets produce an alert
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
                    alert(responseText); // lets produce an alert
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
                    alert(responseText); // lets produce an alert
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
                    alert(responseText); // lets produce an alert
                };

                // We have done everything we need to prepare the CORS request, so send it
                xhr.send();
            }


            //FUNCTIONALITY TO UPDATE UI DURING A GAME

            //show number of cards left in deck of user
            function numOfCardsInDeckOfPlayer(numOfCardsLeft) {
                $("#numberOfCardsInPlayersDeck").text(numOfCardsLeft);
            }

            //show category names of user's first card
            function usersFirstCardCatNames(catName1, catName2, catName3, catName4, catName5 ) {
                $("#nameOfCat1").text(catName1);
                $("#nameOfCat2").text(catName2);
                $("#nameOfCat3").text(catName3);
                $("#nameOfCat4").text(catName4);
                $("#nameOfCat5").text(catName5);
            }

            //update values for each category of first card of user
            function setFirstCardValues(val1, val2, val3, val4, val5) {
			    $("#cat1Value").text(val1);
                $("#cat2Value").text(val2);
                $("#cat3Value").text(val3);
                $("#cat4Value").text(val4);
                $("#cat5Value").text(val5);
            }

            //update number of rounds
            function updateNumberOfRounds(numOfRounds) {
                $("#numberOfRounds").text(numOfRounds);
            }

            //update number of cars in communal pile
            function cardsInCommunalPile(cardsInComPile) {
                $("#numberOfCardsInCommunalPile").text(cardsInComPile);
            }

            //set names of players in the game
            function namesOfPlayers(human, player1, player2, player3, player4) {
                $("#nameOfPlayer1").text(human);
                $("#nameOfPlayer2").text(player1);
                $("#nameOfPlayer3").text(player2);
                $("#nameOfPlayer4").text(player3);
                $("#nameOfPlayer5").text(player4);
            }

            //update number of cards left of each player still in the game
            function updateCardsLeftOfAllPlayers(cardsHuman, cardsPlayer1, cardsPlayer2, cardsPlayer3, cardsPlayer4) {
                $("#cardsOfPlayer1").text(cardsHuman);
                $("#cardsOfPlayer2").text(cardsPlayer1);
                $("#cardsOfPlayer3").text(cardsPlayer2);
                $("#cardsOfPlayer4").text(cardsPlayer3);
                $("#cardsOfPlayer5").text(cardsPlayer4;
            }

            //update middle of game
            function showRoundResult() {
                jQuery('div.draw_hide').hide();
                jQuery('div.turn_hide').hide();
                jQuery('div.round_hide').show();
            }

            function showDrawOccurred() {
                jQuery('div.round_hide').hide();
                jQuery('div.turn_hide').hide();
                jQuery('div.draw_hide').show();
            }

            function showChooseCategory() {
                jQuery('div.round_hide').hide();
                jQuery('div.draw_hide').hide();
                jQuery('div.turn_hide').show();
            }

            //Separate information from updateGame
            function gameStatus() {
                var updateGame = updateGame();

                //split string from method in to parts needed
                var arrayOfContent = updateGame.split(' ');

            }

            //Separate information from roundResult
            function roundStatus() {
			    var roundResult = roundResult();

                //split string from method in to parts needed
            }

            //Separate information from gameFinished
            function finishedGame () {
			    var gameFinisehd = gameFinsihed();

                //split string from method in to parts needed
            }

            //game loop

            function startGame() {
                namesOfPlayers();
            }

            function game() {
                gameStatus();  //get status from game

                //update UI
                numOfCardsInDeckOfPlayer();
                usersFirstCardCatNames();
                setFirstCardValues();

                //game loop
                while (numPlayers > 1) {
                    numOfCardsInDeckOfPlayer();
                    usersFirstCardCatNames();
                    setFirstCardValues();
                    var result = getRoundResult //provided by JSON
                    round(result); //start a round
            }
                writeDatabase();  //calls db connector in java
            }
            
            //round loop
            function round(result) {
                var result = 0;

                if(result.concat('turn')) {
                    showChooseCategory();

                } else (result.concat('draw')) {
                    showDrawOccurred();
                } else {
                    showRoundResult();
                }


                }

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