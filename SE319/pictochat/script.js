const socket = io('http://localhost:3000');
const messageForm = document.getElementById('Sendcontainer');
const messageInput = document.getElementById('inputMessage');
const messageContainer = document.getElementById(MessageContainer);
const screenName = document.getElementById("name-field");



function addNewUser() {
	appendMessage("You joined"); //prints on user screen
	socekt.emit('new-user', screenName);	 //sends to server
}

socket.on('chat-message', data => {
	appendMessage('${data.screenName}: ${data.message}');
})

socket.on('user-connected', ScreenName => {
	appendMessage('${screenName} connected'); //prints to screen when a different user connects
})

socket.on('user-disconnected', ScreenName => {
	appendMessage('${screenName} disconnected'); //prints to screen when a different user connects
})

//sends a message to the server
messageForm.addEventListener('submit', e => {
 e.preventDefault()
 const message = messageInput.value;
 appendMessage('You: {message}');
 socket.emit('chat-message', message);
 messageInput.value = "";
 })
 

 function apppendAMessage(toSend) {
	 const messageElement = document.createElement('div');
	 messageElement.innerText = toSend;
	 messageContainer.append(messageElement);
 }