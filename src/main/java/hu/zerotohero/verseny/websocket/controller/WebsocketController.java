package hu.zerotohero.verseny.websocket.controller;

import hu.zerotohero.verseny.websocket.entity.Order;
import hu.zerotohero.verseny.websocket.entity.Response;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

import java.security.Principal;

@Controller
public class WebsocketController {
    @MessageMapping("order")
    @SendToUser("/topic/done")
    public Response placeOrder(Principal principal, Order order) throws Exception{
        Thread.sleep(2000 + (long)(Math.random() * 2000));
        return new Response(order.getName() + " is done!");
    }
}
