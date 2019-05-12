import * as Stomp from 'stompjs';
import * as SockJS from 'sockjs-client';
import { BACKEND_URL } from "../env/environment";

let socketCache;
let stompClientCache;

export const connectToWebsocket = (channel, onMessage) => {
    socketCache = socketCache || new SockJS(BACKEND_URL + "/socket");
    stompClientCache = stompClientCache || Stomp.over(socketCache);

    stompClientCache.connect({}, (frame) => {
        stompClientCache.subscribe(channel, (message) => {
            onMessage(message)
        })
    }, error => {
        console.error("Error while connecting to websocket ", error);
    });

    stompClientCache.debug = null;
};

