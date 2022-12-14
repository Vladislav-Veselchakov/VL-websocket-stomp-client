package vl;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.web.socket.WebSocketHttpHeaders;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.RestTemplateXhrTransport;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.Transport;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Configuration
public class ClientWebSocketSockJsStompConfig {

    @Bean
    public WebSocketStompClient webSocketStompClient(WebSocketClient webSocketClient,
                                                     StompSessionHandler stompSessionHandler) {
        WebSocketStompClient webSocketStompClient = new WebSocketStompClient(webSocketClient);
        webSocketStompClient.setMessageConverter(new MappingJackson2MessageConverter());//new StringMessageConverter());
//        webSocketStompClient.setMessageConverter(new SimpleMessageConverter());
        WebSocketHttpHeaders handshakeHeaders = new WebSocketHttpHeaders();
        handshakeHeaders.add("VL-header", "hurray, heavens!!");
        StompHeaders connectHeaders = new StompHeaders();
        connectHeaders.add("VL-header", "hurray, heavens!!");
//        webSocketStompClient.connect("http://localhost:8083/gs-guide-websocket", stompSessionHandler);
        webSocketStompClient.connect("http://localhost:8083/gs-guide-websocket", handshakeHeaders,
                connectHeaders, stompSessionHandler);
        new Scanner(System.in).next(); // nextLine(); // Don't close immediately.
        return webSocketStompClient;
    }

    @Bean
    public WebSocketClient webSocketClient() {
        List<Transport> transports = new ArrayList<>();
        transports.add(new WebSocketTransport(new StandardWebSocketClient()));
        transports.add(new RestTemplateXhrTransport());
        return new SockJsClient(transports);
    }

    @Bean
    public StompSessionHandler stompSessionHandler() {
        return new ClientStompSessionHandler();
    }
}
