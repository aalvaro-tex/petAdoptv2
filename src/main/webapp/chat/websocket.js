const urlParams = new URLSearchParams(window.location.search);
const idConversacion = urlParams.get('idConversacion');
const idEmisor = urlParams.get('user');
var wsUri = 'ws://' + document.location.host + document.location.pathname.substr(0, document.location.pathname.indexOf("/web")) + '/websocket/' + idConversacion;
// nos conectamos a la conversacion del id correspondiente
var wsocket = new WebSocket(wsUri);

// funciones para el manejo de eventos

// Funcion para enviar mensajes
function send_message() {
    var campoTexto = document.getElementById("campoTexto");
    wsocket.send(idEmisor + "@" + idConversacion + "-" + campoTexto.value);
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
    // borramos el input
     var campoTexto = document.getElementById("campoTexto");
     campoTexto.value = '';
     // añadimos el mensaje 

    
};
wsocket.onerror = function (evt) {
    console.log(evt)
};