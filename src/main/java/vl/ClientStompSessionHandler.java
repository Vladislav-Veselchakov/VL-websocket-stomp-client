package vl;


import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;

import java.lang.reflect.Type;

public class ClientStompSessionHandler extends StompSessionHandlerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(ClientStompSessionHandler.class);

    @Override
    public void afterConnected(StompSession session, StompHeaders headers) {
        logger.info("Client connected: headers {}", headers);

        session.subscribe("/app/subscribe", this);
        session.subscribe("/queue/responses", this);
        session.subscribe("/queue/errors", this);
//        session.subscribe("/topic/periodic", this);
        session.subscribe("/topic/greetings", this);

        HelloMessage message = new HelloMessage("VLVL");// "{\"name\": \"VLVL\"}";
//        JSONObject jsonObject= new JSONObject(message );
        logger.info("Client sends: {}", message);
        StompHeaders stomHeaders = new StompHeaders();
        stomHeaders.add("destination", "/app/hello");
        stomHeaders.add("VL-header","Hurray! o, Heavens!");
        session.send(stomHeaders, message);
//        session.send("/app/hello", message);
    }

    @Override
    public Type getPayloadType(StompHeaders headers) {
        return Greeting.class;
    }

    @Override
    public void handleFrame(StompHeaders headers, Object payload) {
        Greeting msg = (Greeting) payload;

        int a = 1;
        logger.info("Client received: \n\n\n {}\n headers {}", msg.getContent(), headers);
        ForNothinController.wsResp = msg.getContent();
    }

    @Override
    public void handleException(StompSession session, StompCommand command,
                                StompHeaders headers, byte[] payload, Throwable exception) {
        logger.error("Client error: exception {}, command {}, payload {}, headers {}",
                exception.getMessage(), command, payload, headers);
    }

    @Override
    public void handleTransportError(StompSession session, Throwable exception) {
        logger.error("Client transport error: error {}", exception.getMessage());
    }
}
