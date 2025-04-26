const urlParams = new URLSearchParams(window.location.search);
const idConversacion = urlParams.get('idConversacion');
var wsUri = 'ws://' + document.location.host+ document.location.pathname.substr(0,document.location.pathname.indexOf("/web")) + '/websocket/' + idConversacion;
// nos conectamos a la conversacion del id correspondiente
var wsocket = new WebSocket(wsUri);
               