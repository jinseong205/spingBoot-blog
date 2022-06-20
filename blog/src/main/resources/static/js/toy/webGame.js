var doAnim = true;

var canvas = document.getElementById("myCanvas");
var ctx = canvas.getContext("2d");

var playerW = canvas.width / 8;                                         //플레이어 가로
var playerH = canvas.height / 6;                                       //플레이어 세로
var playerX = (canvas.width - playerW) / 2;                              //플레이어 x 좌표
var playerY = canvas.height - playerH - (canvas.height / 24);              //플레이어 y 좌표

var arrowW = canvas.width / 32;
var arrowH = canvas.height / 24;
var arrowX = Math.floor(Math.random() * 750) + 25;
var arrowY = 0;
var arrowdy = 8;

var rightPressed = false;
var leftPressed = false;


var img = document.getElementById("nave");
var myPlayer = new Image();
myPlayer.src = "/image/webGame/player.png";

var myArrow = new Image();
myArrow.src = "/image/webGame/arrow.png";

function drawplayer() {
	ctx.beginPath();
	//player init locate, size
	ctx.drawImage(myPlayer, playerX, playerY, playerW, playerH);
	var cW = ctx.canvas.width, cH = ctx.canvas.height;
	ctx.closePath();
}

function drawarrow() {
	ctx.beginPath();
	//player init locate, size
	//init x init y xsize ysize 
	ctx.drawImage(myArrow, arrowX, arrowY, arrowW, arrowH);

	var cW = ctx.canvas.width, cH = ctx.canvas.height;
	ctx.closePath();
}

function arrowReload() {
	if (arrowY > 600) {
		arrowX = Math.floor(Math.random() * 750) + 25;
		arrowY = 0;
	}
}

//충돌판정
function collisionDectection() {
	if (playerX - (playerW / 8) < arrowX - (arrowW / 2) 
	&& playerX + (playerW / 2) > arrowX - (arrowW / 2) 
	&& playerY - (playerH / 2) < arrowY - 50 + (arrowH / 2) 
	&& playerY + (playerH / 2) > arrowY - 50 + (arrowH / 2)) {
		
		var returnValue = confirm('Game over \nRetry?');

		if(returnValue){
			arrowY = 0;
			arrowdy = 8;
		}else{
			doAnim = false;
		}
	}
}

document.addEventListener("keydown", keyDownHandler, false);
document.addEventListener("keyup", keyUpHandler, false);
document.addEventListener("mousemove", mouseMoveHandler, false);

function keyDownHandler(e) {
	if (e.keyCode == 39) {
		rightPressed = true;
	}
	else if (e.keyCode == 37) {
		leftPressed = true;
	}
}
function keyUpHandler(e) {
	if (e.keyCode == 39) {
		rightPressed = false;
	}
	else if (e.keyCode == 37) {
		leftPressed = false;
	}
}

function mouseMoveHandler(e) {
	var relativeX = e.clientX - canvas.offsetLeft;
	if (relativeX > 0 && relativeX < canvas.width) {
		playerX = relativeX - playerW / 2;
	}
}

function draw(){

	
	ctx.clearRect(0, 0, canvas.width, canvas.height);
	drawplayer();
	drawarrow();
	arrowReload();
	collisionDectection()

	if(!doAnim){
		return;
	}

	if (rightPressed && playerX < canvas.width - playerW) {
		playerX += 7;
	}
	else if (leftPressed && playerX > 0) {
		playerX -= 7;
	}
	arrowY += arrowdy;
	requestAnimationFrame(draw);
}


draw();

