package org.fireplume.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;
import java.util.UUID;

@Controller
public class SomeController {

    @Autowired
    private SimpMessagingTemplate template;

    @GetMapping(path = "/")
    public String index(Model model, Authentication authentication, Principal principal) {
        model.addAttribute("user", principal != null ? principal.getName() : null);
        return "index";
    }

    @GetMapping("/sock")
    @ResponseBody
    public String callSock(Principal principal) {
        if (principal != null) {
            template.convertAndSendToUser(principal.getName(), "/subscribe/data",
                    "Push: " + UUID.randomUUID().toString());
            return "I called sock for " + principal.getName();
        }
        return "no user";
    }

    @GetMapping("/sock-b")
    @ResponseBody
    public String callSockBroadcast() {
        template.convertAndSend("/subscribe/data",
                "Push: " + UUID.randomUUID().toString());
        return "I called sock for everybody";
    }

    /**
     * {@link MessageMapping} is similar to a usual controller endpoint {@link GetMapping}.
     * "/send" is a part of a bigger endpoint. The first part is in {@link org.fireplume.config.WebSocketConfig}.
     * Fill endpoint is "/sock-prefix/send". In JS: stompBroadcast.send("/sock-prefix/send", {}, json);
     * Where: stompBroadcast.send(command, headers, body)
     * <p>
     * {@link SendTo} is for sending data back to the subscribed stomp
     * <p>
     * stompBroadcast.subscribe('/subscribe/data', function(response){
     * writeResponse(response.body);
     * });
     *
     * @param message body of message
     * @return
     * @throws Exception
     */
    @MessageMapping("/send")
    @SendTo("/subscribe/data")
    public String greeting(String message) throws Exception {
        Thread.sleep(1000); // simulated delay
        return "Data was sent: " + message + ", and additional response" + UUID.randomUUID().toString();
    }
}
