const urlParams = new URLSearchParams(window.location.search);
const idConversacion = urlParams.get('idConversacion');
var wsUri = 'ws://' + document.location.host + document.location.pathname.substr(0, document.location.pathname.indexOf("/web")) + '/websocket/' + idConversacion;
// nos conectamos a la conversacion del id correspondiente
var wsocket = new WebSocket(wsUri);
var emisor = idConversacion;

// funciones para el manejo de eventos

// Funcion para enviar mensajes
function send_message() {
    var campoTexto = document.getElementById("campoTexto");
    wsocket.send(emisor + "@" + campoTexto.value);
}

// Funcion para salir de la sala
function disconnect() {
    wsocket.close();
}

// Listeners

wsocket.onopen = function (evt) {
    console.log(evt)
};
wsocket.onclose = function (evt) {
    console.log(evt)
};
wsocket.onmessage = function (evt) {
     console.log(evt)
     var campoTexto = document.getElementById("campoTexto");
     campoTexto.innerHTML = "";
};
wsocket.onerror = function (evt) {
    console.log(evt)
};