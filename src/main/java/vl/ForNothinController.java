package vl;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.text.DateFormatter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
public class ForNothinController {
    public static String wsResp;
    @GetMapping
    public String forFun() {
        LocalDateTime ldTime = LocalDateTime.now();
        DateTimeFormatter dfData = DateTimeFormatter.ofPattern("YYYY-MM-dd");
        DateTimeFormatter dfTime = DateTimeFormatter.ofPattern("HH:mm:ss");
        return """
        <!DOCTYPE html>
        <html lang="en">
        <head>
            <meta charset="UTF-8">
            <title>VL ws stomp client</title>
        </head>
        <body>
        <h1>VL websocket stopm client</h1>
        <h2>
        """
        + dfData.format(ldTime) + "<br/><br/>" + dfTime.format(ldTime) +
       """
        <br/>hello. Посмотри в консоли </h2>
        <h3>And here the response ws: </h3>"""
        +  wsResp +
        """
        </body>
        </html>                
        """;
    }
}
