
var snakeSize = 20;
var running = false;
var orientation = "East";
var PosX = -snakeSize, PosY = 375;
var MoveX = snakeSize, MoveY = 0;
var id, gameOver, gradient;
//This button action determines the state of the game  and then changes the name of the button respectivley 
function gameMode(){
	var startStop = document.getElementById("startStop");
	if(startStop.value == "Start") {
		running = true;
		startStop.value = "Stop"
	}else {
		running = false;
		clearInterval(id);
		startStop.value = "Start";
	}
	
	if(running == true) {
		id = setInterval(makeNewBlock, 1000);
	}
	
}

//changes the X and Y coordinates so that way the snake travels in the correct direction (left)
function rightTurn() {
	if(orientation == "North") {
		MoveX = -snakeSize;
		MoveY = 0;
		orientation = "West";
	}else if(orientation == "South") {
		MoveX = snakeSize;
		MoveY = 0;
		orientation = "East";
	}else if(orientation == "West") {
		MoveY = -snakeSize;
		MoveX = 0;
		orientation = "South";
	}else  if(orientation == "East"){
		MoveX = 0;
		MoveY = snakeSize;
		orientation = "North";
	}	
}

//changes the X and Y coordinates so that way the snake travels in the correct direction (right)
function leftTurn() {
		if(orientation == "North") {
		MoveX = snakeSize;
		MoveY = 0;
		orientation = "East";
	}else if(orientation == "South") {
		MoveX = -snakeSize;
		MoveY = 0;
		orientation = "West";
	}else if(orientation == "West") {
		MoveY = snakeSize;
		MoveX = 0;
		orientation = "North";
	}else {
		MoveX = 0;
		MoveY = -snakeSize;
		orientation = "South";
	}
}

//creates a new block in based of the direction the person is headed
function makeNewBlock() {
var context = document.getElementById("canvasId").getContext("2d");
var gradient = context.createLinearGradient(0, 0, 0, 170);
gradient.addColorStop(0, "orange");
context.fillStyle = gradient;

PosX = PosX + MoveX;
PosY = PosY + MoveY;
var temp = PosX.toString() + "," + PosY.toString();
if(PosX < 0 || PosY < 0 || PosX > 1000 || PosY > 750){
var stop = document.getElementById("startStop");
stop.value = "Start";
 clearInterval(id);
 running == false;
}else {
	var j, k;
	context.rect(PosX, PosY, snakeSize, snakeSize);
    context.fill();
	}
}
	