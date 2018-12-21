var websocket = null;
var portId = 0;
var portOpen = false;
if('WebSocket' in window){
	websocket = new WebSocket('ws://127.0.0.1:7023/msgServer');
}else{
	alert("Not support webSocket");
}

websocket.onerror = function(){
	
}

websocket.onopen = function(){
	
}

websocket.onmessage = function(data){
	data = eval('(' + data.data + ')');
	if(data.msgType == "LINKMANAGER_MSG"){
		gotoLinkManager(data);
	}else if(data.msgType == "NODEMANAGER_MSG"){
		gotoNodeManager(data);
	}else if(data.msgType == "FOURMETERREAD_MSG"){
		gotoFourMeterReadManager(data);
	}else if(data.msgType == "NETWORKREAD_MSG"){
		gotoNetworkAndRead(data);
	}else if(data.msgType == "NODESTATUS_MSG"){
		gotoNodeStatus(data);
	}
	
}

websocket.onclose = function(){
	
}

window.onbeforeunload = function(){
	websocket.close();
}


